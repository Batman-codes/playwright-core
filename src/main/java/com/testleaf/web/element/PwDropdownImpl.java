package com.testleaf.web.element;

import com.microsoft.playwright.Locator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PwDropdownImpl extends PwElementImpl implements Dropdown{


    public PwDropdownImpl(Locator locator) {
        super(locator);
    }

    @Override
    public void selectOptionByVisibleText(String s) {
        locator.selectOption(s);
    }

    @Override
    public void selectOptionByValue(String s) {
        locator.selectOption(s);
    }

    @Override
    public void selectOptionByIndex(int i) {
        List<Locator> listLocator = locator.locator("option").all();

        Locator indexLocator = listLocator.get(i);
        locator.selectOption(indexLocator.getAttribute("value"));
    }

    @Override
    public List<Element> getAllOptions() {

        List<Locator> listLocator = locator.locator("option").all();
        List<Element> elementList = new ArrayList<>();
        for(Locator l : listLocator){
            elementList.add(new PwElementImpl(locator));
        }
        return elementList;
    }

    @Override
    public boolean isMultiselect() {
        return false;
    }
}
