package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Dto.Request.InvoiceDetailRequest;
import dev.lvpq.CS502052.Dto.Request.InvoiceRequest;
import dev.lvpq.CS502052.Dto.Response.InvoiceResponse;
import dev.lvpq.CS502052.Dto.Response.ProductResponse;

import dev.lvpq.CS502052.Dto.Response.ProductWithQuantityResponse;
import dev.lvpq.CS502052.Entity.*;
import dev.lvpq.CS502052.Enums.OrderStatus;
import dev.lvpq.CS502052.Exception.DefineExceptions.AppException;
import dev.lvpq.CS502052.Exception.Error.AuthExceptionCode;
import dev.lvpq.CS502052.Mapper.InvoiceMapper;
import dev.lvpq.CS502052.Mapper.ProductMapper;
import dev.lvpq.CS502052.Repository.InvoiceDetailRepository;
import dev.lvpq.CS502052.Repository.InvoiceRepository;
import dev.lvpq.CS502052.Repository.ProductRepository;
import dev.lvpq.CS502052.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public InvoiceDetail addProductToInvoice(String productId) {
        User currentUser = userService.getCurrentUser();

        Invoice invoice = invoiceRepository.findByBuyerIdAndStatus(currentUser.getId(), OrderStatus.PENDING)
                .orElseGet(() -> {
                    Invoice newInvoice = new Invoice();
                    newInvoice.setBuyer(currentUser);
                    newInvoice.setStatus(OrderStatus.PENDING);
                    newInvoice.setCreatedAt(LocalDate.now());
                    return newInvoice;
                });
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Check if the product already exists in the invoice
        Optional<InvoiceDetail> existingDetail = invoice.getInvoiceDetails().stream()
                .filter(detail -> detail.getProduct().getId().equals(productId))
                .findFirst();
        if (existingDetail.isPresent()) {
            // Increment the quantity of the existing product
            InvoiceDetail invoiceDetail = existingDetail.get();
            invoiceDetail.setQuantity(invoiceDetail.getQuantity() + 1);
        } else {
            // Add new product to the invoice
            InvoiceDetail newInvoiceDetail = new InvoiceDetail();
            newInvoiceDetail.setProduct(product);
            newInvoiceDetail.setInvoice(invoice);
            newInvoiceDetail.setQuantity(1);  // Start with quantity 1
            invoice.getInvoiceDetails().add(newInvoiceDetail);
        }
        // Save the updated invoice
        invoiceRepository.save(invoice);
        return existingDetail.orElseGet(() -> {
            InvoiceDetail newDetail = new InvoiceDetail();
            newDetail.setProduct(product);
            newDetail.setQuantity(1);
            return newDetail;
        });
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
//            }
        } else {
            throw new AppException(AuthExceptionCode.PRODUCT_NOT_EXISTED);
        }

        invoiceRepository.save(invoice);

        return invoiceMapper.toResponse(invoice);
    }


    public void removeProductIfQuantityZero(String productId) {
        User currentUser = userService.getCurrentUser();
        Invoice invoice = invoiceRepository.findByBuyerIdAndStatus(currentUser.getId(), OrderStatus.PENDING)
                .orElseThrow(() -> new AppException(AuthExceptionCode.INVOICE_NOT_FOUND));

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
        User currentUser = userService.getCurrentUser();
        var userInvoices = invoiceRepository
                .findByBuyerIdAndStatus(currentUser.getId(), OrderStatus.PENDING)
                .orElse(null);

        if (userInvoices == null) return new ArrayList<>();

        var productQuantityMap = userInvoices.getProduct();
        return productQuantityMap.entrySet().stream()
                .map(entry -> new ProductWithQuantityResponse(
                        productMapper.toDetailResponse(entry.getKey()),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }

}
