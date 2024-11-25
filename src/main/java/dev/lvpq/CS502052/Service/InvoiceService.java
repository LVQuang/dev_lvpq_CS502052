package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.InvoiceRequest;
import dev.lvpq.CS502052.Dto.Response.InvoiceResponse;
import dev.lvpq.CS502052.Dto.Response.ProductWithQuantityResponse;
import dev.lvpq.CS502052.Entity.*;
import dev.lvpq.CS502052.Enums.OrderStatus;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Mapper.InvoiceMapper;
import dev.lvpq.CS502052.Mapper.ProductMapper;
import dev.lvpq.CS502052.Repository.InvoiceDetailRepository;
import dev.lvpq.CS502052.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import dev.lvpq.CS502052.Utils.InvoiceUtil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;
    @Autowired
    private InvoiceUtil invoiceUtil;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;


    @Autowired
    private UserService userService;

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
    public String getInvoiceByStatus() {
        User currentUser = userService.getCurrentUser();
        return invoiceRepository.findByBuyerIdAndStatus(currentUser.getId(), OrderStatus.PENDING).get().getId();

    }
    public void addProduct(String productId) {
        var params = invoiceUtil.buildInvoiceProductResponse(productId);
        var invoice = params.getInvoice();
        var product = params.getProduct();

        invoice.addProduct(product);
        invoiceRepository.save(invoice);
    }
    public InvoiceResponse updateInvoice(String productId, InvoiceRequest invoiceRequest) {
        // Tìm hóa đơn theo ID
        User currentUser = userService.getCurrentUser();
        Invoice invoice = invoiceRepository.findByBuyerIdAndStatus(currentUser.getId(), OrderStatus.PENDING)
                .orElseThrow(() -> new AppException(AuthExceptionCode.INVOICE_NOT_FOUND));
        Optional<InvoiceDetail> existingDetail = invoice.getInvoiceDetails().stream()
                .filter(detail -> detail.getProduct().getId().equals(productId))
                .findFirst();
        if (existingDetail.isPresent()) {
            InvoiceDetail invoiceDetail = existingDetail.get();
            invoiceDetail.setQuantity(invoiceRequest.getQuantity());

            invoiceRepository.save(invoice);
        } else {
            throw new AppException(AuthExceptionCode.PRODUCT_NOT_EXISTED);
        }

        invoiceRepository.save(invoice);

        return invoiceMapper.toResponse(invoice);
    }
    public InvoiceResponse updateTotalPrice(Invoice invoice, double discount) {
        BigDecimal total = BigDecimal.valueOf(0);

        for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
            var productPrice = detail.getProduct().getPrice();
            var quantity = detail.getQuantity();

            total = total.add(productPrice.multiply(BigDecimal.valueOf(quantity)));
        }
        total = total.subtract(BigDecimal.valueOf(discount));
        invoice.setTotalPrice(total.doubleValue());
        return invoiceMapper.toResponse(invoice);
    }

    public void removeProductIfQuantityZero(String productId) {
        var invoice = invoiceUtil.getInvoiceCurrentUser();
        if (invoice == null)
            throw new AppException(AuthExceptionCode.INVOICE_NOT_FOUND);

        Optional<InvoiceDetail> existingDetail = invoice.getInvoiceDetails().stream()
                .filter(detail -> detail.getProduct().getId().equals(productId))
                .findFirst();
        if (existingDetail.isPresent()) {
            InvoiceDetail invoiceDetail = existingDetail.get();
                invoice.getInvoiceDetails().remove(invoiceDetail);
                invoiceDetailRepository.delete(invoiceDetail);
                invoiceRepository.save(invoice);
        } else {
            throw new AppException(AuthExceptionCode.PRODUCT_NOT_EXISTED);
        }
    }

    public List<ProductWithQuantityResponse> getUserCartProducts() {
        var userInvoices = invoiceUtil.getInvoiceCurrentUser();
        if (userInvoices == null) return new ArrayList<>();

        var productQuantityMap = userInvoices.getProduct();
        return productQuantityMap.entrySet().stream()
                .map(entry -> new ProductWithQuantityResponse(
                        productMapper.toDetailResponse(entry.getKey()),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }
    public void confirmInvoice() {
        var invoice = invoiceUtil.getInvoiceCurrentUser();
        if (invoice == null)
            throw new AppException(AuthExceptionCode.INVOICE_NOT_FOUND);
        invoice.setStatus(OrderStatus.DELIVERED);
        invoiceRepository.save(invoice);
    }

    public void setTotalPrice(Long amount) {
        var invoice = invoiceUtil.getInvoiceCurrentUser();
        if (invoice == null)
            throw new AppException(AuthExceptionCode.INVOICE_NOT_FOUND);
        invoice.setTotalPrice(amount);
        invoiceRepository.save(invoice);
    }

}
