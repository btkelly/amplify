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

import android.annotation.SuppressLint;

import com.github.stkent.amplify.helpers.BaseTest;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaximumCountCheckTest extends BaseTest {

    @SuppressLint("Assert")
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testThatCheckBlocksPromptIfCountThresholdHasBeenExceeded() {
        // Arrange
        final int maximumEventCount = 7;
        final int currentEventCount = 9;
        assert currentEventCount > maximumEventCount;

        final MaximumCountCheck maximumCountCheck = new MaximumCountCheck(maximumEventCount);

        // Act
        final boolean checkShouldAllowFeedbackPrompt
                = maximumCountCheck.shouldAllowFeedbackPrompt(currentEventCount);

        // Assert
        assertFalse(
                "Feedback prompt should be blocked if the count threshold has been exceeded",
                checkShouldAllowFeedbackPrompt);
    }

    @SuppressLint("Assert")
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testThatCheckAllowsPromptIfCountThresholdHasNotBeenExceeded() {
        // Arrange
        final int maximumEventCount = 7;
        final int currentEventCount = 2;
        assert currentEventCount < maximumEventCount;

        final MaximumCountCheck maximumCountCheck = new MaximumCountCheck(maximumEventCount);

        // Act
        final boolean checkShouldAllowFeedbackPrompt
                = maximumCountCheck.shouldAllowFeedbackPrompt(currentEventCount);

        // Assert
        assertTrue(
                "Feedback prompt should be allowed if the count threshold has not been exceeded",
                checkShouldAllowFeedbackPrompt);
    }

}
