/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Aug 25, 2022 2:34:11 AM                     ---
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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Ingredients}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedIngredients extends GenericItem
{
	/** Qualifier of the <code>Ingredients.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Ingredients.cocoa</code> attribute **/
	public static final String COCOA = "cocoa";
	/** Qualifier of the <code>Ingredients.sugar</code> attribute **/
	public static final String SUGAR = "sugar";
	/** Qualifier of the <code>Ingredients.cocoaButter</code> attribute **/
	public static final String COCOABUTTER = "cocoaButter";
	/** Qualifier of the <code>Ingredients.emulsifier</code> attribute **/
	public static final String EMULSIFIER = "emulsifier";
	/** Qualifier of the <code>Ingredients.soya</code> attribute **/
	public static final String SOYA = "soya";
	/** Qualifier of the <code>Ingredients.gelatin</code> attribute **/
	public static final String GELATIN = "gelatin";
	/** Qualifier of the <code>Ingredients.product</code> attribute **/
	public static final String PRODUCT = "product";
	/**
	* {@link OneToManyHandler} for handling 1:n PRODUCT's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Product> PRODUCTHANDLER = new OneToManyHandler<Product>(
	CoreConstants.TC.PRODUCT,
	false,
	"ingredients",
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
		tmp.put(COCOA, AttributeMode.INITIAL);
		tmp.put(SUGAR, AttributeMode.INITIAL);
		tmp.put(COCOABUTTER, AttributeMode.INITIAL);
		tmp.put(EMULSIFIER, AttributeMode.INITIAL);
		tmp.put(SOYA, AttributeMode.INITIAL);
		tmp.put(GELATIN, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoa</code> attribute.
	 * @return the cocoa
	 */
	public Double getCocoa(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, COCOA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoa</code> attribute.
	 * @return the cocoa
	 */
	public Double getCocoa()
	{
		return getCocoa( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoa</code> attribute. 
	 * @return the cocoa
	 */
	public double getCocoaAsPrimitive(final SessionContext ctx)
	{
		Double value = getCocoa( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoa</code> attribute. 
	 * @return the cocoa
	 */
	public double getCocoaAsPrimitive()
	{
		return getCocoaAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoa</code> attribute. 
	 * @param value the cocoa
	 */
	public void setCocoa(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, COCOA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoa</code> attribute. 
	 * @param value the cocoa
	 */
	public void setCocoa(final Double value)
	{
		setCocoa( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoa</code> attribute. 
	 * @param value the cocoa
	 */
	public void setCocoa(final SessionContext ctx, final double value)
	{
		setCocoa( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoa</code> attribute. 
	 * @param value the cocoa
	 */
	public void setCocoa(final double value)
	{
		setCocoa( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoaButter</code> attribute.
	 * @return the cocoaButter
	 */
	public Double getCocoaButter(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, COCOABUTTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoaButter</code> attribute.
	 * @return the cocoaButter
	 */
	public Double getCocoaButter()
	{
		return getCocoaButter( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoaButter</code> attribute. 
	 * @return the cocoaButter
	 */
	public double getCocoaButterAsPrimitive(final SessionContext ctx)
	{
		Double value = getCocoaButter( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.cocoaButter</code> attribute. 
	 * @return the cocoaButter
	 */
	public double getCocoaButterAsPrimitive()
	{
		return getCocoaButterAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoaButter</code> attribute. 
	 * @param value the cocoaButter
	 */
	public void setCocoaButter(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, COCOABUTTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoaButter</code> attribute. 
	 * @param value the cocoaButter
	 */
	public void setCocoaButter(final Double value)
	{
		setCocoaButter( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoaButter</code> attribute. 
	 * @param value the cocoaButter
	 */
	public void setCocoaButter(final SessionContext ctx, final double value)
	{
		setCocoaButter( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.cocoaButter</code> attribute. 
	 * @param value the cocoaButter
	 */
	public void setCocoaButter(final double value)
	{
		setCocoaButter( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.emulsifier</code> attribute.
	 * @return the emulsifier
	 */
	public Double getEmulsifier(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, EMULSIFIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.emulsifier</code> attribute.
	 * @return the emulsifier
	 */
	public Double getEmulsifier()
	{
		return getEmulsifier( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.emulsifier</code> attribute. 
	 * @return the emulsifier
	 */
	public double getEmulsifierAsPrimitive(final SessionContext ctx)
	{
		Double value = getEmulsifier( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.emulsifier</code> attribute. 
	 * @return the emulsifier
	 */
	public double getEmulsifierAsPrimitive()
	{
		return getEmulsifierAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.emulsifier</code> attribute. 
	 * @param value the emulsifier
	 */
	public void setEmulsifier(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, EMULSIFIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.emulsifier</code> attribute. 
	 * @param value the emulsifier
	 */
	public void setEmulsifier(final Double value)
	{
		setEmulsifier( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.emulsifier</code> attribute. 
	 * @param value the emulsifier
	 */
	public void setEmulsifier(final SessionContext ctx, final double value)
	{
		setEmulsifier( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.emulsifier</code> attribute. 
	 * @param value the emulsifier
	 */
	public void setEmulsifier(final double value)
	{
		setEmulsifier( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.gelatin</code> attribute.
	 * @return the gelatin
	 */
	public Double getGelatin(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, GELATIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.gelatin</code> attribute.
	 * @return the gelatin
	 */
	public Double getGelatin()
	{
		return getGelatin( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.gelatin</code> attribute. 
	 * @return the gelatin
	 */
	public double getGelatinAsPrimitive(final SessionContext ctx)
	{
		Double value = getGelatin( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.gelatin</code> attribute. 
	 * @return the gelatin
	 */
	public double getGelatinAsPrimitive()
	{
		return getGelatinAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.gelatin</code> attribute. 
	 * @param value the gelatin
	 */
	public void setGelatin(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, GELATIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.gelatin</code> attribute. 
	 * @param value the gelatin
	 */
	public void setGelatin(final Double value)
	{
		setGelatin( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.gelatin</code> attribute. 
	 * @param value the gelatin
	 */
	public void setGelatin(final SessionContext ctx, final double value)
	{
		setGelatin( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.gelatin</code> attribute. 
	 * @param value the gelatin
	 */
	public void setGelatin(final double value)
	{
		setGelatin( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.product</code> attribute.
	 * @return the product
	 */
	public Collection<Product> getProduct(final SessionContext ctx)
	{
		return PRODUCTHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.product</code> attribute.
	 * @return the product
	 */
	public Collection<Product> getProduct()
	{
		return getProduct( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.product</code> attribute. 
	 * @param value the product
	 */
	public void setProduct(final SessionContext ctx, final Collection<Product> value)
	{
		PRODUCTHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.product</code> attribute. 
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
	 * <i>Generated method</i> - Getter of the <code>Ingredients.soya</code> attribute.
	 * @return the soya
	 */
	public Double getSoya(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SOYA);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.soya</code> attribute.
	 * @return the soya
	 */
	public Double getSoya()
	{
		return getSoya( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.soya</code> attribute. 
	 * @return the soya
	 */
	public double getSoyaAsPrimitive(final SessionContext ctx)
	{
		Double value = getSoya( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.soya</code> attribute. 
	 * @return the soya
	 */
	public double getSoyaAsPrimitive()
	{
		return getSoyaAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.soya</code> attribute. 
	 * @param value the soya
	 */
	public void setSoya(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SOYA,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.soya</code> attribute. 
	 * @param value the soya
	 */
	public void setSoya(final Double value)
	{
		setSoya( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.soya</code> attribute. 
	 * @param value the soya
	 */
	public void setSoya(final SessionContext ctx, final double value)
	{
		setSoya( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.soya</code> attribute. 
	 * @param value the soya
	 */
	public void setSoya(final double value)
	{
		setSoya( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.sugar</code> attribute.
	 * @return the sugar
	 */
	public Double getSugar(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, SUGAR);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.sugar</code> attribute.
	 * @return the sugar
	 */
	public Double getSugar()
	{
		return getSugar( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.sugar</code> attribute. 
	 * @return the sugar
	 */
	public double getSugarAsPrimitive(final SessionContext ctx)
	{
		Double value = getSugar( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Ingredients.sugar</code> attribute. 
	 * @return the sugar
	 */
	public double getSugarAsPrimitive()
	{
		return getSugarAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, SUGAR,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final Double value)
	{
		setSugar( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final SessionContext ctx, final double value)
	{
		setSugar( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Ingredients.sugar</code> attribute. 
	 * @param value the sugar
	 */
	public void setSugar(final double value)
	{
		setSugar( getSession().getSessionContext(), value );
	}
	
}
