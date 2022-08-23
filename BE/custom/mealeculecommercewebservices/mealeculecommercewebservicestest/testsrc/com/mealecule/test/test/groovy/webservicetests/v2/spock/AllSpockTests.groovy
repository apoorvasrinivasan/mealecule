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
package com.mealecule.test.test.groovy.webservicetests.v2.spock

import de.hybris.bootstrap.annotations.IntegrationTest
import com.mealecule.test.setup.TestSetupUtils
import com.mealecule.test.test.groovy.webservicetests.v2.spock.access.AccessRightsTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.access.OAuth2Test
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.CartDeliveryTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.CartEntriesTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.CartMergeTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.CartPromotionsTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.CartResourceTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.CartVouchersTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.GuestsTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.OrderPlacementTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.SavedCartFullScenarioTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.carts.SavedCartTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.catalogs.CatalogsResourceTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.customergroups.CustomerGroupsTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.errors.ErrorTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.export.ExportTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.filters.CartMatchingFilterTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.filters.UserMatchingFilterTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.flows.AddressBookFlow
import com.mealecule.test.test.groovy.webservicetests.v2.spock.flows.CartFlowTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.general.StateTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.misc.CardTypesTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.misc.CurrenciesTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.misc.DeliveryCountriesTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.misc.LanguagesTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.misc.LocalizationRequestTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.misc.TitlesTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.orders.OrdersTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.products.ProductResourceTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.products.ProductsStockTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.promotions.PromotionsTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.stores.StoresTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.users.UserAccountTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.users.UserAddressTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.users.UserOrdersTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.users.UserPaymentsTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.users.UsersResourceTest
import com.mealecule.test.test.groovy.webservicetests.v2.spock.general.HeaderTests

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.slf4j.LoggerFactory


@RunWith(Suite.class)
@Suite.SuiteClasses([
	AccessRightsTest, OAuth2Test, StateTest, CartDeliveryTest, CartMergeTest, CartEntriesTest, CartPromotionsTest,
	CartResourceTest, CartVouchersTest, GuestsTest, OrderPlacementTest, CatalogsResourceTest, CustomerGroupsTest, ErrorTest, ExportTest,
	AddressBookFlow, CartFlowTest, CardTypesTest, CurrenciesTest, DeliveryCountriesTest, LanguagesTest, LocalizationRequestTest, TitlesTest,
	OrdersTest, ProductResourceTest, ProductsStockTest, PromotionsTest, SavedCartTest ,SavedCartFullScenarioTest, StoresTest, UserAccountTest,
	UserAddressTest, UserOrdersTest, UserPaymentsTest, UsersResourceTest, CartMatchingFilterTest, UserMatchingFilterTest, HeaderTests])
@IntegrationTest
class AllSpockTests {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AllSpockTests.class)

	@BeforeClass
	public static void setUpClass() {
		TestSetupUtils.loadData();
		TestSetupUtils.startServer();
	}

	@AfterClass
	public static void tearDown(){
		TestSetupUtils.stopServer();
		TestSetupUtils.cleanData();
	}

	@Test
	public static void testing() {
		//dummy test class
	}
}
