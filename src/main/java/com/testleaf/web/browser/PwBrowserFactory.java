package com.testleaf.web.browser;


import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.testleaf.constants.BrowserTypes;

public class PwBrowserFactory {

	
	public static Page getBrowserFromFactory(BrowserTypes browserType) {
		
		Playwright playwright = Playwright.create();
		switch(browserType) {
		case CHROME : 
			return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
		case FIREFOX :
			return playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)).newContext().newPage();
		default : throw new IllegalArgumentException("Incorrect BrowserType was provided");
		}
		
		
	}

}
