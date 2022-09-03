/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Aug 25, 2022 2:34:11 AM                     ---
 * ----------------------------------------------------------------
 */
package com.mealecule.core.jalo;

import com.mealecule.core.constants.MealeculeCoreConstants;
import com.mealecule.core.jalo.ApparelProduct;
import com.mealecule.core.jalo.ApparelSizeVariantProduct;
import com.mealecule.core.jalo.ApparelStyleVariantProduct;
import com.mealecule.core.jalo.ElectronicsColorVariantProduct;
import com.mealecule.core.jalo.Ingredients;
import com.mealecule.core.jalo.MealeculeQuotient;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>MealeculeCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMealeculeCoreManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("weightInG", AttributeMode.INITIAL);
		tmp.put("weightInML", AttributeMode.INITIAL);
		tmp.put("totalCalories", AttributeMode.INITIAL);
		tmp.put("mealeculeQuotient", AttributeMode.INITIAL);
		tmp.put("ingredients", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public ApparelProduct createApparelProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MealeculeCoreConstants.TC.APPARELPRODUCT );
			return (ApparelProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelProduct createApparelProduct(final Map attributeValues)
	{
		return createApparelProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MealeculeCoreConstants.TC.APPARELSIZEVARIANTPRODUCT );
			return (ApparelSizeVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelSizeVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelSizeVariantProduct createApparelSizeVariantProduct(final Map attributeValues)
	{
		return createApparelSizeVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MealeculeCoreConstants.TC.APPARELSTYLEVARIANTPRODUCT );
			return (ApparelStyleVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ApparelStyleVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ApparelStyleVariantProduct createApparelStyleVariantProduct(final Map attributeValues)
	{
		return createApparelStyleVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MealeculeCoreConstants.TC.ELECTRONICSCOLORVARIANTPRODUCT );
			return (ElectronicsColorVariantProduct)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ElectronicsColorVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public ElectronicsColorVariantProduct createElectronicsColorVariantProduct(final Map attributeValues)
	{
		return createElectronicsColorVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public Ingredients createIngredients(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MealeculeCoreConstants.TC.INGREDIENTS );
			return (Ingredients)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Ingredients : "+e.getMessage(), 0 );
		}
	}
	
	public Ingredients createIngredients(final Map attributeValues)
	{
		return createIngredients( getSession().getSessionContext(), attributeValues );
	}
	
	public MealeculeQuotient createMealeculeQuotient(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( MealeculeCoreConstants.TC.MEALECULEQUOTIENT );
			return (MealeculeQuotient)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating MealeculeQuotient : "+e.getMessage(), 0 );
		}
	}
	
	public MealeculeQuotient createMealeculeQuotient(final Map attributeValues)
	{
		return createMealeculeQuotient( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return MealeculeCoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.ingredients</code> attribute.
	 * @return the ingredients
	 */
	public Ingredients getIngredients(final SessionContext ctx, final Product item)
	{
		return (Ingredients)item.getProperty( ctx, MealeculeCoreConstants.Attributes.Product.INGREDIENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.ingredients</code> attribute.
	 * @return the ingredients
	 */
	public Ingredients getIngredients(final Product item)
	{
		return getIngredients( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setIngredients(final SessionContext ctx, final Product item, final Ingredients value)
	{
		item.setProperty(ctx, MealeculeCoreConstants.Attributes.Product.INGREDIENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.ingredients</code> attribute. 
	 * @param value the ingredients
	 */
	public void setIngredients(final Product item, final Ingredients value)
	{
		setIngredients( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.mealeculeQuotient</code> attribute.
	 * @return the mealeculeQuotient
	 */
	public MealeculeQuotient getMealeculeQuotient(final SessionContext ctx, final Product item)
	{
		return (MealeculeQuotient)item.getProperty( ctx, MealeculeCoreConstants.Attributes.Product.MEALECULEQUOTIENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.mealeculeQuotient</code> attribute.
	 * @return the mealeculeQuotient
	 */
	public MealeculeQuotient getMealeculeQuotient(final Product item)
	{
		return getMealeculeQuotient( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.mealeculeQuotient</code> attribute. 
	 * @param value the mealeculeQuotient
	 */
	public void setMealeculeQuotient(final SessionContext ctx, final Product item, final MealeculeQuotient value)
	{
		item.setProperty(ctx, MealeculeCoreConstants.Attributes.Product.MEALECULEQUOTIENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.mealeculeQuotient</code> attribute. 
	 * @param value the mealeculeQuotient
	 */
	public void setMealeculeQuotient(final Product item, final MealeculeQuotient value)
	{
		setMealeculeQuotient( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.totalCalories</code> attribute.
	 * @return the totalCalories
	 */
	public Double getTotalCalories(final SessionContext ctx, final Product item)
	{
		return (Double)item.getProperty( ctx, MealeculeCoreConstants.Attributes.Product.TOTALCALORIES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.totalCalories</code> attribute.
	 * @return the totalCalories
	 */
	public Double getTotalCalories(final Product item)
	{
		return getTotalCalories( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.totalCalories</code> attribute. 
	 * @return the totalCalories
	 */
	public double getTotalCaloriesAsPrimitive(final SessionContext ctx, final Product item)
	{
		Double value = getTotalCalories( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.totalCalories</code> attribute. 
	 * @return the totalCalories
	 */
	public double getTotalCaloriesAsPrimitive(final Product item)
	{
		return getTotalCaloriesAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.totalCalories</code> attribute. 
	 * @param value the totalCalories
	 */
	public void setTotalCalories(final SessionContext ctx, final Product item, final Double value)
	{
		item.setProperty(ctx, MealeculeCoreConstants.Attributes.Product.TOTALCALORIES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.totalCalories</code> attribute. 
	 * @param value the totalCalories
	 */
	public void setTotalCalories(final Product item, final Double value)
	{
		setTotalCalories( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.totalCalories</code> attribute. 
	 * @param value the totalCalories
	 */
	public void setTotalCalories(final SessionContext ctx, final Product item, final double value)
	{
		setTotalCalories( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.totalCalories</code> attribute. 
	 * @param value the totalCalories
	 */
	public void setTotalCalories(final Product item, final double value)
	{
		setTotalCalories( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInG</code> attribute.
	 * @return the weightInG
	 */
	public Double getWeightInG(final SessionContext ctx, final Product item)
	{
		return (Double)item.getProperty( ctx, MealeculeCoreConstants.Attributes.Product.WEIGHTING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInG</code> attribute.
	 * @return the weightInG
	 */
	public Double getWeightInG(final Product item)
	{
		return getWeightInG( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInG</code> attribute. 
	 * @return the weightInG
	 */
	public double getWeightInGAsPrimitive(final SessionContext ctx, final Product item)
	{
		Double value = getWeightInG( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInG</code> attribute. 
	 * @return the weightInG
	 */
	public double getWeightInGAsPrimitive(final Product item)
	{
		return getWeightInGAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final SessionContext ctx, final Product item, final Double value)
	{
		item.setProperty(ctx, MealeculeCoreConstants.Attributes.Product.WEIGHTING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final Product item, final Double value)
	{
		setWeightInG( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final SessionContext ctx, final Product item, final double value)
	{
		setWeightInG( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final Product item, final double value)
	{
		setWeightInG( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInML</code> attribute.
	 * @return the weightInML
	 */
	public Double getWeightInML(final SessionContext ctx, final Product item)
	{
		return (Double)item.getProperty( ctx, MealeculeCoreConstants.Attributes.Product.WEIGHTINML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInML</code> attribute.
	 * @return the weightInML
	 */
	public Double getWeightInML(final Product item)
	{
		return getWeightInML( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInML</code> attribute. 
	 * @return the weightInML
	 */
	public double getWeightInMLAsPrimitive(final SessionContext ctx, final Product item)
	{
		Double value = getWeightInML( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.weightInML</code> attribute. 
	 * @return the weightInML
	 */
	public double getWeightInMLAsPrimitive(final Product item)
	{
		return getWeightInMLAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final SessionContext ctx, final Product item, final Double value)
	{
		item.setProperty(ctx, MealeculeCoreConstants.Attributes.Product.WEIGHTINML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final Product item, final Double value)
	{
		setWeightInML( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final SessionContext ctx, final Product item, final double value)
	{
		setWeightInML( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final Product item, final double value)
	{
		setWeightInML( getSession().getSessionContext(), item, value );
	}
	
}
