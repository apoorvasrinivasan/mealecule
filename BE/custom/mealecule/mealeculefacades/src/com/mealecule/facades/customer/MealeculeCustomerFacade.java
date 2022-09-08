/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.mealecule.facades.customer;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.UserModel;

import com.mealecule.core.user.data.CustomerGameData;
import com.mealecule.core.user.data.PreferredMealeculeData;


/**
 * Defines an API to perform various customer related operations
 */
public interface MealeculeCustomerFacade extends CustomerFacade
{
	CustomerGameData updateCustomerGameData(Integer coins, Double height, Double weight, Integer age, UserModel userModel);

	PreferredMealeculeData updatePreferredMealecule(String preferredMealecule, String maxMealecule, String minMealecule, UserModel userModel, CustomerData userData);
}
