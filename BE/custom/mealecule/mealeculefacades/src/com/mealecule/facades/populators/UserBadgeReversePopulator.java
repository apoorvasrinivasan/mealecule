/**
 *
 */
package com.mealecule.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.mealecule.core.enums.DisplayNameEnum;
import com.mealecule.core.enums.LevelEnum;
import com.mealecule.core.model.BadgeModel;
import com.mealecule.core.user.data.BadgeData;


/**
 * @author prirawat5
 *
 */
public class UserBadgeReversePopulator implements Populator<BadgeData, BadgeModel>
{

	@Override
	public void populate(final BadgeData source, final BadgeModel target) throws ConversionException
	{
		target.setId(source.getId());
		target.setDisplayName(DisplayNameEnum.valueOf(source.getDisplayName()));
		target.setLevel(LevelEnum.valueOf(source.getLevel()));
	}

}
