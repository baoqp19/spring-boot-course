# Thông tin liên hệ với tôi

facebook tôi: https://www.facebook.com/pqbao.05


di chuyển class ctrl + nhân các file để copy rồi di chuyển vào thư mục cần đưa vào 

// Skill endpoint khong co delete skill vi bi loi, thì ta xóa job thì xóa cả skill những xóa skill thì job nó lỗi 
build.gradle.kts


-- 1. khi tạo mới user thì khi truyền id = 0 or null thì nó biêt là tạo mới user

-- 2. RequestBody là không cần nhập thông tin ở IDE mà nhập ở phần raw kiểu JSON thì nó sẽ ánh xạ và tạo mới nhờ có jackson mà nó có thể từ JSON sang kiểu Object và ngược lại, 

-- 3. put/patch
  - put là nó ghi đè cả đối tượng
  - patch là nó ghi đè riếng lẻ các thuộc tính
  => nhưng trong dự án thích dùng put hơn thì nó ngắn :v

-- Spring Data Rest: code ngắn, phù hợp để phát triển ứng dụng một cách nhanh nhất có
thể (vì code rất ít). Vì vậy, phù hợp để giải quyết tác vụ CRUD đơn giản.

-- @RestController : code dài hơn. 100% kiểm soát code của bạn. Bạn thích viết ngắn viết
dài tùy thích :v
     ================ XONG CHƯƠNG 4  ===============

-- ResponseEntity: gồm state, header, body

   --return ResponseEntity.status(HttpStatus.CREATED) mã phản hồi 201 khi tạo mới
   --return ResponseEntity.status(HttpStatus.OK) mã phản hồi 200 khi các thao tác khác

annotation: resControoler: trong Class Controller xây dựng các dịch vụ RestFul API, xử lý các yêu cầu HTTP

-- khi có throws trong class Controller thì nó nhảy sang GlobalException coi nó ứng với tên Throws nào để hiển thị ra thông báo

 #@RestControllerAdvice = @ControllerAdvice + @ResponseBody

  + @ControllerAdvice: là annotation để khai báo một lớp xử lý ngoại lệ toàn cục cho các Controller, định nghĩa phương thức xử lý ngoại lệ trong bất kỳ controller nào mà không cần khai báo lại cho từng Controller
  + @ResponseBody: là annotation chỉ rằng giá rị trả về trả về cho client là đinh dạng JSON hoặc XML 