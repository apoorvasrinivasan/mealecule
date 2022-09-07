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

import com.mealecule.core.enums.MealeculeQuotientEnum;
import com.mealecule.core.model.MealeculeQuotientDataModel;
import com.mealecule.core.model.MealeculeQuotientModel;
import com.mealecule.facades.product.data.MealeculeQuotientData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
			quotientData.setWeightInG(mq.getWeightInG());
			quotientData.setWeightInML(mq.getWeightInML());
			List<MealeculeQuotientDataModel> mealeculeQuotientDatas = mq.getMealeculeQuotientData().stream().collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(mealeculeQuotientDatas)){
				for (MealeculeQuotientDataModel mealeculeQuotientDataModel: mealeculeQuotientDatas) {
					MealeculeQuotientEnum mealeculeQuotientType = mealeculeQuotientDataModel.getMealeculeQuotientType();
					if (MealeculeQuotientEnum.CARBOHYDRATE.equals(mealeculeQuotientType)) {
						quotientData.setCarbohydrate(mealeculeQuotientDataModel.getValue());
					} else if (MealeculeQuotientEnum.FAT.equals(mealeculeQuotientType)) {
						quotientData.setFat(mealeculeQuotientDataModel.getValue());
					} else if (MealeculeQuotientEnum.PROTEIN.equals(mealeculeQuotientType)) {
						quotientData.setProtein(mealeculeQuotientDataModel.getValue());
					} else if (MealeculeQuotientEnum.FIBER.equals(mealeculeQuotientType)) {
						quotientData.setFiber(mealeculeQuotientDataModel.getValue());
					} else if (MealeculeQuotientEnum.SUGAR.equals(mealeculeQuotientType)) {
						quotientData.setSugar(mealeculeQuotientDataModel.getValue());
					} else if (MealeculeQuotientEnum.CALORIES.equals(mealeculeQuotientType)) {
						quotientData.setEnergy(mealeculeQuotientDataModel.getValue());
					} else if (MealeculeQuotientEnum.WATER.equals(mealeculeQuotientType)) {
						quotientData.setWater(mealeculeQuotientDataModel.getValue());
					}
				}
			}
			target.setMealeculeQuotientData(quotientData);
			target.setWeightInG(source.getWeightInG());
			target.setWeightInML(source.getWeightInML());
			target.setTotalCalories(source.getTotalCalories());
			target.setManufacturer(source.getManufacturerName());
		}
	}

}
