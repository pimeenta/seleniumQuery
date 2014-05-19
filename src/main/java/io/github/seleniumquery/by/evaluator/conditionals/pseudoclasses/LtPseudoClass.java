package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SeleniumQueryCssCompiler;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LtPseudoClass implements PseudoClass {

	private static final LtPseudoClass instance = new LtPseudoClass();
	public static LtPseudoClass getInstance() {
		return instance;
	}
	private LtPseudoClass() { }

	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return pseudoClassValue.matches("lt\\(.*\\)");
	}

	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector) {
		String ltIndex = pseudoClassSelector.getPseudoClassContent();
		if (!ltIndex.matches("[+-]?\\d+")) {
			throw new RuntimeException("The :lt() pseudo-class requires an integer but got: " + ltIndex);
		}
		if (ltIndex.charAt(0) == '+') {
			ltIndex = ltIndex.substring(1);
		}
		int index = Integer.valueOf(ltIndex);
		
		return LtPseudoClass.isLt(driver, element, pseudoClassSelector, index);
	}
	
	private static boolean isLt(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector, int wantedIndex) {
		if (wantedIndex == 0) {
			return false;
		}
		CompiledSelector compileSelector = SeleniumQueryCssCompiler.compileSelector(driver, pseudoClassSelector.getStringMap(), pseudoClassSelector.getSelector());
		List<WebElement> elements = compileSelector.execute(driver);
		if (elements.isEmpty()) {
			return false;
		}
		int actuallyWantedIndex = wantedIndex;
		if (wantedIndex < 0) {
			actuallyWantedIndex = elements.size() + wantedIndex;
		}
		
		if (elements.size() <= actuallyWantedIndex) {
			return true;
		}
		int indexFound = elements.indexOf(element);
		if (indexFound == -1) {
			return false;
		}
		return indexFound < actuallyWantedIndex;
	}

	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, PseudoClassSelector pseudoClassSelector) {
		// :lt() is an extension selector, no browser implements it natively
		SqCSSFilter ltPseudoClassFilter = new PseudoClassFilter(getInstance(), pseudoClassSelector);
		return CompiledSelector.createFilterOnlySelector(ltPseudoClassFilter);
	}

}