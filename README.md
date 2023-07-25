# Ứng dụng quản lý hóa đơn khách sạn - Hướng dẫn sử dụng

## Giới thiệu

Đây là một ứng dụng quản lý các hóa đơn của khách hàng thuê phòng trong khách sạn X. Ứng dụng hỗ trợ quản lý hóa đơn theo giờ và hóa đơn theo ngày. Thông tin chung của chi tiết hóa đơn bao gồm Mã hóa đơn, Ngày hóa đơn, Tên khách hàng, Mã phòng và Đơn giá. Thông tin riêng của từng loại hóa đơn đã được mô tả dưới đây.

## Các loại hóa đơn

### Hóa đơn theo giờ

- Hóa đơn theo giờ còn có thông tin về số giờ thuê.
- Thành tiền được tính bằng công thức: Thành tiền = Số giờ thuê * Đơn giá.
- Trường hợp số giờ thuê vượt quá 24 giờ và nhỏ hơn 30 giờ, chỉ tính 24 giờ.
- Nếu số giờ thuê lớn hơn 30 giờ, không sử dụng loại hóa đơn theo giờ.

### Hóa đơn theo ngày

- Hóa đơn theo ngày đi kèm thông tin số ngày thuê.
- Thành tiền được tính bằng công thức: Thành tiền = Số ngày thuê * Đơn giá.
- Nếu số ngày thuê lớn hơn 7, áp dụng giảm 20% đơn giá cho những ngày còn lại sau 7 ngày.

## Chức năng của ứng dụng

- Thêm, xóa, sửa thông tin hóa đơn.
- Tìm kiếm hóa đơn theo mã hóa đơn, ngày hóa đơn, tên khách hàng hoặc mã phòng.
- Tính tổng số lượng cho từng loại thuê phòng (hóa đơn theo giờ và hóa đơn theo ngày).
- Tính trung bình thành tiền của hóa đơn thuê phòng trong một tháng nào đó.

## Yêu cầu

1. Xây dựng chương trình quản lý hóa đơn khách sạn, áp dụng các mẫu thiết kế sau:
   - ECB (Entity-Control-Boundary)
   - MVC (Model-View-Controller)
   - Observer
   - Command
   - Memento
   - Singleton

2. Lưu trữ dữ liệu:
   - Sử dụng File để lưu trữ dữ liệu.
   - Sử dụng JSON để lưu trữ dữ liệu.
   - Sử dụng hệ quản trị CSDL quan hệ (RDBMS) để lưu trữ dữ liệu.

## Cấu trúc báo cáo

### 4.1. Thu thập yêu cầu
- Use case: Mô tả các use case của ứng dụng.
- Mô tả Use case: Mô tả chi tiết từng use case.
- Sơ đồ use case: Biểu đồ mô tả mối quan hệ giữa các use case.

### 4.2. Phân tích yêu cầu
- Sơ đồ đối tượng (ECB): Biểu đồ mô tả mối quan hệ giữa các đối tượng trong ứng dụng.
- Sơ đồ class: Biểu đồ mô tả cấu trúc class trong ứng dụng.
- Sơ đồ tuần tự: Biểu đồ mô tả luồng xử lý của các use case.
- Sơ đồ trạng thái: Biểu đồ mô tả các trạng thái của các đối tượng.

### 4.3. Thiết kế
- Sơ đồ Thành phần (components): Biểu đồ mô tả các thành phần của ứng dụng.
- Sơ đồ class: Biểu đồ mô tả cấu trúc class sau khi đã áp dụng các mẫu thiết kế.
- Sơ đồ tuần tự: Biểu đồ mô tả luồng xử lý của các use case sau khi đã áp dụng các mẫu thiết kế.
- Áp dụng mẫu: Mô tả cách áp dụng các mẫu thiết kế trong ứng dụng.

### 4.4. Cài đặt
- Mô tả quá trình cài đặt ứng dụng.
- Demo hoạt động của ứng dụng.

Vui lòng thực hiện theo hướng dẫn và cung cấp báo cáo với cấu trúc như trên sau khi hoàn thành dự án. Chúc bạn thành công trong việc xây dựng ứng dụng quản lý hóa đơn khách sạn!
