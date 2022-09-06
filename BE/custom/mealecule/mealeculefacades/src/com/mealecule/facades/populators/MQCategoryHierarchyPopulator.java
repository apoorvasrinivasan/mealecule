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
import com.mealecule.facades.product.data.MealeculeQuotientData;
import com.mealecule.facades.search.data.FacetData;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.catalog.CatalogOption;
import de.hybris.platform.commercefacades.catalog.PageOption;
import de.hybris.platform.commercefacades.catalog.converters.populator.CategoryHierarchyPopulator;
import de.hybris.platform.commercefacades.catalog.data.CategoryHierarchyData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;


/**
 * Populates {@link CategoryHierarchyData} from {@link CategoryModel} using specific {@link CatalogOption}s
 */
public class MQCategoryHierarchyPopulator extends CategoryHierarchyPopulator
{

	public static final String SLASH = "/";
	public static final String FIELDS_FULL = "?fields=FULL";
	public static final String CATALOGS = "catalogs";

	private EnumerationService enumerationService;

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
			List<MealeculeQuotientData> mealeculeQuotientDatas = new ArrayList<>();
			target.getProducts().forEach(productData ->{
				if(null != productData.getMealeculeQuotientData()) {
					mealeculeQuotientDatas.add(productData.getMealeculeQuotientData());
				}
			});
			if(CollectionUtils.isNotEmpty(mealeculeQuotientDatas)) {
				List<FacetData> facetDataList = new ArrayList<>();

				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.CARBOHYDRATE, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getCarbohydrate).reversed()).findFirst().get().getCarbohydrate(), mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getCarbohydrate)).findFirst().get().getCarbohydrate());
				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.FAT, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getFat).reversed()).findFirst().get().getFat(), mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getFat)).findFirst().get().getFat());
				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.FIBER, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getFiber).reversed()).findFirst().get().getFiber(), mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getFiber)).findFirst().get().getFiber());
				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.PROTEIN, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getProtein).reversed()).findFirst().get().getProtein(), mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getProtein)).findFirst().get().getProtein());
				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.SUGAR, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getSugar).reversed()).findFirst().get().getSugar(), mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getSugar)).findFirst().get().getSugar());
				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.CALORIES, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getEnergy).reversed()).findFirst().get().getEnergy(), mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getEnergy)).findFirst().get().getEnergy());
				updateNutrientFacet(facetDataList, MealeculeQuotientEnum.WATER, mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getWater).reversed()).findFirst().get().getWater(),mealeculeQuotientDatas.stream().sorted(Comparator.comparingDouble(MealeculeQuotientData::getWater)).findFirst().get().getWater());

				target.setFacetDatas(facetDataList);
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

	private void updateNutrientFacet(List<FacetData> facetDatas, MealeculeQuotientEnum nutrient, Double maxValue, Double minValue) {
		FacetData facetData = new FacetData();
		facetData.setCode(nutrient.getCode());
		facetData.setName(getEnumerationService().getEnumerationName(nutrient));
		facetData.setMaxValue(maxValue);
		facetData.setMinValue(minValue);
		facetDatas.add(facetData);
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

	public EnumerationService getEnumerationService() {
		return enumerationService;
	}

	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}
}
