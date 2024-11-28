package com.testleaf.web.element;

import com.microsoft.playwright.Locator;

public class PwLinkImpl extends PwElementImpl implements Link {

    public PwLinkImpl(Locator locator) {
        super(locator);
    }

    @Override
    public void click() {
        locator.click();
    }

    @Override
    public String getHref() {
        return locator.getAttribute("href");
    }

    @Override
    public String getText() {
        return locator.textContent();
    }
}
