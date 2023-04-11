package Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class weatherProgram {
	public static void main(String[] args) {
		// Set the path to the chromedriver executable
		// System.setProperty("webdriver.chrome.driver",
		// "/Users/brunojaredevangelistadacruz/Desktop/chromedriver_mac64/chromedriver");
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		// Create a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();

		// Navigate to the Google homepage
		driver.get("https://www.google.com/");

		// Find the search bar and enter the weather search query
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Weather in San Francisco, California");

		// Submit the search query
		searchBox.submit();

		// Wait for the results page to load
		WebElement searchResult = driver.findElement(By.id("wob_tm"));

		// Extract the temperature from the results and store it in an object
		String temperature = searchResult.getText();
		System.out.println("The temperature in San Francisco is: " + temperature + " degrees Celsius");

		// Close the browser
		driver.quit();
		// Call the OpenWeatherMap API to retrieve weather data for San Francisco
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=San+Francisco,California&appid=ecd7660dc79f6a42e1e7a96e306616e2&lang=";
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			  
	                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                StringBuilder sb = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    sb.append(line+"\n");
	                }
	                br.close();
	                
	                
	                
	                JSONObject  jObject = new JSONObject(sb.toString());
	                JSONObject wether = jObject.getJSONObject("main");
	                
	                Double fWether = wether.getDouble("temp");
	                    System.out.println(fWether);
	                    Double  Celsius = ((fWether-32)*5)/9;
	                    System.out.println("Temperature in celsius is: "+Celsius);  
	                    
	                Double CelsiusWeb =Double.parseDouble(temperature);    
	                
	                if (CelsiusWeb == Celsius) {
	                    System.out.println("Temperature in celsius by Google: "+CelsiusWeb +" Temperature in celsius  by RestAPi: "+Celsius + " the temperatures are the same ");
	                }else {
	                	throw new Exception("different temperatures");
					}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
