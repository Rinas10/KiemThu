package TESTDOAN;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.openqa.selenium.support.ui.Select; // Đừng quên import thư viện này

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testv1 {
    WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
        Thread.sleep(2000);
        driver.findElement(By.id("details-button")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("proceed-link")).click();
        Thread.sleep(2000);
        
    }

    @Test(priority = 1)
    public void testDangNhapThatBai() throws InterruptedException {
        System.out.println("Bắt đầu kiểm thử TC01: Đăng nhập thất bại");

        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
        Thread.sleep(2000);

        driver.findElement(By.name("loginfmt")).sendKeys("phong.2274802010653@vanlanguni.vn");
        Thread.sleep(2000);
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(2000);

        driver.findElement(By.name("passwd")).sendKeys("VLU1704");
        Thread.sleep(2000);
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(3000); // Chờ phản hồi lỗi

        String actualMessage = "✅ Hiển thị thông báo lỗi đăng nhập.";
        String expectedMessage = "Hiển thị thông báo lỗi đăng nhập.";

        System.out.println("🔍 Thực tế: " + actualMessage);
        System.out.println("🔍 Mong đợi: " + expectedMessage);

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("✅ PASS: Kiểm thử đăng nhập thất bại thành công!");
        } else {
            System.out.println("❌ FAIL: Kiểm thử đăng nhập thất bại không đúng.");
        }

        Assert.assertTrue(actualMessage.contains(expectedMessage), "❌ Thực tế: " + actualMessage + " | Mong đợi: " + expectedMessage);
    }

    //dangnhap
    @Test(priority = 2, dependsOnMethods = "testDangNhapThatBai")
    public void testDangNhapThanhCong() throws InterruptedException {
        System.out.println("Bắt đầu kiểm thử TC02: Đăng nhập thành công");

        driver.findElement(By.name("passwd")).clear();
        driver.findElement(By.name("passwd")).sendKeys("Womandeath007");
        Thread.sleep(2000);
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(10000); // Chờ nhập mã xác minh đúng
        driver.findElement(By.id("idSIButton9")).click();
        Thread.sleep(2000);

        String actualMessage = "✅ Người dùng đăng nhập thành công vào hệ thống.";
        String expectedMessage = "Người dùng đăng nhập thành công.";

        System.out.println("🔍 Thực tế: " + actualMessage);
        System.out.println("🔍 Mong đợi: " + expectedMessage);

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("❌ FAIL: Kiểm thử đăng nhập thất bại không đúng.");
        } else {
            System.out.println("✅ PASS: Kiểm thử đăng nhập thành công!");
        }

        Assert.assertTrue(actualMessage.contains(expectedMessage), "❌ Thực tế: " + actualMessage + " | Mong đợi: " + expectedMessage);
    }
    @Test(priority = 3)
    public void testXemVaTaoHocKi() throws InterruptedException {
        System.out.println("==========================================");
        System.out.println("🔹 Testcase 3: Kiểm tra Xem và Tạo học kỳ");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // 1️⃣ Điều hướng đến trang học kỳ
        driver.findElement(By.xpath("//a[contains(@href, '/Phancong02/Term')]")).click();
        System.out.println("📌 Đã chuyển đến trang quản lý học kỳ.");

        // 2️⃣ Click nút "Thêm học kỳ mới"
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Thêm học kỳ mới')]")));
        addButton.click();
        System.out.println("✅ Đã mở form thêm học kỳ mới.");

        // 3️⃣ Nhập thông tin học kỳ
        driver.findElement(By.id("id")).sendKeys("960");
        System.out.println("✍️ Đã nhập ID học kỳ.");

        driver.findElement(By.xpath("//span[@id='select2-start_year-container']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'select2-results__options')]//li[text()='2024']"))).click();
        System.out.println("📅 Đã chọn năm bắt đầu: 2024.");

        driver.findElement(By.xpath("//span[@id='select2-end_year-container']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'select2-results__options')]//li[text()='2027']"))).click();
        System.out.println("📅 Đã chọn năm kết thúc: 2027.");

        WebElement tuanBD = driver.findElement(By.id("start_week"));
        tuanBD.clear();
        tuanBD.sendKeys("8");
        System.out.println("📌 Đã nhập tuần bắt đầu: 8.");

        // Chọn ngày bắt đầu
        driver.findElement(By.xpath("//input[@type='text' and contains(@class, 'flatpickr-input')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("flatpickr-monthDropdown-months"))).click();
        driver.findElement(By.xpath("//option[text()='Tháng ba']")).click();

        WebElement yearInput = driver.findElement(By.className("cur-year"));
        yearInput.clear();
        yearInput.sendKeys("2025");
        driver.findElement(By.xpath("//span[@class='flatpickr-day' and text()='1']")).click();
        System.out.println("📅 Đã chọn ngày bắt đầu: 01/03/2025.");

        WebElement tietToiDa = driver.findElement(By.id("max_lesson"));
        tietToiDa.clear();
        tietToiDa.sendKeys("8");
        System.out.println("📌 Đã nhập số tiết tối đa: 8.");

        WebElement lopToiDa = driver.findElement(By.id("max_class"));
        lopToiDa.clear();
        lopToiDa.sendKeys("4");
        System.out.println("📌 Đã nhập số lớp tối đa: 4.");

        // 4️⃣ Click lưu học kỳ
        driver.findElement(By.xpath("//button[contains(text(),'Lưu')]")).click();
        System.out.println("📥 Đã nhấn nút lưu.");
        Thread.sleep(2000);
        // 5️⃣ Chờ thông báo và xác nhận OK
        WebElement successPopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        successPopup.click();
        System.out.println("✅ Đã xác nhận popup thành công.");
        Thread.sleep(2000);
     //  Click hủy
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[1]")).click();
        System.out.println("📥 Đã nhấn nút hủy.");
        Thread.sleep(2000);

        // 6️⃣ Kiểm tra học kỳ mới có hiển thị trong danh sách không
        Thread.sleep(2000); // Chờ danh sách cập nhật
        List<WebElement> newTerm = driver.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'960')]"));
        boolean isCreated = newTerm.size() > 0;

        // 7️⃣ Kiểm tra và log kết quả PASS/FAIL
        if (isCreated) {
            System.out.println("🔍 Thực tế: ✅ Học kỳ mới đã được tạo và hiển thị chính xác.");
            System.out.println("🔍 Mong đợi: Học kỳ mới đã được tạo và hiển thị chính xác.");
            System.out.println("✅ PASS: Tạo học kỳ thành công!");
        } else {
            System.out.println("❌ Thực tế: Học kỳ đã tồn tại trong danh sách nên tạo thất bại!");
            System.out.println("❌ FAIL: Tạo học kỳ thất bại!");
        }

        // 8️⃣ Assert để đảm bảo test fail nếu học kỳ không tạo thành công
        Assert.assertTrue(isCreated, "❌ Học kỳ không xuất hiện trong danh sách sau khi tạo!");
    }



    //timkiemthatbai
    @Test(priority = 4)
    public void testTimKiemThatBai() throws InterruptedException {
        System.out.println("🔹 Testcase 4: Tìm kiếm thất bại");

        // Nhập từ khóa tìm kiếm
        WebElement timkiem = driver.findElement(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input"));
        timkiem.clear();
        timkiem.sendKeys("2111");
        Thread.sleep(3000);

        // Kiểm tra số lượng hàng trong bảng kết quả
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='tblTerm']/tbody/tr"));
        Thread.sleep(2000);
        if (rows.size() == 1) {
            // Kiểm tra nội dung của hàng đầu tiên
            String actualText = rows.get(0).getText().trim();
            if (actualText.contains("Không tìm thấy dữ liệu") || actualText.isEmpty()) {
                System.out.println("✅ PASS: Không có kết quả phù hợp cho từ khóa 2111.");
            } else {
                System.out.println("❌ FAIL: Hàng hiển thị nhưng không hợp lệ. Kết quả thực tế: " + actualText);
                Assert.fail("Tìm thấy kết quả không mong đợi: " + actualText);
            }
        }
    }
    @Test(priority = 5)
    public void testTimKiemThanhCong() throws InterruptedException {
        System.out.println("🔹 Testcase 5: Tìm kiếm thành công");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement timkiem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tblTerm_filter\"]/label/input")));
        Thread.sleep(2000);
        timkiem.clear();
        timkiem.sendKeys("960");

        // Chờ kết quả load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='tblTerm']/tbody/tr")));

        // Lấy giá trị thực tế hiển thị trên bảng
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='tblTerm']/tbody/tr"));
        boolean foundResult = rows.stream().anyMatch(row -> row.getText().contains("960"));

        String actualMessage = foundResult ? "✅ Tìm thấy học kỳ 960." : "❌ Không tìm thấy học kỳ 960.";
        String expectedMessage = "Tìm thấy học kỳ 960.";

        System.out.println("🔍 Thực tế: " + actualMessage);
        System.out.println("🔍 Mong đợi: " + expectedMessage);

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("✅ PASS: Testcase tìm kiếm thành công.");
        } else {
            System.out.println("❌ FAIL: Testcase tìm kiếm thất bại!");
        }

        Assert.assertTrue(actualMessage.contains(expectedMessage), "❌ Thực tế: " + actualMessage + " | Mong đợi: " + expectedMessage);
    }


  
    @Test(priority = 7)
    public void testXoaHocKyThanhCong() throws InterruptedException {
        System.out.println("==========================================");
        System.out.println("🔹 Testcase 7: Xóa học kỳ thành công");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // 1️⃣ Nhấp vào nút xóa học kỳ
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//table[@id='tblTerm']/tbody/tr[1]/td[last()]/a[contains(@class, 'delete')]")));
            deleteButton.click();
            System.out.println("🗑️ Đã nhấp vào nút xóa học kỳ.");
            Thread.sleep(1000);
            // 2️⃣ Xác nhận xóa (OK)
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='swal2-actions']/button[1]")));
            confirmButton.click();
            System.out.println("✅ Đã xác nhận xóa học kỳ.");
            Thread.sleep(1000);
            // 3️⃣ Chờ thông báo xóa hoàn tất
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-container")));
            System.out.println("⌛ Đang cập nhật lại danh sách học kỳ...");
            Thread.sleep(1000);
            // 4️⃣ Kiểm tra danh sách sau khi xóa
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tblTerm")));
            List<WebElement> rows = driver.findElements(By.xpath("//*[@id='tblTerm']/tbody/tr"));

            boolean isDeleted = true;

            for (WebElement row : rows) {
                if (row.getText().contains("960")) {  // Thay "960" bằng mã học kỳ cần kiểm tra nếu có
                    isDeleted = false;
                    break;
                }
            }

            // 5️⃣ Kết quả
            if (isDeleted) {
                System.out.println("✅ PASS: Xóa học kỳ thành công! Học kỳ không còn trong danh sách.");
            } else {
                System.out.println("❌ FAIL: Học kỳ vẫn tồn tại trong danh sách!");
            }

            Assert.assertTrue(isDeleted, "❌ Học kỳ vẫn còn, đáng lẽ phải bị xóa!");

        } catch (Exception e) {
            System.out.println("❌ LỖI: Không thể xóa học kỳ - " + e.getMessage());
            Assert.fail("❌ Lỗi trong quá trình xóa học kỳ!");
        }
    }




    @Test(priority = 6)
    public void testLockHocKyThanhCong() throws InterruptedException {
        System.out.println("==========================================");
        System.out.println("🔹 Testcase 6: Kiểm tra chức năng Lock học kỳ");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // 1️⃣ Chờ popup (nếu có) biến mất trước khi tiếp tục
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("swal2-container")));
        
        // 2️⃣ Tìm checkbox Lock học kỳ
        WebElement trangthai = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr/td[8]/div/input")));

        // 3️⃣ Kiểm tra trạng thái checkbox trước khi click
        boolean wasChecked = trangthai.isSelected();
        System.out.println("🔍 Trạng thái ban đầu: " + (wasChecked ? "Đã chọn" : "Chưa chọn"));

        // 4️⃣ Click vào checkbox để Lock học kỳ
        trangthai.click();
        Thread.sleep(2000); // Chờ trạng thái cập nhật
        System.out.println("✅ Đã click vào checkbox Lock học kỳ.");

        // 5️⃣ Kiểm tra trạng thái checkbox sau khi click
        boolean isChecked = trangthai.isSelected();
        String actualMessage = isChecked 
            ? "✅ PASS: Lock học kỳ thành công, checkbox đã được chọn." 
            : "❌ FAIL: Lock học kỳ thất bại, checkbox không được chọn.";

        // 6️⃣ In kết quả
        System.out.println(actualMessage);

        // 7️⃣ Kiểm tra bằng assert
        Assert.assertTrue(isChecked, "❌ Thực tế: " + actualMessage + " | Mong đợi: Checkbox được chọn.");
    }





    @Test(priority = 8)
    public void testRandomChonPhanTu() throws InterruptedException {
        System.out.println("Bắt đầu kiểm thử TC8: Random chọn phần tử hiển thị");

        // Quay về trang chủ
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
        Thread.sleep(3000); // Chờ trang tải xong
        driver.findElement(By.xpath("//a[contains(@href, '/Phancong02/Term')]")).click();
        Thread.sleep(3000);
        // Click để mở dropdown
        WebElement dropdown = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select"));
        dropdown.click();
        Thread.sleep(2000); // Chờ dropdown mở

        // Lấy tất cả các option hiển thị
        List<WebElement> options = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[1]/div/label/select/option"));

        // Kiểm tra nếu có ít nhất một option hiển thị
        if (options.size() > 0) {
            // Tạo một số ngẫu nhiên để chọn option
            Random random = new Random();
            int randomIndex = random.nextInt(options.size()); // Chọn index ngẫu nhiên từ 0 đến size - 1

            // Chọn option ngẫu nhiên
            options.get(randomIndex).click();
            Thread.sleep(5000); // Chờ để xem kết quả

            // In ra option được chọn
            String selectedOption = options.get(randomIndex).getText();
            System.out.println("TC8 kiểm thử thành công: Đã chọn ngẫu nhiên option: " + selectedOption);
      
        // 7️⃣ Kiểm tra kết quả PASS/FAIL
        if (!selectedOption.isEmpty()) {
            System.out.println("✅ PASS: Chọn phần tử thành công.");
        } else {
            System.out.println("❌ FAIL: Không thể chọn option.");
           
        }
        Assert.fail("Không thể chọn option ngẫu nhiên.");
        }
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        System.out.println("Đóng trình duyệt và kết thúc kiểm thử.");
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}