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
package com.mealecule.test.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.mealecule.test.constants.YcommercewebservicestestConstants;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class YcommercewebservicestestManager extends GeneratedYcommercewebservicestestManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( YcommercewebservicestestManager.class.getName() );
	
	public static final YcommercewebservicestestManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (YcommercewebservicestestManager) em.getExtension(YcommercewebservicestestConstants.EXTENSIONNAME);
	}
	
}