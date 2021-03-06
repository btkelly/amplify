/**
 * Copyright 2015 Stuart Kent
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.github.stkent.amplify.tracking.checks;

import com.github.stkent.amplify.helpers.BaseTest;
import com.github.stkent.amplify.tracking.interfaces.IEnvironmentCapabilitiesProvider;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class GooglePlayStoreIsAvailableCheckTest extends BaseTest {

    private GooglePlayStoreIsAvailableCheck googlePlayStoreIsAvailableCheck;

    @Mock
    private IEnvironmentCapabilitiesProvider mockEnvironmentInfoProvider;

    @Override
    public void localSetUp() {
        googlePlayStoreIsAvailableCheck = new GooglePlayStoreIsAvailableCheck();
    }

    @Test
    public void testThatEnvironmentCheckIsMetIfGooglePlayStoreIsInstalledOnDevice() {
        // Arrange
       when(mockEnvironmentInfoProvider.isGooglePlayStoreInstalled()).thenReturn(true);

        // Act
        final boolean isEnvironmentCheckMet =
                googlePlayStoreIsAvailableCheck.shouldAllowFeedbackPrompt(mockEnvironmentInfoProvider);

        // Assert
        assertTrue(
                "Environment check should be met",
                isEnvironmentCheckMet);
    }

    @Test
    public void testThatEnvironmentCheckIsNotMetIfGooglePlayStoreIsNotInstalledOnDevice() {
        // Arrange
        when(mockEnvironmentInfoProvider.isGooglePlayStoreInstalled()).thenReturn(false);

        // Act
        final boolean isEnvironmentCheckMet =
                googlePlayStoreIsAvailableCheck.shouldAllowFeedbackPrompt(mockEnvironmentInfoProvider);

        // Assert
        assertFalse(
                "Environment check should not be met",
                isEnvironmentCheckMet);
    }

}
