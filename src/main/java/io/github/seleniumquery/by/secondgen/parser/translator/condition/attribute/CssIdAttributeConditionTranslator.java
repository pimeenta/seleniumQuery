/*
 * Copyright (c) 2017 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.secondgen.parser.translator.condition.attribute;

import org.unbescape.java.JavaEscape;
import org.w3c.css.sac.AttributeCondition;

import io.github.seleniumquery.by.secondgen.csstree.condition.CssCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.AstCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.CssIdAttributeCondition;

/**
 * #id
 *
 * CASE SENSITIVE!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssIdAttributeConditionTranslator {

	public CssCondition translate(AttributeCondition attributeCondition) {
		String wantedId = attributeCondition.getValue();
		String escapedId = JavaEscape.unescapeJava(wantedId);
		return new CssIdAttributeCondition(new AstCssIdAttributeCondition(escapedId));
	}

}
