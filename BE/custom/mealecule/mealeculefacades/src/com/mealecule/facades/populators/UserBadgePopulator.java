/**
 *
 */
package com.mealecule.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.mealecule.core.model.BadgeModel;
import com.mealecule.core.user.data.BadgeData;


/**
 * @author prirawat5
 *
 */
public class UserBadgePopulator implements Populator<BadgeModel, BadgeData>
{

	@Override
	public void populate(final BadgeModel source, final BadgeData target) throws ConversionException
	{
		target.setId(source.getId());
		if(null != source.getLevel()) {
			target.setLevel(source.getLevel().getCode());
		}
		if(null != source.getDisplayName()) {
			target.setDisplayName(source.getDisplayName().getCode());
		}
	}

}
