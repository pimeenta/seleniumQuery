package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import java.util.Map;

import org.w3c.css.sac.Selector;

public class PseudoClassSelector {
	
	private Map<String, String> stringMap;
	private Selector selectorThisConditionShouldApply;
	private String pseudoClassValue;

	public PseudoClassSelector(Map<String, String> stringMap, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		this.stringMap = stringMap;
		this.selectorThisConditionShouldApply = selectorThisConditionShouldApply;
		this.pseudoClassValue = pseudoClassValue;
	}

	public String getPseudoClassContent() {
		String index = pseudoClassValue.substring(pseudoClassValue.indexOf('(')+1, pseudoClassValue.length()-1);
		System.out.println("pseudoClassValue: "+pseudoClassValue);
		return this.stringMap.get(index);
	}

	/**
	 * Represents the selector this pseudo class condition should apply to.
	 * 
	 * In other words, the selector up to the point of this pseudo class ---> #i.mean.this.selector:before-this-pseudo
	 */
	public Selector getSelector() {
		return selectorThisConditionShouldApply;
	}

	public Map<String, String> getStringMap() {
		return stringMap;
	}

}