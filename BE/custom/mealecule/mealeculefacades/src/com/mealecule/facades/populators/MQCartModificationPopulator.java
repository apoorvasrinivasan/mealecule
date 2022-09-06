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
import com.mealecule.facades.product.data.MealeculeQuotientData;
import de.hybris.platform.commercefacades.order.converters.populator.CartModificationPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;


/**
 * Populates
 */
public class MQCartModificationPopulator extends CartModificationPopulator
{
	private CartMealeculeQuotientPopulator cartMealeculePopulator;

	@Override
	public void populate(final CommerceCartModification source, final CartModificationData target)
	{
		super.populate(source, target);
		final AbstractOrderModel abstractOrderModel = source.getEntry().getOrder();
		AbstractOrderData abstractOrderData = new AbstractOrderData();
		getCartMealeculePopulator().populate(abstractOrderModel, abstractOrderData);
		if(null != abstractOrderData.getMealeculeQuotientData()) {
			target.setMealeculeQuotientData(abstractOrderData.getMealeculeQuotientData());
		}

	}

	public CartMealeculeQuotientPopulator getCartMealeculePopulator() {
		return cartMealeculePopulator;
	}
	@Required
	public void setCartMealeculePopulator(CartMealeculeQuotientPopulator cartMealeculePopulator) {
		this.cartMealeculePopulator = cartMealeculePopulator;
	}
}
