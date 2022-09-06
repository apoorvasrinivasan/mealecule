package com.mealecule.facades.customer.impl;

import com.mealecule.core.user.data.CustomerGameData;
import com.mealecule.core.user.data.PreferredMealeculeData;
import com.mealecule.facades.customer.MealeculeCustomerFacade;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.Arrays;


/**
 * Default implementation for the {@link com.mealecule.facades.customer.MealeculeCustomerFacade}.
 */
public class DefaultMealeculeCustomerFacade extends DefaultCustomerFacade implements MealeculeCustomerFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultMealeculeCustomerFacade.class);

	private Converter<CustomerData, Object> userBadgeReverseConverter;

	@Override
	public CustomerGameData updateCustomerGameData(Integer coins, UserModel userModel) {
		final CustomerData userData = new CustomerData();
		CustomerGameData customerGameData = new CustomerGameData();
		customerGameData.setCoins(coins);
		userData.setGameData(customerGameData);
		getUserBadgeReverseConverter().convert(userData, userModel);
		return userData.getGameData();
	}

	@Override
	public PreferredMealeculeData updatePreferredMealecule(String preferredMealecule, String maxMealecule, String minMealecule, UserModel userModel, CustomerData userData) {
		userModel.setPreferredMealecule(
				StringUtils.isNotBlank(preferredMealecule) ? Arrays.asList(preferredMealecule.split(",")) : null);
		final PreferredMealeculeData mealeculeData = new PreferredMealeculeData();
		mealeculeData.setPreferredMealecule(userData.getPreferredMealecule());
		if(StringUtils.isNotBlank(maxMealecule)){
			userModel.setMaxMealecule(maxMealecule);
			mealeculeData.setMaxMealecule(userModel.getMaxMealecule());
		}
		if(StringUtils.isNotBlank(minMealecule)){
			userModel.setMinMealecule(minMealecule);
			mealeculeData.setMinMealecule(userModel.getMinMealecule());
		}

		getModelService().save(userModel);
		return mealeculeData;
	}


	public Converter<CustomerData, Object> getUserBadgeReverseConverter() {
		return userBadgeReverseConverter;
	}

	@Required
	public void setUserBadgeReverseConverter(Converter<CustomerData, Object> userBadgeReverseConverter) {
		this.userBadgeReverseConverter = userBadgeReverseConverter;
	}
}
