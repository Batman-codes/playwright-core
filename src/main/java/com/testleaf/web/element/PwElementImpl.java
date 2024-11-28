package com.testleaf.web.element;

import com.microsoft.playwright.Locator;

public class PwElementImpl implements Element {

	protected Locator locator;

    public PwElementImpl(Locator locator) {
        this.locator = locator;
    }

    @Override
    public boolean isEnabled() {
        return locator.isEnabled();
    }

    @Override
    public boolean isVisible() {
        return locator.isVisible();
    }

    @Override
    public String getAttribute(String attribute) {
        return locator.getAttribute(attribute);
    }

    @Override
    public String getText() {
        return locator.textContent();
    }


}
