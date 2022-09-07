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
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;


/**
 * Populates {@link ProductData} with genders
 */
public class CartMealeculeQuotientPopulator implements Populator<AbstractOrderModel, AbstractOrderData>
{

	@Override
	public void populate(final AbstractOrderModel source, final AbstractOrderData target)
	{
		Double totalCartCarbohydrate = 0.0;
		Double totalCartProtein = 0.0;
		Double totalCartFat = 0.0;
		Double totalCartSugar = 0.0;
		Double totalCartFiber = 0.0;
		Double totalCartEnergy = 0.0;
		Double totalCartWater = 0.0;
		for (AbstractOrderEntryModel entry : source.getEntries()) {
			Double productWeight = null != entry.getProduct() && null != entry.getProduct().getWeightInG() ? entry.getProduct().getWeightInG() : entry.getProduct().getWeightInML();
			if (null != entry.getProduct().getMealeculeQuotient()) {
				List<MealeculeQuotientDataModel> list = entry.getProduct().getMealeculeQuotient().getMealeculeQuotientData();

				Double mqWeightInG = entry.getProduct().getMealeculeQuotient().getWeightInG();
				Double mqWeightInML = entry.getProduct().getMealeculeQuotient().getWeightInML();
				Double mqWeight = (null == mqWeightInG || 0.0 == mqWeightInG) ? mqWeightInML : mqWeightInG;
				Double considerWeight = (null == mqWeight || 0.0 == mqWeight) ? 100 : mqWeight;
				if (CollectionUtils.isNotEmpty(list)) {
					totalCartCarbohydrate = totalCartCarbohydrate + list.stream().filter(mq -> MealeculeQuotientEnum.CARBOHYDRATE.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartProtein = totalCartProtein + list.stream().filter(mq -> MealeculeQuotientEnum.PROTEIN.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartFat = totalCartFat + list.stream().filter(mq -> MealeculeQuotientEnum.FAT.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartSugar = totalCartSugar + list.stream().filter(mq -> MealeculeQuotientEnum.SUGAR.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartFiber = totalCartFiber + list.stream().filter(mq -> MealeculeQuotientEnum.FIBER.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartEnergy = totalCartEnergy + list.stream().filter(mq -> MealeculeQuotientEnum.CALORIES.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();
					totalCartWater = totalCartWater + list.stream().filter(mq -> MealeculeQuotientEnum.WATER.equals(mq.getMealeculeQuotientType())).findFirst().get().getValue() * productWeight / considerWeight * entry.getQuantity();

				}
			}
		}
		MealeculeQuotientData mealeculeQuotientData = new MealeculeQuotientData();
		if(CollectionUtils.isNotEmpty(source.getUser().getPreferredMealecule())){
			for (String preferredMealecule:source.getUser().getPreferredMealecule()) {
				if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.CARBOHYDRATE.getCode())){
					mealeculeQuotientData.setCarbohydrate(totalCartCarbohydrate);
				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.PROTEIN.getCode())){
					mealeculeQuotientData.setProtein(totalCartProtein);
				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.FAT.getCode())){
					mealeculeQuotientData.setFat(totalCartFat);
				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.SUGAR.getCode())){
					mealeculeQuotientData.setSugar(totalCartSugar);
				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.FIBER.getCode())){
					mealeculeQuotientData.setFiber(totalCartFiber);
				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.CALORIES.getCode())){
					mealeculeQuotientData.setEnergy(totalCartEnergy);
				} else if(preferredMealecule.equalsIgnoreCase(MealeculeQuotientEnum.WATER.getCode())){
					mealeculeQuotientData.setWater(totalCartWater);
				}
			}
		} else {

			mealeculeQuotientData.setCarbohydrate(totalCartCarbohydrate);
			mealeculeQuotientData.setProtein(totalCartProtein);
			mealeculeQuotientData.setFat(totalCartFat);
			mealeculeQuotientData.setSugar(totalCartSugar);
			mealeculeQuotientData.setFiber(totalCartFiber);
			mealeculeQuotientData.setEnergy(totalCartEnergy);
			mealeculeQuotientData.setWater(totalCartWater);
		}
		target.setMealeculeQuotientData(mealeculeQuotientData);

	}

}
