/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 3, 2022 12:20:26 PM                     ---
 * ----------------------------------------------------------------
 */
package com.mealecule.core.constants;

/**
 * @deprecated since ages - use constants in Model classes instead
 */
@Deprecated
@SuppressWarnings({"unused","cast","PMD"})
public class GeneratedMealeculeCoreConstants
{
	public static final String EXTENSIONNAME = "mealeculecore";
	public static class TC
	{
		public static final String APPARELPRODUCT = "ApparelProduct".intern();
		public static final String APPARELSIZEVARIANTPRODUCT = "ApparelSizeVariantProduct".intern();
		public static final String APPARELSTYLEVARIANTPRODUCT = "ApparelStyleVariantProduct".intern();
		public static final String BADGE = "Badge".intern();
		public static final String DISPLAYNAMEENUM = "DisplayNameEnum".intern();
		public static final String ELECTRONICSCOLORVARIANTPRODUCT = "ElectronicsColorVariantProduct".intern();
		public static final String INGREDIENTS = "Ingredients".intern();
		public static final String LEVELENUM = "LevelEnum".intern();
		public static final String MEALECULEQUOTIENT = "MealeculeQuotient".intern();
		public static final String MEALECULEQUOTIENTDATA = "MealeculeQuotientData".intern();
		public static final String MEALECULEQUOTIENTENUM = "MealeculeQuotientEnum".intern();
		public static final String STATUSENUM = "StatusEnum".intern();
		public static final String SWATCHCOLORENUM = "SwatchColorEnum".intern();
	}
	public static class Attributes
	{
		public static class Product
		{
			public static final String INGREDIENTS = "ingredients".intern();
			public static final String MEALECULEQUOTIENT = "mealeculeQuotient".intern();
			public static final String TOTALCALORIES = "totalCalories".intern();
			public static final String WEIGHTING = "weightInG".intern();
			public static final String WEIGHTINML = "weightInML".intern();
		}
		public static class User
		{
			public static final String BADGE = "badge".intern();
			public static final String COINS = "coins".intern();
			public static final String PREFERREDMEALECULE = "preferredMealecule".intern();
		}
	}
	public static class Enumerations
	{
		public static class DisplayNameEnum
		{
			public static final String REGISTERED = "REGISTERED".intern();
			public static final String NONREGISTERED = "NONREGISTERED".intern();
		}
		public static class LevelEnum
		{
			public static final String GOLD = "GOLD".intern();
			public static final String SILVER = "SILVER".intern();
			public static final String BRONZE = "BRONZE".intern();
		}
		public static class MealeculeQuotientEnum
		{
			public static final String CARBOHYDRATE = "CARBOHYDRATE".intern();
			public static final String FAT = "FAT".intern();
			public static final String PROTEIN = "PROTEIN".intern();
			public static final String FIBER = "FIBER".intern();
			public static final String CALORIES = "CALORIES".intern();
			public static final String WATER = "WATER".intern();
			public static final String SUGAR = "SUGAR".intern();
		}
		public static class StatusEnum
		{
			public static final String ACTIVE = "ACTIVE".intern();
			public static final String INACTIVE = "INACTIVE".intern();
			public static final String NOT_APPLICABLE = "NOT_APPLICABLE".intern();
		}
		public static class SwatchColorEnum
		{
			public static final String BLACK = "BLACK".intern();
			public static final String BLUE = "BLUE".intern();
			public static final String BROWN = "BROWN".intern();
			public static final String GREEN = "GREEN".intern();
			public static final String GREY = "GREY".intern();
			public static final String ORANGE = "ORANGE".intern();
			public static final String PINK = "PINK".intern();
			public static final String PURPLE = "PURPLE".intern();
			public static final String RED = "RED".intern();
			public static final String SILVER = "SILVER".intern();
			public static final String WHITE = "WHITE".intern();
			public static final String YELLOW = "YELLOW".intern();
		}
	}
	public static class Relations
	{
		public static final String MEALECULEINFOTOMEALECULEQUOTIENTDATAS = "MealeculeInfoToMealeculeQuotientDatas".intern();
		public static final String MEALECULEQUOTIENTTOPRODUCT = "MealeculeQuotientToProduct".intern();
		public static final String PRODUCTTOINGREDIENTS = "ProductToIngredients".intern();
	}
	
	protected GeneratedMealeculeCoreConstants()
	{
		// private constructor
	}
	
	
}
