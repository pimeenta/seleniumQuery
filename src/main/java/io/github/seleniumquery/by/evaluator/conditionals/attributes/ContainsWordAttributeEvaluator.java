package io.github.seleniumquery.by.evaluator.conditionals.attributes;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import java.util.Map;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.evaluator.SelectorUtils;
import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class ContainsWordAttributeEvaluator implements CSSCondition<AttributeCondition> {

	private static final ContainsWordAttributeEvaluator instance = new ContainsWordAttributeEvaluator();
	public static ContainsWordAttributeEvaluator getInstance() {
		return instance;
	}
	private ContainsWordAttributeEvaluator() { }
	

	public static final String CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL = "~=";
	
	/**
	 * @see {@link org.w3c.css.sac.Condition#SAC_ONE_OF_ATTRIBUTE_CONDITION}
	 * 
	 * This condition checks for a value in a space-separated values in a specified attribute example:
	 * [values~="10"]
	 * 
	 * Case INsensitive!
	 */
	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		if (!SelectorUtils.hasAttribute(element, attributeName)) {
			return false;
		}
		String wantedValue = attributeCondition.getValue();
		String attributeValue = element.getAttribute(attributeName);
		String[] values = attributeValue.split(" ");
		for (String value : values) {
			if (equalsIgnoreCase(value, wantedValue)){
				return true;
			}
		}
		return false;
	}

	@Override
	public CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector simpleSelector, AttributeCondition attributeCondition) {
		// nothing to do, everyone supports this selector
		return AttributeEvaluatorUtils.createAttributeNoFilterCompiledSelector(attributeCondition,
				CONTAINS_WORD_ATTRIBUTE_SELECTOR_SYMBOL);
	}

}