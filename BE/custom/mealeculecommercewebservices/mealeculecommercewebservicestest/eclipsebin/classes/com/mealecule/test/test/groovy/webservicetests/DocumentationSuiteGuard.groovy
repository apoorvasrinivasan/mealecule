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
package com.mealecule.test.test.groovy.webservicetests

class DocumentationSuiteGuard {
    private static boolean isSuiteRunning = false;
    public static boolean isSuiteRunning() {
        return isSuiteRunning;
    }

    public static void setSuiteRunning(boolean isSuiteRunning) {
        this.isSuiteRunning = isSuiteRunning;
    }
}
