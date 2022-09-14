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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
			final Double mqWeightInG = mq.getWeightInG();
			final Double mqWeightInML = mq.getWeightInML();
			final Double mqWeight = (null == mqWeightInG || 0.0 == mqWeightInG) ? mqWeightInML : mqWeightInG;
			Double considerWeight = (null == mqWeight || 0.0 == mqWeight) ? 100 : mqWeight;
			List<MealeculeQuotientDataModel> mealeculeQuotientDatas = mq.getMealeculeQuotientData().stream().collect(Collectors.toList());
			if(CollectionUtils.isNotEmpty(mealeculeQuotientDatas)){
				for (MealeculeQuotientDataModel mealeculeQuotientDataModel: mealeculeQuotientDatas) {
					MealeculeQuotientEnum mealeculeQuotientType = mealeculeQuotientDataModel.getMealeculeQuotientType();
					if (MealeculeQuotientEnum.CARBOHYDRATE.equals(mealeculeQuotientType)) {
						Double carbs = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setCarbohydrate(carbs);
					} else if (MealeculeQuotientEnum.FAT.equals(mealeculeQuotientType)) {
						Double fat = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setFat(fat);
					} else if (MealeculeQuotientEnum.PROTEIN.equals(mealeculeQuotientType)) {
						Double protein = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setProtein(protein);
					} else if (MealeculeQuotientEnum.FIBER.equals(mealeculeQuotientType)) {
						Double fiber = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setFiber(fiber);
					} else if (MealeculeQuotientEnum.SUGAR.equals(mealeculeQuotientType)) {
						Double sugar = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setSugar(sugar);
					} else if (MealeculeQuotientEnum.CALORIES.equals(mealeculeQuotientType)) {
						Double calories = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setCalories(calories);
					} else if (MealeculeQuotientEnum.WATER.equals(mealeculeQuotientType)) {
						Double water = getMealeculeValueInDouble(considerWeight, mealeculeQuotientDataModel);
						quotientData.setWater(water);
					}
				}
			}
			target.setMealeculeQuotientData(quotientData);
			target.setWeightInG(source.getWeightInG());
			target.setWeightInML(source.getWeightInML());
			target.setTotalCalories(source.getTotalCalories());
			target.setManufacturer(source.getManufacturerName());
			if(CollectionUtils.isNotEmpty(source.getDetail())) {
				target.setImageURL(source.getDetail().stream().findFirst().get().getURL());
			}
		}
	}

	private static Double getMealeculeValueInDouble(Double considerWeight, MealeculeQuotientDataModel mealeculeQuotientDataModel) {
		Double value = mealeculeQuotientDataModel.getValue() * 100 / considerWeight;
		Double roundedValue = new BigDecimal(value).setScale(1, RoundingMode.HALF_UP).doubleValue();
		return roundedValue;
	}

}
