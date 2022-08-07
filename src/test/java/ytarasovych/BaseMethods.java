package ytarasovych;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseMethods {
    @BeforeClass
    public void setConfigurations(){
        Configuration.browser = "chrome";
        Configuration.timeout = 20;
    }

    @BeforeMethod
    public void openHomePage(){
        Selenide.open("https://www.google.com");
    }
}
