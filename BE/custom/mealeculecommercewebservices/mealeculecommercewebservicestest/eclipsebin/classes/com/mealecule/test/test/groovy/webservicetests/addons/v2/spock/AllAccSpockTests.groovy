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
package com.mealecule.test.test.groovy.webservicetests.addons.v2.spock


import de.hybris.platform.oauth2.constants.OAuth2Constants
import de.hybris.platform.util.Config
import com.mealecule.test.constants.YcommercewebservicestestConstants
import com.mealecule.test.setup.TestSetupUtils

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite.class)
@Suite.SuiteClasses([SopTest, ExtendedCartV2Tests])
class AllAccSpockTests {
	@BeforeClass
	public static void setUpClass() {
		if (Config.getBoolean("mealeculecommercewebservicestest.enableAccTest", false)) {
			TestSetupUtils.loadData();
			String[] ext = [
				YcommercewebservicestestConstants.EXTENSIONNAME - "test",
				OAuth2Constants.EXTENSIONNAME,
				"acceleratorservices"
			]
			TestSetupUtils.startServer(ext);
		}
	}

	@AfterClass
	public static void tearDown(){
		if (Config.getBoolean("mealeculecommercewebservicestest.enableAccTest", false)) {
			TestSetupUtils.stopServer();
			TestSetupUtils.cleanData();
		}
	}

	@Test
	public static void testing() {
		//dummy test class
	}
}
