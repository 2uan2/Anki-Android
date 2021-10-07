/*
 *  Copyright (c) 2021 Arthur Milchior <arthur@milchior.fr>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ichi2.anki

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ichi2.testutils.isType
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.full.companionObject

@RunWith(AndroidJUnit4::class)
class OnboardingUtilsTest : RobolectricTest() {

    @Before
    override fun setUp() {
        super.setUp()
        Onboarding.resetOnboardingForTesting() // #9597 - global needs resetting
    }

    @Test
    fun resetResetAllElementsFromOnboarding() {
        val onboardingIdentifierCount = Onboarding::class.companionObject!!.members.filter { it.isFinal && it.isType<String>() }.size
        // featureConstants is internally used in reset()
        val featuresAvailableForReset = OnboardingUtils.featureConstants.size
        assertThat("All onboarding identifiers are available for reset", onboardingIdentifierCount, CoreMatchers.equalTo(featuresAvailableForReset))
    }

    enum class Feature : OnboardingFlag
}
