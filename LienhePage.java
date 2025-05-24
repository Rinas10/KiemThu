package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LienhePage {
	private WebDriverWait wait;
	private WebDriver driver;
    private By btnDangNhap = By.xpath("/html/body/div/div[3]/div[2]/div/div/div/div/form/div/div/div/button");
    private By inputEmail = By.name("loginfmt");
    private By btnNext = By.id("idSIButton9");
    private By inputPassword = By.name("passwd");
   
    private By Signin = By.id("idSIButton9");
    private By yes = By.id("idSIButton9");
   
    //nganh
    private By menuNganhHoc = By.xpath("//html/body/div[2]/div[1]/div[2]/ul/li[2]/a");
    private By chonnganh = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/ul/li[2]/a");
    private By btnThemNganh = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]/button");
    private By txtMaNganh = By.xpath("/html/body/div[3]/div[2]/form/div[1]/input");
    private By txtTenNganh = By.xpath("/html/body/div[3]/div[2]/form/div[2]/input");
    private By txtTenVietTat = By.xpath("/html/body/div[3]/div[2]/form/div[3]/input");
    private By dropdownCTDT = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span/span[1]");
    private By listCTDT = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[2]/span/span[2]/ul/li[1]");
    private By btnLuu = By.xpath("/html/body/div[3]/div[2]/form/div[5]/button[2]");
    //private By txtTimKiem = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");
    //TimKiem
    private By timkiem = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");
    //Xoa
    private By Xoa = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div/div/div/div[2]/div/div/table/tbody/tr[1]/td[6]/a[2]");
    private By xacnhanXoa = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
    //zoom
    private By Zoom1 = By.xpath("/html/body/div[2]/nav/div/div/ul[2]/li/a/i");
    private By Zoom2 = By.xpath("/html/body/div[2]/nav/div/div/ul[2]/li/a/i");
    // Người dùng
    private By menuNguoiDung = By.xpath("/html/body/div[2]/div[1]/div[2]/ul/li[3]/a");
    private By btnThemNguoiDung = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[2]/div/div[2]/button");
    private By txtMaGiangVien = By.xpath("/html/body/div[3]/div[2]/form/div[1]/input");
    private By txtTenGV = By.xpath("/html/body/div[3]/div[2]/form/div[2]/input");
    private By txtEmail = By.xpath("/html/body/div[3]/div[2]/form/div[3]/input");
    private By dropdownLGV = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span/span[1]/span/span[1]");
    private By listLGV = By.xpath("/html/body/div[3]/div[2]/form/div[4]/div/span[2]/span/span[2]/ul/li[1]");
    private By dropdownRole = By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span[1]/span[1]/span/span[1]");
    private By clickRole = By.xpath("/html/body/div[3]/div[2]/form/div[5]/div/span[2]/span/span[2]/ul/li[1]");
    private By Luu = By.xpath("/html/body/div[3]/div[2]/form/div[7]/button[2]");
    
    private By txtTimkiemnguoidung = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/div[1]/div[2]/div/div[1]/div/label/input");
    private By xoaNguoiDung = By.xpath("/html/body/div[2]/div[2]/div[3]/div/section/div[2]/div[2]/div/div/table/tbody/tr[1]/td[7]/a[2]");
    private By xacNhanXoaNguoiDung = By.xpath("/html/body/div[3]/div/div[6]/button[1]");
        public LienhePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver,10);
        }
    public void login(String inEmail, String inpassword) throws InterruptedException {
        driver.findElement(btnDangNhap).click();
        Thread.sleep(2000);
        driver.findElement(inputEmail).sendKeys(inEmail);
        Thread.sleep(2000);
        driver.findElement(btnNext).click();
        Thread.sleep(2000);
        driver.findElement(inputPassword).sendKeys(inpassword);
        Thread.sleep(2000);

        driver.findElement(Signin).click();
        Thread.sleep(10000);
        driver.findElement(yes).click();
        Thread.sleep(2000);
    }
    //nganh
    
        public void openNganhHocPage() throws InterruptedException {
            driver.findElement(menuNganhHoc).click();
            Thread.sleep(2000);
            driver.findElement(chonnganh).click();
            Thread.sleep(2000);
        }

        public void clickThemNganh() throws InterruptedException {
            driver.findElement(btnThemNganh).click();
            Thread.sleep(2000);
        }

        public void nhapThongTinNganh(String maNganh, String tenNganh, String tenVietTat) throws InterruptedException {
            driver.findElement(txtMaNganh).sendKeys(maNganh);
            Thread.sleep(2000);
            
            driver.findElement(txtTenNganh).sendKeys(tenNganh);
            Thread.sleep(2000);
            driver.findElement(txtTenVietTat).sendKeys(tenVietTat);
            Thread.sleep(2000);
        }

        public void chonCTDT() throws InterruptedException {
            driver.findElement(dropdownCTDT).click();
            Thread.sleep(2000);
            driver.findElement(listCTDT).click();
            Thread.sleep(2000);
        }

        public void clickLuu() throws InterruptedException {
            driver.findElement(btnLuu).click();
            Thread.sleep(2000);
        }
      //TimKiem
        public void nhaptimkiem(String generatedMaNganh) throws InterruptedException {
            WebElement searchBox = driver.findElement(timkiem);
            
            // Xóa nội dung cũ trước khi nhập
            searchBox.clear();
            Thread.sleep(1000);
            
            // Nhập mã ngành cần tìm kiếm
            searchBox.sendKeys(generatedMaNganh);
            Thread.sleep(1000);
            
            // Nhấn Enter để kích hoạt tìm kiếm
            searchBox.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            
            // Chờ dữ liệu trong bảng hiển thị
            By searchResult = By.xpath("//table/tbody/tr");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult));

            // Kiểm tra xem có kết quả hay không
            int resultCount = driver.findElements(searchResult).size();
            if (resultCount > 0) {
                System.out.println("✔ Tìm thấy kết quả cho mã ngành: " + generatedMaNganh);
            } else {
                System.out.println("❌ Không tìm thấy kết quả nào!");
            }
        }
        //Xoa
        public void clickXoanganh() throws InterruptedException {
            driver.findElement(Xoa).click();
            Thread.sleep(2000);
            driver.findElement(xacnhanXoa).click();
            Thread.sleep(2000);
        }

	//zoom
        public void clickzoom() throws InterruptedException {
            driver.findElement(Zoom1).click();
            Thread.sleep(2000);
            driver.findElement(Zoom2).click();
            Thread.sleep(2000);
        }
        // Người dùng
        public void openNguoiDungPage() throws InterruptedException {
            driver.findElement(menuNguoiDung).click();
            Thread.sleep(2000);
        }

        public void clickThemNguoiDung() throws InterruptedException {
            driver.findElement(btnThemNguoiDung).click();
            Thread.sleep(2000);
        }

        public void nhapThongTinNguoiDung(String MaNguoiDung, String TenGV, String email) throws InterruptedException {
            driver.findElement(txtMaGiangVien).sendKeys(MaNguoiDung);
            Thread.sleep(1000);
            driver.findElement(txtTenGV).sendKeys(TenGV);
            Thread.sleep(1000);
            driver.findElement(txtEmail).sendKeys(email);
            Thread.sleep(1000);
        }
        
        
        public void chonLoaiGV() throws InterruptedException {
            driver.findElement(dropdownLGV).click();
            Thread.sleep(1000);
            driver.findElement(listLGV).click();
            Thread.sleep(1000);

        }
        public void Role() throws InterruptedException {

            driver.findElement(dropdownRole).click();
            Thread.sleep(1000);
            driver.findElement(clickRole).click();
            Thread.sleep(1000);
        }
        

        public void clickLuuNguoiDung() throws InterruptedException {
            driver.findElement(Luu).click();
            Thread.sleep(1000);
        }

        public void timKiemNguoiDungTheoTenGV(String TenGV) throws InterruptedException {
            WebElement searchBox = driver.findElement(txtTimkiemnguoidung);
            searchBox.click();
            Thread.sleep(1000);
            searchBox.clear(); // Xóa nội dung cũ trước khi nhập
            searchBox.sendKeys(TenGV);
            Thread.sleep(1000);
            searchBox.sendKeys(Keys.ENTER);
            Thread.sleep(2000);

            // Chờ dữ liệu hiển thị
            By searchResult = By.xpath("//table/tbody/tr");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult));

            // Kiểm tra xem có kết quả hay không
            int resultCount = driver.findElements(searchResult).size();
            if (resultCount > 0) {
                System.out.println("✔ Tìm thấy kết quả cho giảng viên: " + TenGV);
            } else {
                System.out.println("❌ Không tìm thấy kết quả nào!");
            }
        }

        


            
        
        public void clickXoaNguoiDung(String maGV) throws InterruptedException {
            driver.findElement(xoaNguoiDung).click();
            Thread.sleep(2000);
            driver.findElement(xacNhanXoaNguoiDung).click();
            Thread.sleep(2000);System.out.println("🔹 Đang xóa người dùng có mã: " + maGV);
        }
        

        

}
