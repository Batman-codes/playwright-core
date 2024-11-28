package com.testleaf.web.element;

import com.microsoft.playwright.Locator;

public class PwEditImpl extends PwElementImpl implements Edit {

    public PwEditImpl(Locator locator) {
        super(locator);
    }

    @Override
    public void type(String text) {
        locator.fill(text);
    }

    @Override
    public String getValue() {
        return locator.inputValue();
    }

    @Override
    public void clear() {
        locator.fill("");
    }
}
