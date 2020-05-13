package com.yourcompany;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


//import com.sun.jna.platform.win32.Sspi.TimeStamp;
import static org.testng.Assert.assertEquals;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Array;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



public class SampleSauceTest {
	

	  public static final String USERNAME = "";	  
	  public static final String ACCESS_KEY = "";
	  public static long beforeSession;
	  public static long afterSession;

	@Test
    public static void main() throws IOException, JSONException, InterruptedException {
		
        DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("platformName", "Android");
		  
//--------------------------------------------------------------------------------------------------------------------------------------
    
		  AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com/wd/hub"), caps);

//-----------------------------------------------------------------------------------------------------------------------------------
		  
		  driver.get("https://www.saucelabs.com");
		  
		  
		    String sauceUrl = driver.getCapabilities().getCapability("testobject_test_report_url").toString();
		    int lastSlashIndex = sauceUrl.lastIndexOf("/");
		    String jobId = sauceUrl.substring(lastSlashIndex + 1);
		    System.out.println(jobId);
	       
        driver.quit();
        
	       try{
 	   Thread.sleep(20000);
 	  }catch (InterruptedException ie1) {
 	    //ie1.printStackTrace();
 	  } 
        
	  	HttpGet request = new HttpGet("https://api.us-west-1.saucelabs.com/v1/rdc/jobs/"+ jobId + "/video.mp4");
	  	String auth = USERNAME + ":" + ACCESS_KEY;
	  	byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
	  	String authHeader = "Basic " + new String(encodedAuth);
	  	request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

	  	HttpClient client = HttpClientBuilder.create().build();
	  	HttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        
        int responseCode = response.getStatusLine().getStatusCode();

        System.out.println("Request Url: " + request.getURI());
        System.out.println("Response Code: " + responseCode);

        InputStream is = entity.getContent();

        String filePath = "/Path/To/video.mp4";
        FileOutputStream fos = new FileOutputStream(new File(filePath));
	  	
        int inByte;
        while ((inByte = is.read()) != -1) {
            fos.write(inByte);
        }

        is.close();
        fos.close();
      }

}
   
