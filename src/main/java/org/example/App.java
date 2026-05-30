package org.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Задание №1 + точка входа, вызывающая Task2 и Task3.
 */
public class App {

    /** Путь к chromedriver.exe в файловой системе. */
    static final String DRIVER_PATH = "E:\\proga\\ST-7\\chromedriver-win64\\chromedriver.exe";

    /** Путь к браузеру Chrome for Testing. */
    static final String CHROME_BINARY = "E:\\proga\\ST-7\\chrome-win64\\chrome.exe";

    /** Создаёт настроенный экземпляр ChromeDriver. */
    static WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(CHROME_BINARY);
        return new ChromeDriver(options);
    }

    public static void main(String[] args) {
        WebDriver webDriver = createDriver();
        try {
            // ===== Задание №1 =====
            webDriver.get("https://www.calculator.net/password-generator.html");

            // Пароли генерируются JS-ом в <div id="resultid"> после загрузки страницы.
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            WebElement result = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id("resultid")));
            wait.until(d -> !result.getText().trim().isEmpty());

            // Внутри блока результата сам пароль выводится жирным (<b>).
            String password;
            try {
                password = result.findElement(By.tagName("b")).getText().trim();
            } catch (Exception e) {
                password = result.getText().trim();
            }

            System.out.println("=== Задание №1: сгенерированный пароль ===");
            System.out.println(password);
            System.out.println();

            // ===== Задание №2 =====
            Task2.run(webDriver);

            // ===== Задание №3 =====
            Task3.run(webDriver);

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
