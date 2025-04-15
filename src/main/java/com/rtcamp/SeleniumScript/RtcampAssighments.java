package com.rtcamp.SeleniumScript;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RtcampAssighments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 WebDriver driver= WebDriverManager.chromedriver().create();
		 driver.manage().window().maximize();
		 driver.get("https://www.saucedemo.com/v1/inventory.html");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 Select s=new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
         s.selectByVisibleText("Price (low to high)");
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
         List<WebElement> elements=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
         List<Double> actuallist=new ArrayList<>();
        
         for(WebElement el: elements) {
        	 actuallist.add(Double.parseDouble(el.getText().replace("$", "").trim()));
        	
        	 
         }
         System.out.println("ActualList"+actuallist);
         List<Double> expectedsortedlist=new ArrayList<>(actuallist);
         Collections.sort(expectedsortedlist);
         System.out.println("expectedsortedlist"+expectedsortedlist);
         Assert.assertEquals(actuallist, expectedsortedlist);
         System.out.println("Prices are correctly sorted from Low to High: " + actuallist);
         driver.close();

	}

}
