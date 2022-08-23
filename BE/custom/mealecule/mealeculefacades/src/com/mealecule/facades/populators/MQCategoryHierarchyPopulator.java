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

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.catalog.CatalogOption;
import de.hybris.platform.commercefacades.catalog.PageOption;
import de.hybris.platform.commercefacades.catalog.converters.populator.CategoryHierarchyPopulator;
import de.hybris.platform.commercefacades.catalog.data.CategoryHierarchyData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Populates {@link CategoryHierarchyData} from {@link CategoryModel} using specific {@link CatalogOption}s
 */
public class MQCategoryHierarchyPopulator extends CategoryHierarchyPopulator
{

	public static final String SLASH = "/";
	public static final String FIELDS_FULL = "?fields=FULL";
	public static final String CATALOGS = "catalogs";

	@Override
	public void populate(final CategoryModel source, final CategoryHierarchyData target,
			final Collection<? extends CatalogOption> options, final PageOption page) throws ConversionException
	{
		String cateoryURL = SLASH + CATALOGS + target.getUrl() + SLASH + source.getCode() + FIELDS_FULL;
		target.setId(source.getCode());
		target.setName(source.getName());
		target.setLastModified(source.getModifiedtime());
		target.setUrl(cateoryURL);
		target.setProducts(new ArrayList<ProductData>());
		target.setSubcategories(new ArrayList<CategoryHierarchyData>());

		if (options.contains(CatalogOption.PRODUCTS))
		{
			final List<ProductModel> products = getProductService().getProductsForCategory(source, page.getPageStart(),
					page.getPageSize());
			for (final ProductModel product : products)
			{
				final ProductData productData = getProductConverter().convert(product);
				productData.setUrl(SLASH + "products" + SLASH + product.getCode());
				target.getProducts().add(productData);
			}
		}

		if (page.includeInformationAboutPages())
		{
			final Integer totalNumber = getProductService().getAllProductsCountForCategory(source);
			final Integer numberOfPages = Integer.valueOf((int) (Math.ceil(totalNumber.doubleValue() / page.getPageSize())));
			target.setTotalNumber(totalNumber);
			target.setCurrentPage(Integer.valueOf(page.getPageNumber()));
			target.setPageSize(Integer.valueOf(page.getPageSize()));
			target.setNumberOfPages(numberOfPages);
		}

		if (options.contains(CatalogOption.SUBCATEGORIES))
		{
			recursive(target, source, true, options);
		}
	}

	@Override
	protected void recursive(final CategoryHierarchyData categoryData2, final CategoryModel category, final boolean root,
							 final Collection<? extends CatalogOption> options) {
		String urlPrefix = SLASH + CATALOGS + SLASH + category.getCatalogVersion().getCatalog().getId() + SLASH + category.getCatalogVersion().getVersion() + "/categories/";
		if (root)
		{
			for (final CategoryModel subc : category.getCategories())
			{
				recursive(categoryData2, subc, false, options);
			}
		}
		else
		{
			final CategoryHierarchyData categoryData = new CategoryHierarchyData();
			categoryData.setId(category.getCode());
			categoryData.setName(category.getName());
			categoryData.setLastModified(category.getModifiedtime());
			categoryData.setUrl(urlPrefix + category.getCode() + FIELDS_FULL);
			categoryData.setProducts(new ArrayList<ProductData>());
			categoryData.setSubcategories(new ArrayList<CategoryHierarchyData>());

			if (options.contains(CatalogOption.PRODUCTS))
			{
				final List<ProductModel> products = category.getProducts();
				for (final ProductModel product : products)
				{
					final ProductData productData = getProductConverter().convert(product);
					categoryData.getProducts().add(productData);
				}
			}
			categoryData2.getSubcategories().add(categoryData);
			for (final CategoryModel subc : category.getCategories())
			{
				recursive(categoryData, subc, false, options);
			}
		}
	}
}
