package com.testleaf.web.element;

import com.microsoft.playwright.Locator;

public class PwButtonImpl extends PwElementImpl implements Button {

    public PwButtonImpl(Locator locator) {
        super(locator);
    }

    @Override
    public void click() {
        locator.click();
    }
}