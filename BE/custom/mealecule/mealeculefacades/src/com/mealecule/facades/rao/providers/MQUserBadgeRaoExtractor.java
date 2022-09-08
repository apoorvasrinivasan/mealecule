/**
 *
 */
package com.mealecule.facades.rao.providers;

import de.hybris.platform.ruleengineservices.rao.CartRAO;
import de.hybris.platform.ruleengineservices.rao.providers.RAOFactsExtractor;

import java.util.HashSet;
import java.util.Set;


/**
 * @author prirawat5
 *
 */
public class MQUserBadgeRaoExtractor implements RAOFactsExtractor
{

	public static final String EXPAND_USER_BADGES = "EXPAND_USER_BADGES";


	@Override
	public Set expandFact(final Object fact)
	{
		final Set<Object> facts = new HashSet<>();
		if (fact instanceof CartRAO)
		{
			final CartRAO cartRAO = (CartRAO) fact;
			if (cartRAO.getUser() != null)
			{
				facts.add(cartRAO.getUser().getUserBadgeLevel());
				facts.add(cartRAO.getUser().getUserBadgeStatus());
			}
		}
		return facts;
	}

	@Override
	public String getTriggeringOption()
	{
		return EXPAND_USER_BADGES;
	}

	@Override
	public boolean isDefault()
	{
		// YTODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMinOption()
	{
		// YTODO Auto-generated method stub
		return false;
	}

}
