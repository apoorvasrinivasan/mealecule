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

import de.hybris.platform.cmsfacades.data.ProductData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.mealecule.core.enums.MealeculeQuotientEnum;
import com.mealecule.core.model.MealeculeQuotientDataModel;
import com.mealecule.facades.product.data.MealeculeQuotientData;


/**
 * Populates {@link ProductData} with genders
 */
public class OrderMealeculeQuotientHistoryPopulator implements Populator<OrderModel, OrderHistoryData>
{

	@Override
	public void populate(final OrderModel source, final OrderHistoryData target) throws ConversionException
	{
		Double totalCartCarbohydrate = 0.0;
		Double totalCartProtein = 0.0;
		Double totalCartFat = 0.0;
		Double totalCartSugar = 0.0;
		Double totalCartFiber = 0.0;
		Double totalCartEnergy = 0.0;
		Double totalCartWater = 0.0;
		for (final AbstractOrderEntryModel entry : source.getEntries())
		{
			final Double productWeight = null != entry.getProduct() && null != entry.getProduct().getWeightInG()
					? entry.getProduct().getWeightInG()
					: entry.getProduct().getWeightInML();
			if (null != entry.getProduct().getMealeculeQuotient())
			{
				final List<MealeculeQuotientDataModel> list = entry.getProduct().getMealeculeQuotient().getMealeculeQuotientData();

				final Double mqWeightInG = entry.getProduct().getMealeculeQuotient().getWeightInG();
				final Double mqWeightInML = entry.getProduct().getMealeculeQuotient().getWeightInML();
				final Double mqWeight = (null == mqWeightInG || 0.0 == mqWeightInG) ? mqWeightInML : mqWeightInG;
				final Double considerWeight = (null == mqWeight || 0.0 == mqWeight) ? 100 : mqWeight;
				if (CollectionUtils.isNotEmpty(list))
				{
					totalCartCarbohydrate = totalCartCarbohydrate
							+ list.stream().filter(mq -> MealeculeQuotientEnum.CARBOHYDRATE.equals(mq.getMealeculeQuotientType()))
									.findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartProtein = totalCartProtein
							+ list.stream().filter(mq -> MealeculeQuotientEnum.PROTEIN.equals(mq.getMealeculeQuotientType())).findFirst()
									.get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartFat = totalCartFat
							+ list.stream().filter(mq -> MealeculeQuotientEnum.FAT.equals(mq.getMealeculeQuotientType())).findFirst()
									.get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartSugar = totalCartSugar
							+ list.stream().filter(mq -> MealeculeQuotientEnum.SUGAR.equals(mq.getMealeculeQuotientType())).findFirst()
									.get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartFiber = totalCartFiber
							+ list.stream().filter(mq -> MealeculeQuotientEnum.FIBER.equals(mq.getMealeculeQuotientType())).findFirst()
									.get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartEnergy = totalCartEnergy
							+ list.stream().filter(mq -> MealeculeQuotientEnum.CALORIES.equals(mq.getMealeculeQuotientType()))
									.findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartWater = totalCartWater
							+ list.stream().filter(mq -> MealeculeQuotientEnum.WATER.equals(mq.getMealeculeQuotientType())).findFirst()
									.get().getValue() * productWeight / considerWeight * entry.getQuantity();

				}
			}
		}

		final MealeculeQuotientData mealeculeQuotientData = new MealeculeQuotientData();
		//		if(CollectionUtils.isNotEmpty(source.getUser().getPreferredMealecule())){
		//			for (final String preferredMealecule:source.getUser().getPreferredMealecule()) {
		//				if (preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.CARBOHYDRATE.getCode()))
		//				{
		//					mealeculeQuotientData.setCarbohydrate(totalCartCarbohydrate);
		//				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.PROTEIN.getCode())){
		//					mealeculeQuotientData.setProtein(totalCartProtein);
		//				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.FAT.getCode())){
		//					mealeculeQuotientData.setFat(totalCartFat);
		//				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.SUGAR.getCode())){
		//					mealeculeQuotientData.setSugar(totalCartSugar);
		//				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.FIBER.getCode())){
		//					mealeculeQuotientData.setFiber(totalCartFiber);
		//				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.CALORIES.getCode())){
		//					mealeculeQuotientData.setEnergy(totalCartEnergy);
		//				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.WATER.getCode())){
		//					mealeculeQuotientData.setWater(totalCartWater);
		//				}
		//			}
		//		} else {

		mealeculeQuotientData.setCarbohydrate(totalCartCarbohydrate);
		mealeculeQuotientData.setProtein(totalCartProtein);
		mealeculeQuotientData.setFat(totalCartFat);
		mealeculeQuotientData.setSugar(totalCartSugar);
		mealeculeQuotientData.setFiber(totalCartFiber);
		mealeculeQuotientData.setEnergy(totalCartEnergy);
		mealeculeQuotientData.setWater(totalCartWater);
		//		}
		target.setMealeculeQuotientData(mealeculeQuotientData);
	}

}
