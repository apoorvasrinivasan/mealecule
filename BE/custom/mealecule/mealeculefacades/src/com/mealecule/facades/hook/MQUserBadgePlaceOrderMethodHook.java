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
package com.mealecule.facades.hook;

import com.mealecule.core.model.BadgeModel;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *@author @isha.mehta
 */
@SuppressWarnings("PMD")
public class MQUserBadgePlaceOrderMethodHook  implements CommercePlaceOrderMethodHook
{
	private ModelService modelService;

	@Override
	public void afterPlaceOrder(CommerceCheckoutParameter parameter, CommerceOrderResult orderModel) throws InvalidCartException {
		if(null != orderModel.getOrder().getTotalDiscounts()){
			OrderModel order = orderModel.getOrder();
			final CustomerModel customer = (CustomerModel) order.getUser();
			if(null != customer.getType() && CustomerType.REGISTERED.getCode().equals(customer.getType().getCode())){
				BadgeModel badgeModel = customer.getBadge();
				if(null != badgeModel){
					Integer customerCoins = customer.getCoins();
					Integer roundedDiscountValue = new BigDecimal(order.getTotalDiscounts()).setScale(0, RoundingMode.UP).intValue();
					customerCoins = customerCoins - roundedDiscountValue;
					customer.setCoins(customerCoins);
					getModelService().save(customer);
				}
			}
		}
	}

	@Override
	public void beforePlaceOrder(CommerceCheckoutParameter parameter) throws InvalidCartException {

	}

	@Override
	public void beforeSubmitOrder(CommerceCheckoutParameter parameter, CommerceOrderResult result) throws InvalidCartException {

	}

	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
}
