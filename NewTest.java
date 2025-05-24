package TESTDOAN;

import org.testng.annotations.Test;
import Pages.LienhePage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class NewTest {
    WebDriver driver;
    WebDriverWait wait;
    String generatedMaNganh;
    String generatedTenNguoiDung;
    String generatedMaGV;
    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        driver = new ChromeDriver(); // Gán vào biến toàn cục
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,10); // Chờ tối đa 10 giây

        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");

        // Xử lý bảo mật (nếu có)
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("details-button"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("proceed-link"))).click();
        } catch (Exception e) {
            System.out.println("Không cần bỏ qua cảnh báo bảo mật.");
        }
    }



    @Test(priority = 1)
    public void testDangNhapThanhCong() throws InterruptedException, IOException, ParseException {
        System.out.println("🔹 Testcase 1: Đăng nhập thành công");

        LienhePage lienhe = new LienhePage(driver);
        FileReader reader = new FileReader("C:\\dulieu.json");

        JSONParser jsonparser = new JSONParser();

        Object obj = jsonparser.parse(reader);

        JSONObject empjsonobj = (JSONObject) obj;
        JSONArray array = (JSONArray) empjsonobj.get("info");
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject info = (JSONObject) array.get(i);
            String name = (String) info.get("Email");
            String matkhau = (String) info.get("Password");
    
           
        lienhe.login(name, matkhau);
        }
    }

    @Test(priority = 2)
    public void testTaoNganh() throws InterruptedException, IOException, ParseException {
        System.out.println("🔹 Testcase: TC2: Tạo ngành học");
        LienhePage lienhe = new LienhePage(driver);
        Random random = new Random();

        // 🔹 Tạo mã ngành ngẫu nhiên (số từ 1000 đến 9999)
        generatedMaNganh = String.valueOf(1000 + random.nextInt(9000));
        System.out.println("🆕 Mã ngành được sinh: " + generatedMaNganh);
 

        FileReader reader = new FileReader("C:\\dulieu.json");
        JSONParser jsonparser = new JSONParser();
        Object obj = jsonparser.parse(reader);
        JSONObject empjsonobj = (JSONObject) obj;
        JSONArray array = (JSONArray) empjsonobj.get("info");

        for (int i = 0; i < array.size(); i++) {
            JSONObject info = (JSONObject) array.get(i);
            String tennganh = (String) info.get("TenNganh");
            String tenviettat = (String) info.get("TenVietTat");
            String manganh = (String) info.get("MaNganh");  // đọc mã ngành nếu có sẵn

            System.out.println("📌 Đọc từ JSON: Tên ngành = " + tennganh + ", Viết tắt = " + tenviettat);

            Thread.sleep(1000);
            if (tennganh != null && !tennganh.isEmpty() && tenviettat != null && !tenviettat.isEmpty()) {
                System.out.println("✅ PASS: Dữ liệu từ JSON hợp lệ!");

                // Tạo mã ngành ngẫu nhiên nếu chưa có
                if (manganh == null || manganh.isEmpty()) {
                    manganh = String.valueOf(1000 + random.nextInt(9000));
                }

                lienhe.openNganhHocPage();
                lienhe.clickThemNganh();
                lienhe.nhapThongTinNganh(manganh, tennganh, tenviettat);
                lienhe.chonCTDT();
                lienhe.clickLuu();

                System.out.println("✔️ Đã nhập ngành học: [Mã: " + manganh + "] - [Tên: " + tennganh + "] - [Viết tắt: " + tenviettat + "]");
            } 
        }
    }


    @Test(priority = 3)
    public void testtimkiem() throws InterruptedException {
        System.out.println("\uD83D\uDD39 Testcase: TC3: Tìm kiếm ngành vừa tạo");
        LienhePage lienhe = new LienhePage(driver);

        if (generatedMaNganh != null) {
            lienhe.nhaptimkiem(generatedMaNganh);
            Thread.sleep(2000);

            String xpathKetQua = "//table/tbody/tr[1]/td[2]";
            String actualMaNganh = driver.findElement(By.xpath(xpathKetQua)).getText().trim();

            System.out.println("🔍 Kiểm tra kết quả tìm kiếm...");
            System.out.println("Thực tế: " + actualMaNganh + " | Mong đợi: " + generatedMaNganh);

            if (actualMaNganh.equals(generatedMaNganh)) {
                System.out.println("✅ PASS: Đã tìm kiếm đúng mã ngành: " + actualMaNganh);
            } else {
                System.out.println("❌ FAIL: Mã ngành tìm kiếm không khớp! (Thực tế: \"" + actualMaNganh + "\", Mong đợi: \"" + generatedMaNganh + "\")");
            }
            
            Assert.assertEquals(actualMaNganh, generatedMaNganh, "❌ FAIL: Kết quả tìm kiếm không đúng!");
        } else {
            System.out.println("⚠ Lỗi: Mã ngành chưa được tạo trước đó!");
            Assert.fail("⚠ Lỗi: Mã ngành chưa được tạo trước đó!");
        }
    }



    @Test(priority = 4)
    public void testXoaNganh() throws InterruptedException {
        System.out.println("\uD83D\uDD39 Testcase: TC4: Xóa ngành học");
        LienhePage lienhe = new LienhePage(driver);

        if (generatedMaNganh != null) {
            lienhe.nhaptimkiem(generatedMaNganh);
            Thread.sleep(2000);

            String xpathKetQua = "//table/tbody/tr[1]/td[2]";
            WebElement ketQua = driver.findElement(By.xpath(xpathKetQua));
            if (ketQua.getText().trim().equals(generatedMaNganh)) {
                lienhe.clickXoanganh();
                Thread.sleep(2000);
                lienhe.nhaptimkiem(generatedMaNganh);
                boolean isDeleted = driver.findElements(By.xpath(xpathKetQua)).isEmpty();
                if (isDeleted) {
                    System.out.println("✅ PASS: Mã ngành đã được xóa thành công.");
                } else {
                    System.out.println("❌ FAIL: Mã ngành vẫn tồn tại sau khi xóa!");
                }
                Assert.assertTrue(isDeleted);
            } else {
                System.out.println("❌ FAIL: Không tìm thấy đúng mã ngành cần xóa!");
            }
        } else {
            System.out.println("⚠ Lỗi: Không có mã ngành nào để xóa!");
        }
    }

    @Test(priority = 5)
    public void clickzoom() throws InterruptedException {
        System.out.println("🔹 Testcase: TC5:Zoom màn hình");
        LienhePage lienhe = new LienhePage(driver);
        lienhe.clickzoom();
        lienhe.clickzoom();
    }
    @Test(priority = 6)
    public void testThemNguoiDung() throws InterruptedException {
        System.out.println("🔹 Testcase 6: Thêm người dùng");

        LienhePage lienhe = new LienhePage(driver);
        Random random = new Random();

        // 🔹 Tạo thông tin người dùng ngẫu nhiên
        generatedMaGV = "t" + (100 + random.nextInt(900));  // 🔹 Lưu lại mã giảng viên
        String TenGiangVien = "NTT" + generatedMaGV.toLowerCase();
        String Email = TenGiangVien + random.nextInt(900) + "@vanlanguni.vn";

        lienhe.openNguoiDungPage();
        lienhe.clickThemNguoiDung();
        lienhe.nhapThongTinNguoiDung(generatedMaGV, TenGiangVien, Email);
        lienhe.chonLoaiGV();
        lienhe.Role();
        lienhe.clickLuuNguoiDung();

        Thread.sleep(3000); // Đợi phản hồi từ hệ thống

        // 🔹 Kiểm tra xem người dùng đã được thêm thành công chưa
        lienhe.timKiemNguoiDungTheoTenGV(generatedMaGV);
        String xpathKetQua = "//table/tbody/tr[1]/td[2]";
        WebElement ketQua = driver.findElement(By.xpath(xpathKetQua));
        String actualMaGV = ketQua.getText().trim();

        if (actualMaGV.equals(generatedMaGV)) {
            System.out.println("✅ PASS: Đã thêm thành công người dùng với mã giảng viên: " + generatedMaGV);
        } else {
            System.out.println("❌ FAIL: Không tìm thấy người dùng vừa thêm! (Thực tế: \"" + actualMaGV + "\", Mong đợi: \"" + generatedMaGV + "\")");
        }
        Assert.assertEquals(actualMaGV, generatedMaGV);
    }


    @Test(priority = 7, dependsOnMethods = "testThemNguoiDung")
    public void testTimKiemNguoiDung() throws InterruptedException {
        System.out.println("\uD83D\uDD39 Testcase: TC7: Tìm kiếm giảng viên vừa tạo");

        Assert.assertNotNull(generatedMaGV, "❌ Mã giảng viên bị null!");
        Assert.assertFalse(generatedMaGV.isEmpty(), "❌ Mã giảng viên rỗng!");

        LienhePage lienhe = new LienhePage(driver);
        lienhe.timKiemNguoiDungTheoTenGV(generatedMaGV);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        String xpathKetQua = "//table/tbody/tr[1]/td[2]";

        WebElement ketQua = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathKetQua)));
        String actualMaGV = ketQua.getText().trim();

        if (actualMaGV.equals(generatedMaGV)) {
            System.out.println("✅ PASS: Đã tìm kiếm đúng giảng viên: " + actualMaGV);
        } else {
            System.out.println("❌ FAIL: Mã giảng viên tìm kiếm không khớp! (Thực tế: \"" + actualMaGV + "\", Mong đợi: \"" + generatedMaGV + "\")");
        }
        Assert.assertEquals(actualMaGV, generatedMaGV);
    }

    @Test(priority = 8, dependsOnMethods = "testTimKiemNguoiDung")
    public void testXoaNguoiDung() throws InterruptedException {
        System.out.println("\uD83D\uDD39 Testcase: TC8: Xóa giảng viên vừa tìm kiếm");
        LienhePage lienhe = new LienhePage(driver);

        lienhe.clickXoaNguoiDung(generatedMaGV);
        lienhe.timKiemNguoiDungTheoTenGV(generatedMaGV);

        String xpathKetQua = "//table/tbody/tr[1]/td[2]";
        boolean isDeleted = driver.findElements(By.xpath(xpathKetQua)).isEmpty();

        if (isDeleted) {
            System.out.println("✅ PASS: Đã xóa thành công giảng viên: " + generatedMaGV);
        } else {
            System.out.println("❌ FAIL: Người dùng chưa bị xóa!");
        }
        Assert.assertTrue(isDeleted, "❌ Người dùng chưa bị xóa!");
    }
}
