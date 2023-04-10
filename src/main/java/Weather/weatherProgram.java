package Weather;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Weather.Gson.WeatherData;

public class weatherProgram {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "/Users/brunojaredevangelistadacruz/Desktop/chromedriver_mac64/chromedriver");

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
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=San+Francisco,California&appid=ecd7660dc79f6a42e1e7a96e306616e2&lang=" ;
        String weatherJson = "temperature";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            weatherJson = sb.toString();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserialize the weather data into a WeatherData object using Gson
        Gson gson = new GsonBuilder().create();
        WeatherData weatherData = gson.fromJson(weatherJson, WeatherData.class);

        // Extract the temperature from the OpenWeatherMap data and convert it from Kelvin to Fahrenheit
        WeatherData openWeatherTemp = weatherData.getMain().getTemp();
		double fahrenheitTemp = (openWeatherTemp  + 32);

        // Calculate the temperature difference between the Google search and OpenWeatherMap data
        double googleTemp = 0;
		double tempDiff = Math.abs(googleTemp - fahrenheitTemp);
        System.out.println("The temperature difference between Google and OpenWeatherMap data is: " + tempDiff + " degrees Fahrenheit");
    }

    }

