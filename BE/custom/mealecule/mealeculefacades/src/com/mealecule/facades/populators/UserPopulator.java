/**
 *
 */
package com.mealecule.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;

import com.mealecule.core.model.BadgeModel;
import com.mealecule.core.user.data.BadgeData;
import com.mealecule.core.user.data.UserData;


/**
 * @author prirawat5
 *
 */
public class UserPopulator implements Populator<UserModel, UserData>
{
	private Converter<BadgeModel, BadgeData> userBadgeConverter;

	@Override
	public void populate(final UserModel source, final UserData target) throws ConversionException
	{
		target.setUid(source.getUid());
		target.setCoins(source.getCoins());
		target.setPreferredMealecule(source.getPreferredMealecule());
		target.setBadges(userBadgeConverter.convertAll(source.getBadges()));
	}

	/**
	 * @return the userBadgeConverter
	 */
	public Converter<BadgeModel, BadgeData> getUserBadgeConverter()
	{
		return userBadgeConverter;
	}

	/**
	 * @param userBadgeConverter
	 *           the userBadgeConverter to set
	 */
	@Required
	public void setUserBadgeConverter(final Converter<BadgeModel, BadgeData> userBadgeConverter)
	{
		this.userBadgeConverter = userBadgeConverter;
	}
}
