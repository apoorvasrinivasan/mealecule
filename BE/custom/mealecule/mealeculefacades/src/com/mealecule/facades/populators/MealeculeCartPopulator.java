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
import de.hybris.platform.commercefacades.order.converters.populator.CartPopulator;
import de.hybris.platform.commercefacades.order.converters.populator.ExtendedCartPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Populates {@link ProductData} with genders
 */
public class MealeculeCartPopulator extends ExtendedCartPopulator
{
	private CartMealeculeQuotientPopulator cartMealeculePopulator;
	@Override
	public void populate(final CartModel source, final CartData target)
	{
		super.populate(source,target);
		addMealeculeDetails(source,target);

	}
	protected void addMealeculeDetails(final AbstractOrderModel source, final AbstractOrderData target)
	{
		getCartMealeculePopulator().populate(source, target);
	}

	public CartMealeculeQuotientPopulator getCartMealeculePopulator() {
		return cartMealeculePopulator;
	}

	@Required
	public void setCartMealeculePopulator(CartMealeculeQuotientPopulator cartMealeculePopulator) {
		this.cartMealeculePopulator = cartMealeculePopulator;
	}
}
