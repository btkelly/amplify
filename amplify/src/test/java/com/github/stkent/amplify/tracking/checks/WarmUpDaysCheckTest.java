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
import com.github.stkent.amplify.utils.time.SystemTimeUtil;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WarmUpDaysCheckTest extends BaseTest {

    @SuppressLint("Assert")
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testThatCheckBlocksPromptIfEventHasNotOccurredYet() {
        // Arrange
        final int warmUpTimeDays = 7;

        final WarmUpDaysCheck warmUpDaysCheck = new WarmUpDaysCheck(warmUpTimeDays);

        // fixme: refactor to avoid this nasty dependency on a magic number
        final long lastEventTime = Long.MAX_VALUE;

        // Act
        final boolean checkShouldAllowFeedbackPrompt
                = warmUpDaysCheck.shouldAllowFeedbackPrompt(lastEventTime);

        // Assert
        assertFalse(
                "Feedback prompt should be blocked if the event has not occurred yet",
                checkShouldAllowFeedbackPrompt);
    }

    @SuppressLint("Assert")
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testThatCheckBlocksPromptIfWarmUpPeriodHasNotPassed() {
        // Arrange
        final int warmUpTimeDays = 7;
        final int daysSinceLastEvent = 2;
        assert daysSinceLastEvent < warmUpTimeDays;

        final WarmUpDaysCheck warmUpDaysCheck = new WarmUpDaysCheck(warmUpTimeDays);
        final long lastEventTime = SystemTimeUtil.currentTimeMillis() - TimeUnit.DAYS.toMillis(daysSinceLastEvent);

        // Act
        final boolean checkShouldAllowFeedbackPrompt
                = warmUpDaysCheck.shouldAllowFeedbackPrompt(lastEventTime);

        // Assert
        assertFalse(
                "Feedback prompt should be blocked if the warm-up period has not passed",
                checkShouldAllowFeedbackPrompt);
    }

    @SuppressLint("Assert")
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testThatCheckAllowsPromptIfWarmUpPeriodHasPassed() {
        // Arrange
        final int warmUpTimeDays = 7;
        final int daysSinceLastEvent = 9;
        assert daysSinceLastEvent > warmUpTimeDays;

        final WarmUpDaysCheck warmUpDaysCheck = new WarmUpDaysCheck(warmUpTimeDays);
        final long lastEventTime = SystemTimeUtil.currentTimeMillis() - TimeUnit.DAYS.toMillis(daysSinceLastEvent);

        // Act
        final boolean checkShouldAllowFeedbackPrompt
                = warmUpDaysCheck.shouldAllowFeedbackPrompt(lastEventTime);

        // Assert
        assertTrue(
                "Feedback prompt should be allowed if the warm-up period has passed",
                checkShouldAllowFeedbackPrompt);
    }

}
