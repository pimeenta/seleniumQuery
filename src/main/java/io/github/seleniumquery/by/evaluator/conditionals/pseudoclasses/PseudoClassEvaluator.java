package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import io.github.seleniumquery.by.evaluator.CSSCondition;
import io.github.seleniumquery.by.selector.CompiledSelector;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.AttributeCondition;
import org.w3c.css.sac.Selector;

public class PseudoClassEvaluator implements CSSCondition<AttributeCondition> {

	private static final PseudoClassEvaluator instance = new PseudoClassEvaluator();
	public static PseudoClassEvaluator getInstance() {
		return instance;
	}
	private PseudoClassEvaluator() { }
	
	private final List<PseudoClass> pseudoClasses = Arrays.asList(CheckedPseudoClass.getInstance(),
			SelectedPseudoClass.getInstance(), EqPseudoClass.getInstance(), OnlyChildPseudoClass.getInstance(),
			ContainsPseudoClass.getInstance(), FirstChildPseudoClass.getInstance(), NotPseudoClass.getInstance(),
			OnlyOfTypePseudoClass.getInstance(), RootPseudoClass.getInstance(), PresentPseudoClass.getInstance(),
			EnabledPseudoClass.getInstance(), DisabledPseudoClass.getInstance(), VisiblePseudoClass.getInstance(),
			HiddenPseudoClass.getInstance(), FirstPseudoClass.getInstance(), LastPseudoClass.getInstance(),
			CheckboxPseudoClass.getInstance(), RadioPseudoClass.getInstance(), ImagePseudoClass.getInstance(),
			PasswordPseudoClass.getInstance(), FilePseudoClass.getInstance(), SubmitPseudoClass.getInstance(),
			ResetPseudoClass.getInstance(), ButtonPseudoClass.getInstance(), InputPseudoClass.getInstance(),
			HeaderPseudoClass.getInstance(), LtPseudoClass.getInstance(), GtPseudoClass.getInstance(),
			FocusPseudoClass.getInstance(), FocusablePseudoClass.getInstance(), TabbablePseudoClass.getInstance(),
			HasPseudoClass.getInstance(), LangPseudoClass.getInstance());

	@Override
	public boolean isCondition(WebDriver driver, WebElement element, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		String pseudoClassValue = attributeCondition.getValue();
		for (PseudoClass pseudoClass : pseudoClasses) {
			if (pseudoClass.isApplicable(pseudoClassValue)) {
				return pseudoClass.isPseudoClass(driver, element, new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassValue));
			}
		}
		System.err.println("Warning: Unsupported pseudo-class: " + pseudoClassValue);
		return false;
	}
	
	@Override
	public CompiledSelector compileCondition(WebDriver driver, Map<String, String> stringMap, Selector selectorUpToThisPoint, AttributeCondition attributeCondition) {
		
		String pseudoClassValue = attributeCondition.getValue();
		for (PseudoClass pseudoClass : pseudoClasses) {
			if (pseudoClass.isApplicable(pseudoClassValue)) {
				return pseudoClass.compilePseudoClass(driver, new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassValue));
			}
		}
		System.err.println("Warning: Unsupported pseudo-class: " + pseudoClassValue);
		return new CompiledSelector(":"+attributeCondition.toString(), "Unsupported pseudo-class!!!");
	}

}