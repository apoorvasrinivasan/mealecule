/**
 *
 */
package com.mealecule.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.ruleengineservices.rao.UserRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.mealecule.core.model.BadgeModel;


/**
 * @author prirawat5
 *
 */
public class UserRaoPopulator implements Populator<UserModel, UserRAO>
{

	@Override
	public void populate(final UserModel source, final UserRAO target) throws ConversionException
	{
		final BadgeModel badge = source.getBadge();
		if (badge != null)
		{
			target.setUserBadgeLevel(badge.getLevel().toString());
			target.setUserBadgeStatus(badge.getStatus().toString());
		}

	}

}
