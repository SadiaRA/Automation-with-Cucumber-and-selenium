package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver InitializeDriver() throws IOException {
		prop=new Properties();
		FileInputStream fis= new FileInputStream("/home/sadia/eclipse-workspace/End2EndProject/src/main/java/resources/data.properties");
		prop.load(fis);
		//for setting the browser from maven rather than properties file
		//mvn test -Dbrowser=chrome
		//String browserName= System.getProperty("browser");
		String browserName= prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/home/sadia/Downloads/chromedriver");

		 driver = new ChromeDriver();
			
		}
		else if(browserName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "/home/sadia/Downloads/geckodriver");
		driver=new FirefoxDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	public String getScreenshotPath( String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=ts.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                String DestFile=System.getProperty("user.dir")+"/reports/"+testCaseName+".png";

                //Copy file at destination

                FileUtils.copyFile(SrcFile, new File(DestFile));
                return DestFile;
	}

}
