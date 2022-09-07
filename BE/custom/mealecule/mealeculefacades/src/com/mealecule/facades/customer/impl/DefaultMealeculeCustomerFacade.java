package com.mealecule.facades.customer.impl;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.mealecule.core.user.data.CustomerGameData;
import com.mealecule.core.user.data.PreferredMealeculeData;
import com.mealecule.facades.customer.MealeculeCustomerFacade;


/**
 * Default implementation for the {@link com.mealecule.facades.customer.MealeculeCustomerFacade}.
 */
public class DefaultMealeculeCustomerFacade extends DefaultCustomerFacade implements MealeculeCustomerFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultMealeculeCustomerFacade.class);

	private Converter<CustomerData, Object> userBadgeReverseConverter;

	@Override
	public PreferredMealeculeData updatePreferredMealecule(final String preferredMealecule, final String maxMealecule, final String minMealecule, final UserModel userModel, final CustomerData userData) {
		userModel.setPreferredMealecule(
				StringUtils.isNotBlank(preferredMealecule) ? Arrays.asList(preferredMealecule.split(",")) : null);
		final PreferredMealeculeData mealeculeData = new PreferredMealeculeData();

		if(StringUtils.isNotBlank(maxMealecule)){
			userModel.setMaxMealecule(maxMealecule);
			mealeculeData.setMaxMealecule(maxMealecule);
		}
		if(StringUtils.isNotBlank(minMealecule)){
			userModel.setMinMealecule(minMealecule);
			mealeculeData.setMinMealecule(minMealecule);
		}
		mealeculeData.setPreferredMealecule(StringUtils.isNotBlank(preferredMealecule) ? Arrays.asList(preferredMealecule.split(",")) : null);

		getModelService().save(userModel);
		return mealeculeData;
	}


	public Converter<CustomerData, Object> getUserBadgeReverseConverter() {
		return userBadgeReverseConverter;
	}

	@Required
	public void setUserBadgeReverseConverter(final Converter<CustomerData, Object> userBadgeReverseConverter) {
		this.userBadgeReverseConverter = userBadgeReverseConverter;
	}


	@Override
	public CustomerGameData updateCustomerGameData(final Integer coins, final Integer height, final Integer weight,
			final Integer age, final UserModel userModel)
	{
		final CustomerData userData = new CustomerData();
		final CustomerGameData customerGameData = new CustomerGameData();
		if (null != coins)
		{
			customerGameData.setCoins(coins);
		}

		if (null != height)
		{
			if (height.intValue() != 0)
			{
				customerGameData.setHeight(height);
			}
		}

		if (null != weight)
		{
			if (weight.intValue() != 0)
			{
				customerGameData.setWeight(weight);
			}
		}

		if (null != age)
		{
			if (age.intValue() != 0)
			{
				customerGameData.setAge(age);
			}
		}

		userData.setGameData(customerGameData);
		getUserBadgeReverseConverter().convert(userData, userModel);
		return userData.getGameData();
	}

}
