/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 3, 2022 12:20:26 PM                     ---
 * ----------------------------------------------------------------
 */
package com.mealecule.core.jalo;

import com.mealecule.core.constants.MealeculeCoreConstants;
import com.mealecule.core.jalo.MealeculeQuotientData;
import de.hybris.platform.constants.CoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem MealeculeQuotient}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMealeculeQuotient extends GenericItem
{
	/** Qualifier of the <code>MealeculeQuotient.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>MealeculeQuotient.weightInG</code> attribute **/
	public static final String WEIGHTING = "weightInG";
	/** Qualifier of the <code>MealeculeQuotient.weightInML</code> attribute **/
	public static final String WEIGHTINML = "weightInML";
	/** Qualifier of the <code>MealeculeQuotient.product</code> attribute **/
	public static final String PRODUCT = "product";
	/** Qualifier of the <code>MealeculeQuotient.mealeculeQuotientData</code> attribute **/
	public static final String MEALECULEQUOTIENTDATA = "mealeculeQuotientData";
	/**
	* {@link OneToManyHandler} for handling 1:n PRODUCT's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Product> PRODUCTHANDLER = new OneToManyHandler<Product>(
	CoreConstants.TC.PRODUCT,
	false,
	"mealeculeQuotient",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link OneToManyHandler} for handling 1:n MEALECULEQUOTIENTDATA's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<MealeculeQuotientData> MEALECULEQUOTIENTDATAHANDLER = new OneToManyHandler<MealeculeQuotientData>(
	MealeculeCoreConstants.TC.MEALECULEQUOTIENTDATA,
	false,
	"mealeculeQuotient",
	null,
	false,
	true,
	CollectionType.LIST
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(WEIGHTING, AttributeMode.INITIAL);
		tmp.put(WEIGHTINML, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.mealeculeQuotientData</code> attribute.
	 * @return the mealeculeQuotientData
	 */
	public List<MealeculeQuotientData> getMealeculeQuotientData(final SessionContext ctx)
	{
		return (List<MealeculeQuotientData>)MEALECULEQUOTIENTDATAHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.mealeculeQuotientData</code> attribute.
	 * @return the mealeculeQuotientData
	 */
	public List<MealeculeQuotientData> getMealeculeQuotientData()
	{
		return getMealeculeQuotientData( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.mealeculeQuotientData</code> attribute. 
	 * @param value the mealeculeQuotientData
	 */
	public void setMealeculeQuotientData(final SessionContext ctx, final List<MealeculeQuotientData> value)
	{
		MEALECULEQUOTIENTDATAHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.mealeculeQuotientData</code> attribute. 
	 * @param value the mealeculeQuotientData
	 */
	public void setMealeculeQuotientData(final List<MealeculeQuotientData> value)
	{
		setMealeculeQuotientData( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to mealeculeQuotientData. 
	 * @param value the item to add to mealeculeQuotientData
	 */
	public void addToMealeculeQuotientData(final SessionContext ctx, final MealeculeQuotientData value)
	{
		MEALECULEQUOTIENTDATAHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to mealeculeQuotientData. 
	 * @param value the item to add to mealeculeQuotientData
	 */
	public void addToMealeculeQuotientData(final MealeculeQuotientData value)
	{
		addToMealeculeQuotientData( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from mealeculeQuotientData. 
	 * @param value the item to remove from mealeculeQuotientData
	 */
	public void removeFromMealeculeQuotientData(final SessionContext ctx, final MealeculeQuotientData value)
	{
		MEALECULEQUOTIENTDATAHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from mealeculeQuotientData. 
	 * @param value the item to remove from mealeculeQuotientData
	 */
	public void removeFromMealeculeQuotientData(final MealeculeQuotientData value)
	{
		removeFromMealeculeQuotientData( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.product</code> attribute.
	 * @return the product
	 */
	public Collection<Product> getProduct(final SessionContext ctx)
	{
		return PRODUCTHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.product</code> attribute.
	 * @return the product
	 */
	public Collection<Product> getProduct()
	{
		return getProduct( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final SessionContext ctx, final Collection<Product> value)
	{
		PRODUCTHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final Collection<Product> value)
	{
		setProduct( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to product. 
	 * @param value the item to add to product
	 */
	public void addToProduct(final SessionContext ctx, final Product value)
	{
		PRODUCTHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to product. 
	 * @param value the item to add to product
	 */
	public void addToProduct(final Product value)
	{
		addToProduct( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from product. 
	 * @param value the item to remove from product
	 */
	public void removeFromProduct(final SessionContext ctx, final Product value)
	{
		PRODUCTHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from product. 
	 * @param value the item to remove from product
	 */
	public void removeFromProduct(final Product value)
	{
		removeFromProduct( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInG</code> attribute.
	 * @return the weightInG
	 */
	public Double getWeightInG(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, WEIGHTING);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInG</code> attribute.
	 * @return the weightInG
	 */
	public Double getWeightInG()
	{
		return getWeightInG( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInG</code> attribute. 
	 * @return the weightInG
	 */
	public double getWeightInGAsPrimitive(final SessionContext ctx)
	{
		Double value = getWeightInG( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInG</code> attribute. 
	 * @return the weightInG
	 */
	public double getWeightInGAsPrimitive()
	{
		return getWeightInGAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, WEIGHTING,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final Double value)
	{
		setWeightInG( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final SessionContext ctx, final double value)
	{
		setWeightInG( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInG</code> attribute. 
	 * @param value the weightInG
	 */
	public void setWeightInG(final double value)
	{
		setWeightInG( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInML</code> attribute.
	 * @return the weightInML
	 */
	public Double getWeightInML(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, WEIGHTINML);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInML</code> attribute.
	 * @return the weightInML
	 */
	public Double getWeightInML()
	{
		return getWeightInML( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInML</code> attribute. 
	 * @return the weightInML
	 */
	public double getWeightInMLAsPrimitive(final SessionContext ctx)
	{
		Double value = getWeightInML( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.weightInML</code> attribute. 
	 * @return the weightInML
	 */
	public double getWeightInMLAsPrimitive()
	{
		return getWeightInMLAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, WEIGHTINML,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final Double value)
	{
		setWeightInML( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final SessionContext ctx, final double value)
	{
		setWeightInML( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.weightInML</code> attribute. 
	 * @param value the weightInML
	 */
	public void setWeightInML(final double value)
	{
		setWeightInML( getSession().getSessionContext(), value );
	}
	
}
