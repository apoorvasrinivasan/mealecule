/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Aug 22, 2022 11:58:09 PM                    ---
 * ----------------------------------------------------------------
 */
package com.mealecule.core.jalo;

import com.mealecule.core.constants.MealeculeCoreConstants;
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
	/** Qualifier of the <code>MealeculeQuotient.water</code> attribute **/
	public static final String WATER = "water";
	/** Qualifier of the <code>MealeculeQuotient.energy</code> attribute **/
	public static final String ENERGY = "energy";
	/** Qualifier of the <code>MealeculeQuotient.nitrogen</code> attribute **/
	public static final String NITROGEN = "nitrogen";
	/** Qualifier of the <code>MealeculeQuotient.protein</code> attribute **/
	public static final String PROTEIN = "protein";
	/** Qualifier of the <code>MealeculeQuotient.fat</code> attribute **/
	public static final String FAT = "fat";
	/** Qualifier of the <code>MealeculeQuotient.carbohydrate</code> attribute **/
	public static final String CARBOHYDRATE = "carbohydrate";
	/** Qualifier of the <code>MealeculeQuotient.fiber</code> attribute **/
	public static final String FIBER = "fiber";
	/** Qualifier of the <code>MealeculeQuotient.sugar</code> attribute **/
	public static final String SUGAR = "sugar";
	/** Qualifier of the <code>MealeculeQuotient.product</code> attribute **/
	public static final String PRODUCT = "product";
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
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(WEIGHTING, AttributeMode.INITIAL);
		tmp.put(WEIGHTINML, AttributeMode.INITIAL);
		tmp.put(WATER, AttributeMode.INITIAL);
		tmp.put(ENERGY, AttributeMode.INITIAL);
		tmp.put(NITROGEN, AttributeMode.INITIAL);
		tmp.put(PROTEIN, AttributeMode.INITIAL);
		tmp.put(FAT, AttributeMode.INITIAL);
		tmp.put(CARBOHYDRATE, AttributeMode.INITIAL);
		tmp.put(FIBER, AttributeMode.INITIAL);
		tmp.put(SUGAR, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.carbohydrate</code> attribute.
	 * @return the carbohydrate
	 */
	public Double getCarbohydrate(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, CARBOHYDRATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.carbohydrate</code> attribute.
	 * @return the carbohydrate
	 */
	public Double getCarbohydrate()
	{
		return getCarbohydrate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.carbohydrate</code> attribute. 
	 * @return the carbohydrate
	 */
	public double getCarbohydrateAsPrimitive(final SessionContext ctx)
	{
		Double value = getCarbohydrate( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.carbohydrate</code> attribute. 
	 * @return the carbohydrate
	 */
	public double getCarbohydrateAsPrimitive()
	{
		return getCarbohydrateAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.carbohydrate</code> attribute. 
	 * @param value the carbohydrate
	 */
	public void setCarbohydrate(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, CARBOHYDRATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.carbohydrate</code> attribute. 
	 * @param value the carbohydrate
	 */
	public void setCarbohydrate(final Double value)
	{
		setCarbohydrate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.carbohydrate</code> attribute. 
	 * @param value the carbohydrate
	 */
	public void setCarbohydrate(final SessionContext ctx, final double value)
	{
		setCarbohydrate( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.carbohydrate</code> attribute. 
	 * @param value the carbohydrate
	 */
	public void setCarbohydrate(final double value)
	{
		setCarbohydrate( getSession().getSessionContext(), value );
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
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.energy</code> attribute.
	 * @return the energy
	 */
	public Double getEnergy(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, ENERGY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.energy</code> attribute.
	 * @return the energy
	 */
	public Double getEnergy()
	{
		return getEnergy( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.energy</code> attribute. 
	 * @return the energy
	 */
	public double getEnergyAsPrimitive(final SessionContext ctx)
	{
		Double value = getEnergy( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.energy</code> attribute. 
	 * @return the energy
	 */
	public double getEnergyAsPrimitive()
	{
		return getEnergyAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.energy</code> attribute. 
	 * @param value the energy
	 */
	public void setEnergy(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, ENERGY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.energy</code> attribute. 
	 * @param value the energy
	 */
	public void setEnergy(final Double value)
	{
		setEnergy( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.energy</code> attribute. 
	 * @param value the energy
	 */
	public void setEnergy(final SessionContext ctx, final double value)
	{
		setEnergy( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.energy</code> attribute. 
	 * @param value the energy
	 */
	public void setEnergy(final double value)
	{
		setEnergy( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fat</code> attribute.
	 * @return the fat
	 */
	public Double getFat(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, FAT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fat</code> attribute.
	 * @return the fat
	 */
	public Double getFat()
	{
		return getFat( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fat</code> attribute. 
	 * @return the fat
	 */
	public double getFatAsPrimitive(final SessionContext ctx)
	{
		Double value = getFat( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fat</code> attribute. 
	 * @return the fat
	 */
	public double getFatAsPrimitive()
	{
		return getFatAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fat</code> attribute. 
	 * @param value the fat
	 */
	public void setFat(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, FAT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fat</code> attribute. 
	 * @param value the fat
	 */
	public void setFat(final Double value)
	{
		setFat( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fat</code> attribute. 
	 * @param value the fat
	 */
	public void setFat(final SessionContext ctx, final double value)
	{
		setFat( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fat</code> attribute. 
	 * @param value the fat
	 */
	public void setFat(final double value)
	{
		setFat( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fiber</code> attribute.
	 * @return the fiber
	 */
	public Double getFiber(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, FIBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fiber</code> attribute.
	 * @return the fiber
	 */
	public Double getFiber()
	{
		return getFiber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fiber</code> attribute. 
	 * @return the fiber
	 */
	public double getFiberAsPrimitive(final SessionContext ctx)
	{
		Double value = getFiber( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.fiber</code> attribute. 
	 * @return the fiber
	 */
	public double getFiberAsPrimitive()
	{
		return getFiberAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fiber</code> attribute. 
	 * @param value the fiber
	 */
	public void setFiber(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, FIBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fiber</code> attribute. 
	 * @param value the fiber
	 */
	public void setFiber(final Double value)
	{
		setFiber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fiber</code> attribute. 
	 * @param value the fiber
	 */
	public void setFiber(final SessionContext ctx, final double value)
	{
		setFiber( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.fiber</code> attribute. 
	 * @param value the fiber
	 */
	public void setFiber(final double value)
	{
		setFiber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.nitrogen</code> attribute.
	 * @return the nitrogen
	 */
	public Double getNitrogen(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, NITROGEN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.nitrogen</code> attribute.
	 * @return the nitrogen
	 */
	public Double getNitrogen()
	{
		return getNitrogen( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.nitrogen</code> attribute. 
	 * @return the nitrogen
	 */
	public double getNitrogenAsPrimitive(final SessionContext ctx)
	{
		Double value = getNitrogen( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.nitrogen</code> attribute. 
	 * @return the nitrogen
	 */
	public double getNitrogenAsPrimitive()
	{
		return getNitrogenAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.nitrogen</code> attribute. 
	 * @param value the nitrogen
	 */
	public void setNitrogen(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, NITROGEN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.nitrogen</code> attribute. 
	 * @param value the nitrogen
	 */
	public void setNitrogen(final Double value)
	{
		setNitrogen( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.nitrogen</code> attribute. 
	 * @param value the nitrogen
	 */
	public void setNitrogen(final SessionContext ctx, final double value)
	{
		setNitrogen( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.nitrogen</code> attribute. 
	 * @param value the nitrogen
	 */
	public void setNitrogen(final double value)
	{
		setNitrogen( getSession().getSessionContext(), value );
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
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.protein</code> attribute.
	 * @return the protein
	 */
	public Double getProtein(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, PROTEIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.protein</code> attribute.
	 * @return the protein
	 */
	public Double getProtein()
	{
		return getProtein( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.protein</code> attribute. 
	 * @return the protein
	 */
	public double getProteinAsPrimitive(final SessionContext ctx)
	{
		Double value = getProtein( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.protein</code> attribute. 
	 * @return the protein
	 */
	public double getProteinAsPrimitive()
	{
		return getProteinAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.protein</code> attribute. 
	 * @param value the protein
	 */
	public void setProtein(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, PROTEIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.protein</code> attribute. 
	 * @param value the protein
	 */
	public void setProtein(final Double value)
	{
		setProtein( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.protein</code> attribute. 
	 * @param value the protein
	 */
	public void setProtein(final SessionContext ctx, final double value)
	{
		setProtein( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.protein</code> attribute. 
	 * @param value the protein
	 */
	public void setProtein(final double value)
	{
		setProtein( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.sugar</code> attribute.
	 * @return the sugar
	 */
	public Double getSugar(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SUGAR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.sugar</code> attribute.
	 * @return the sugar
	 */
	public Double getSugar()
	{
		return getSugar( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.sugar</code> attribute. 
	 * @return the sugar
	 */
	public double getSugarAsPrimitive(final SessionContext ctx)
	{
		Double value = getSugar( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.sugar</code> attribute. 
	 * @return the sugar
	 */
	public double getSugarAsPrimitive()
	{
		return getSugarAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SUGAR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final Double value)
	{
		setSugar( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final SessionContext ctx, final double value)
	{
		setSugar( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final double value)
	{
		setSugar( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.water</code> attribute.
	 * @return the water
	 */
	public Double getWater(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, WATER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.water</code> attribute.
	 * @return the water
	 */
	public Double getWater()
	{
		return getWater( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.water</code> attribute. 
	 * @return the water
	 */
	public double getWaterAsPrimitive(final SessionContext ctx)
	{
		Double value = getWater( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MealeculeQuotient.water</code> attribute. 
	 * @return the water
	 */
	public double getWaterAsPrimitive()
	{
		return getWaterAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.water</code> attribute. 
	 * @param value the water
	 */
	public void setWater(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, WATER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.water</code> attribute. 
	 * @param value the water
	 */
	public void setWater(final Double value)
	{
		setWater( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.water</code> attribute. 
	 * @param value the water
	 */
	public void setWater(final SessionContext ctx, final double value)
	{
		setWater( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MealeculeQuotient.water</code> attribute. 
	 * @param value the water
	 */
	public void setWater(final double value)
	{
		setWater( getSession().getSessionContext(), value );
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
