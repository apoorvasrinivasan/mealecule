/**
 *
 */
package com.mealecule.facades.definitions.conditions;

import de.hybris.platform.ruleengineservices.compiler.RuleCompilerContext;
import de.hybris.platform.ruleengineservices.compiler.RuleConditionTranslator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrAttributeOperator;
import de.hybris.platform.ruleengineservices.compiler.RuleIrCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrFalseCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupCondition;
import de.hybris.platform.ruleengineservices.compiler.RuleIrGroupOperator;
import de.hybris.platform.ruleengineservices.rao.UserRAO;
import de.hybris.platform.ruleengineservices.rule.data.RuleConditionData;
import de.hybris.platform.ruleengineservices.rule.data.RuleConditionDefinitionData;
import de.hybris.platform.ruleengineservices.rule.data.RuleParameterData;

import java.util.ArrayList;
import java.util.List;

import com.mealecule.core.enums.LevelEnum;
import com.mealecule.core.enums.StatusEnum;
/**
 * @author prirawat5
 *
 */
public class RuleBronzeBadgeTranslator implements RuleConditionTranslator
{

	public static final String USER_BADGE_LEVEL = "userBadgeLevel";
	public static final String USER_BADGE_STATUS = "userBadgeStatus";

	public static final String bronzeLevel = LevelEnum.BRONZE.toString();
	public static final String activeStatus = StatusEnum.ACTIVE.toString();

	@Override
	public RuleIrCondition translate(final RuleCompilerContext context, final RuleConditionData condition,
			final RuleConditionDefinitionData arg2)
	{
		final RuleParameterData parameterData = condition.getParameters().get(USER_BADGE_LEVEL);

		if (parameterData != null)
		{
			final String userRaoVariable = context.generateVariable(UserRAO.class);

			final List<RuleIrCondition> irConditions = new ArrayList<>();

			final RuleIrAttributeCondition ruleIrAttributeCondition = new RuleIrAttributeCondition();
			ruleIrAttributeCondition.setVariable(userRaoVariable);
			ruleIrAttributeCondition.setOperator(RuleIrAttributeOperator.EQUAL);
			ruleIrAttributeCondition.setAttribute(USER_BADGE_LEVEL);
			ruleIrAttributeCondition.setValue(bronzeLevel);

			final RuleIrAttributeCondition ruleIrAttributeCondition2 = new RuleIrAttributeCondition();
			ruleIrAttributeCondition2.setVariable(userRaoVariable);
			ruleIrAttributeCondition2.setOperator(RuleIrAttributeOperator.EQUAL);
			ruleIrAttributeCondition2.setAttribute(USER_BADGE_STATUS);
			ruleIrAttributeCondition2.setValue(activeStatus);

			irConditions.add(ruleIrAttributeCondition);
			irConditions.add(ruleIrAttributeCondition2);

			final RuleIrGroupCondition irGoldBadgeCondition = new RuleIrGroupCondition();
			irGoldBadgeCondition.setOperator(RuleIrGroupOperator.AND);
			irGoldBadgeCondition.setChildren(irConditions);
			return irGoldBadgeCondition;
		}

		return new RuleIrFalseCondition();
	}

}
