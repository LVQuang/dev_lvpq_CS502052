# Đặc tả

## MÔ TẢ CHUNG

Hệ thống CRM E-commerce Giày sẽ quản lý quy trình mua hàng, bán hàng, theo dõi đơn hàng, và giữ chân khách hàng. Hệ thống tập trung vào việc tối ưu hóa quản lý kho hàng, quy trình bán hàng, và cải thiện trải nghiệm khách hàng thông qua quản lý khách hàng (CRM).

## YÊU CẦU CHỨC NĂNG

### 2.1 Quy trình mua hàng (Purchasing Process)

Quản lý kho hàng: Theo dõi số lượng sản phẩm tồn kho theo thời gian thực.

Cảnh báo nhập thêm hàng: Tự động cảnh báo khi lượng sản phẩm đạt đến ngưỡng tồn kho tối thiểu.

Hỗ trợ admin nhập hàng: Gợi ý cho admin số tiền cần thiết để nhập thêm hàng và tạo đơn đặt hàng.

### 2.2 Quy trình bán hàng (Selling Process)

Tìm kiếm sản phẩm: Người dùng có thể tìm kiếm sản phẩm dựa trên từ khóa.

Giỏ hàng: Hiển thị số lượng và tổng giá trị của các sản phẩm trong giỏ hàng theo thời gian thực.

Thanh toán: Hỗ trợ nhiều phương thức thanh toán (chuyển khoản, ví điện tử, thẻ tín dụng).

Quản lý đơn hàng: Khách hàng có thể theo dõi trạng thái đơn hàng sau khi thanh toán.

### 2.3 Theo dõi đơn hàng và vận chuyển (Package Tracking and Logistics)

Chờ xác nhận: Khách hàng xác nhận đơn hàng.

Đóng gói: Admin nhận yêu cầu đóng gói.

Vận chuyển: Admin cập nhật trạng thái đơn hàng trong quá trình vận chuyển.

Giao hàng thành công: Hệ thống nhận phản hồi từ khách hàng khi đơn hàng được giao thành công.
> [!NOTE]
> Hệ thống phải thông báo cho khách hàng và admin về trạng thái từng giai đoạn vận chuyển.

### 2.4 Quản lý quan hệ khách hàng (CRM)

Tìm khách hàng: Cho phép tìm kiếm khách hàng thông qua email hoặc thông tin cá nhân.

Chốt khách hàng: Hỗ trợ admin dựa vào lịch sử mua hàng để chốt khách hàng tiềm năng.

Giữ khách hàng:

* Dựa trên điểm tín nhiệm (đánh giá qua giá trị và tần suất mua hàng).

* Lưu trữ lịch sử mua hàng của khách hàng.

* Cung cấp ưu đãi hoặc khuyến mãi cho khách hàng thân thiết.

## YÊU CẦU PHI CHỨC NĂNG

### 3.1 Bảo mật.

Hệ thống phải sử dụng chuẩn bảo mật HTTPS để mã hóa dữ liệu.

Xác thực hai yếu tố (2FA) cho admin nhằm đảm bảo an toàn khi đăng nhập.

Mật khẩu của người dùng và admin phải được mã hóa và lưu trữ an toàn trong cơ sở dữ liệu.

### 3.2 Hiệu năng.

Hệ thống phải đáp ứng số lượng người dùng lớn mà không bị gián đoạn, đặc biệt trong các giai đoạn mua sắm cao điểm.

Tốc độ phản hồi của hệ thống (thời gian truy cập trang web, tìm kiếm sản phẩm, xử lý thanh toán) phải nhanh chóng và mượt mà.

### 3.3 Khả năng mở rộng.

Hệ thống linh hoạt và dễ dàng mở rộng, có thể nâng cấp khi cần để xử lý lượng khách hàng tăng cao hoặc thêm các tính năng mới mà không ảnh hưởng đến hoạt động của hệ thống hiện tại.

### 3.4 Khả năng tích hợp.

Hệ thống có khả năng tích hợp với các dịch vụ bên thứ ba, như các hệ thống quản lý vận chuyển hoặc các dịch vụ thanh toán trực tuyến (PayPal, MoMo).

### 3.5 Giao diện người dùng.

Giao diện phải thân thiện với người dùng và dễ sử dụng, đặc biệt là đối với khách hàng và admin.

Hỗ trợ đa ngôn ngữ để khách hàng từ nhiều quốc gia có thể sử dụng.

### 3.6 Sao lưu và phục hồi dữ liệu.

Dữ liệu của hệ thống phải được sao lưu định kỳ và có cơ chế phục hồi dữ liệu trong trường hợp có sự cố.
