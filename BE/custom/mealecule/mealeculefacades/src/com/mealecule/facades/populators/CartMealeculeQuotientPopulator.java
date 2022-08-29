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

import com.mealecule.facades.product.data.MealeculeQuotientData;
import de.hybris.platform.commercefacades.order.converters.populator.CartPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;


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
		for (OrderEntryData entry : target.getEntries()) {
			Double productWeight = null != entry.getProduct() && null != entry.getProduct().getWeightInG() ? entry.getProduct().getWeightInG() : entry.getProduct().getWeightInML();
			totalCartCarbohydrate = totalCartCarbohydrate + entry.getProduct().getMealeculeQuotientData().getCarbohydrate() * productWeight / 100 * entry.getQuantity();
			totalCartProtein = totalCartProtein + entry.getProduct().getMealeculeQuotientData().getProtein() * productWeight / 100 * entry.getQuantity();
			totalCartFat = totalCartFat + entry.getProduct().getMealeculeQuotientData().getFat() * productWeight / 100 * entry.getQuantity();
			totalCartSugar = totalCartSugar + entry.getProduct().getMealeculeQuotientData().getSugar() * productWeight / 100 * entry.getQuantity();
			totalCartFiber = totalCartFiber + entry.getProduct().getMealeculeQuotientData().getFiber() * productWeight / 100 * entry.getQuantity();
			totalCartEnergy = totalCartEnergy + entry.getProduct().getMealeculeQuotientData().getEnergy() * productWeight / 100 * entry.getQuantity();
			totalCartWater = totalCartWater + entry.getProduct().getMealeculeQuotientData().getWater() * productWeight / 100 * entry.getQuantity();
		}
		MealeculeQuotientData mealeculeQuotientData = new MealeculeQuotientData();
		mealeculeQuotientData.setCarbohydrate(totalCartCarbohydrate);
		mealeculeQuotientData.setProtein(totalCartProtein);
		mealeculeQuotientData.setFat(totalCartFat);
		mealeculeQuotientData.setSugar(totalCartSugar);
		mealeculeQuotientData.setFiber(totalCartFiber);
		mealeculeQuotientData.setEnergy(totalCartEnergy);
		mealeculeQuotientData.setWater(totalCartWater);
		target.setMealeculeQuotientData(mealeculeQuotientData);

	}

}
