package com.testleaf.web.browser;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import com.testleaf.constants.BrowserTypes;
import com.testleaf.constants.LocatorType;
import com.testleaf.web.api.APIClient;
import com.testleaf.web.api.PwResponseImpl;
import com.testleaf.web.api.ResponseAPI;
import com.testleaf.web.element.*;

public class PwBrowserImpl implements Browser, APIClient {

    private Playwright playwright;
    private com.microsoft.playwright.Browser pwBrowser;
    private BrowserContext context;
    private Page page;

    public PwBrowserImpl(BrowserTypes browserType) {      
        page = PwBrowserPool.getBrowserFromPool(browserType);
    }
    

    @Override
    public void navigateTo(String url) {
        page.navigate(url);
    }


    @Override
    public void closeBrowser() {
    	
    	PwBrowserPool.quitAllBrowsers();
    }
    

	@Override
	public void releaseBrowser() {
		
		PwBrowserPool.releaseBrowser(page);
		
	}

    private String buildSelector(LocatorType locatorType, String locator) {
        switch(locatorType) {
            case ID:
                return "#" + locator;
            case NAME:
                return "[name='" + locator + "']";
            case CLASS:
                return "." + locator;
            case XPATH:
                return "xpath=" + locator;
            case LINKTEXT:
                return "a:has-text(\"" + locator + "\")";
            default:
                throw new IllegalArgumentException("Unsupported LocatorType: " + locatorType);
        }
    }
    
    @Override
    public Element locateElement(LocatorType locatorType, String locator) {
        return new PwElementImpl(page.locator(buildSelector(locatorType, locator)));
    }

    @Override
    public Button locateButton(LocatorType locatorType, String locator) {
        return new PwButtonImpl(page.locator(buildSelector(locatorType, locator)));
    }

    @Override
    public Edit locateEdit(LocatorType locatorType, String locator) {
        return new PwEditImpl(page.locator(buildSelector(locatorType, locator)));
    }
    
    @Override
    public Link locateLink(LocatorType locatorType, String locator) {
        return new PwLinkImpl(page.locator(buildSelector(locatorType, locator)));
    }

    @Override
    public Dropdown locateDropdown(LocatorType locatorType, String locator) {
        return new PwDropdownImpl(page.locator(buildSelector(locatorType,locator)));
    }

    @Override
	public void maximize() {
		
	}

	@Override
	public String getTitle() {
		return page.title();
	}


    @Override
    public ResponseAPI get(String endPoint) {
        APIResponse response = page.request().get(endPoint);
        System.out.println("Playwright Response");
        return new PwResponseImpl(response);
    }

    @Override
    public ResponseAPI post(String endPoint, Object body) {
        APIResponse response = page.request().post(endPoint, RequestOptions.create().setHeader("Content-Type","application/json").setData(body));
        return new PwResponseImpl(response);
    }

    @Override
    public ResponseAPI put(String endPoint, Object body) {
        APIResponse response = page.request().put(endPoint, RequestOptions.create().setHeader("Content-Type","application/json").setData(body));
        return new PwResponseImpl(response);
    }

    @Override
    public ResponseAPI delete(String endPoint) {
        APIResponse response = page.request().delete(endPoint);
        System.out.println("Playwright Response");
        return new PwResponseImpl(response);
    }

}

