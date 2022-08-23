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

import com.mealecule.core.model.ApparelProductModel;
import com.mealecule.facades.product.data.GenderData;
import com.mealecule.facades.product.data.IngredientsData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.variants.model.VariantProductModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;


/**
 * Populates {@link ProductData} with genders
 */
public class ProductIngredientsPopulator implements Populator<ProductModel, ProductData>
{

	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		if (null != source.getIngredients())
		{
			IngredientsData ingredientsData = new IngredientsData();
			target.setIngredientsData(ingredientsData);
		}
	}
}
