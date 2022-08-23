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
package com.mealecule.facades.populators;

import com.mealecule.core.model.MealeculeQuotientModel;
import com.mealecule.facades.product.data.MealeculeQuotientData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;


/**
 * Populates {@link ProductData} with genders
 */
public class ProductMealeculeQuotientPopulator implements Populator<ProductModel, ProductData>
{

	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{

		if (null != source.getMealeculeQuotient())
		{
			MealeculeQuotientModel mq = source.getMealeculeQuotient();
			MealeculeQuotientData quotientData = new MealeculeQuotientData();
			quotientData.setCode(mq.getCode());
			quotientData.setCarbohydrate(mq.getCarbohydrate());
			quotientData.setWeightInG(mq.getWeightInG());
			quotientData.setWeightInML(mq.getWeightInML());
			quotientData.setProtein(mq.getProtein());
			quotientData.setFat(mq.getFat());
			quotientData.setFiber(mq.getFiber());
			quotientData.setSugar(mq.getSugar());
			quotientData.setWater(mq.getWater());
			quotientData.setEnergy(mq.getEnergy());
			quotientData.setNitrogen(mq.getNitrogen());
			target.setMealeculeQuotientData(quotientData);
			target.setWeightInG(source.getWeightInG());
			target.setWeightInML(source.getWeightInML());
			target.setTotalCalories(source.getTotalCalories());
		}
	}

}
