package com.testleaf.web.browser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import com.microsoft.playwright.Page;
import com.testleaf.constants.BrowserTypes;

public class PwBrowserPool {
	
	
	private static final Logger logger = Logger.getLogger(PwBrowserPool.class.getName());
	
	private static final ConcurrentMap<BrowserTypes, BlockingQueue<Page>> pageMap = new ConcurrentHashMap<>();
	
	private static final ConcurrentMap<Page, BrowserTypes> pageBrowserMap = new ConcurrentHashMap<>();
	
	public static Page getBrowserFromPool(BrowserTypes browserType){
		
		logger.info("Checking if the browser exist in the pool");
		
		BlockingQueue<Page> pageQueue = pageMap.computeIfAbsent(browserType, b -> new LinkedBlockingQueue<Page>());
		
		Page page = pageQueue.poll();
		
		if(page == null) {
			
			logger.info("Creating a new browser");
			
			page = PwBrowserFactory.getBrowserFromFactory(browserType);
			
			logger.info("Got the new Browser : " + page.hashCode());
			
			pageBrowserMap.put(page, browserType);
			
		}else {
			
			logger.info("Re-using the browser " + page.hashCode());
		}
		
		return page;
		
	}
	
	public static void releaseBrowser(Page page) {
		
		BrowserTypes browserType = pageBrowserMap.get(page);
		
		if(browserType != null) {
		
			pageMap.computeIfAbsent(browserType, b -> new LinkedBlockingQueue<Page>()).offer(page);
			logger.info("Added the browser back to the queue");
		
		}else {
			logger.warning("Warning: BrowserType was incorrect. Closing the browser");
			page.close();
		}
		
	}
	
	public static void quitAllBrowsers() {
		
		for(Page page : pageBrowserMap.keySet()) {
			
			page.close();
			
		}
	}
	


}
