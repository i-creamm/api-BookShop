# 📦 Bookshop Management API
 Đây là dự án Backend xây dựng hệ thống quản lý mua bán sách bằng Java Spring Boot. API cung cấp các tính năng quản lý danh mục, sản phẩm, đơn đặt hàng, giỏ hàng và tích hợp bảo mật.

# 🚀 Công nghệ sử dụng

- Java 17 
- Framework: Spring Boot 4.x, Spring Data JPA
- Database: MySQL
- Security: Spring Security, JWT
- Công cụ: Maven, Lombok
- Validation: Hibernate Validator (JSR 380)

# 🛠️ Các tính năng chính
- Quản lý sản phẩm: Thêm, sửa, xóa, tìm kiếm sản phẩm.
- Validation: Kiểm tra dữ liệu đầu vào chặt chẽ
- Xử lý lỗi tập trung: Sử dụng @RestControllerAdvice để trả về định dạng lỗi nhất quán.

```text
src/main/java/ecom/

├── common          # Cấu hình ENum Role
├── config          # Cấu hình Bean, Security
├── controller      # Nơi tiếp nhận các HTTP Request
├── dto             # Data Transfer Object (Dữ liệu gửi nhận qua API)
├── exception       # Xử lý lỗi tùy chỉnh (Custom Exceptions)
├── entity          # Các thực thể Database (Entities)
├── repository      # Giao tiếp với Database qua Spring Data JPA
└── service         # Nơi xử lý logic nghiệp vụ (Business Logic)
    └── impl
```

Dự án này được tạo ra nhằm mục đich học tập.
