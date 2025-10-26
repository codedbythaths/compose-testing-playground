package com.example.composetestingplayground

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composetestingplayground.ui.theme.ComposeTestingPlaygroundTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counterApp_displaysInitialState() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        composeTestRule.onNodeWithTag("counter_text").assertExists().assertIsDisplayed()
            .assertTextEquals("Counter: 0")
    }

    @Test
    fun counterApp_displayCorrectText() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        composeTestRule.onNodeWithText("Counter: 0").assertExists().assertTextEquals("Counter: 0")
    }

    @Test
    fun counterApp_increaseButtonIncrementsCount() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        // Verify initial state
        composeTestRule.onNodeWithText("Counter: 0").assertExists()

        // Click increase button
        composeTestRule.onNodeWithTag("increase_button").performClick()

        // Verify count increased
        composeTestRule.onNodeWithText("Counter: 1").assertExists()
    }

    @Test
    fun counterApp_decreaseButtonDecrementsCount() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: 0")

        composeTestRule.onNodeWithTag("decrease_button").performClick()

        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: -1")
    }

    @Test
    fun counterApp_resetButtonResetsCount() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        repeat(3) {
            composeTestRule.onNodeWithTag("increase_button").performClick()
        }

        composeTestRule.onNodeWithTag("reset_button").performClick()

        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: 0")
    }

    @Test
    fun counterApp_allButtonsAreEnabled() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        // Verify all buttons are enabled using test tags
        composeTestRule.onNodeWithTag("decrease_button").assertExists().assertIsEnabled()

        composeTestRule.onNodeWithTag("increase_button").assertExists().assertIsEnabled()

        composeTestRule.onNodeWithTag("reset_button").assertExists().assertIsEnabled()
    }

    @Test
    fun counterApp_allButtonsAreDisplayed() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        // Verify all buttons are displayed using test tags
        composeTestRule.onNodeWithTag("decrease_button").assertExists().assertIsDisplayed()

        composeTestRule.onNodeWithTag("increase_button").assertExists().assertIsDisplayed()

        composeTestRule.onNodeWithTag("reset_button").assertExists().assertIsDisplayed()
    }


    @Test
    fun counterApp_multipleDecreasesWork() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        // First increase to 5 using test tag
        repeat(5) {
            composeTestRule.onNodeWithTag("increase_button").performClick()
        }

        // Then decrease 3 times using test tag
        repeat(3) {
            composeTestRule.onNodeWithTag("decrease_button").performClick()
        }

        // Verify final count using test tag
        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: 2")
    }

    @Test
    fun counterApp_decreaseFromZero() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        // Try to decrease from 0 using test tag
        composeTestRule.onNodeWithTag("decrease_button").performClick()

        // Verify count goes to -1 using test tag
        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: -1")
    }

    @Test
    fun counterApp_resetFromNegative() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                CounterApp()
            }
        }

        // Decrease to negative using test tag
        repeat(5) {
            composeTestRule.onNodeWithTag("decrease_button").performClick()
        }

        // Verify negative count using test tag
        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: -5")

        // Reset from negative using test tag
        composeTestRule.onNodeWithTag("reset_button").performClick()

        // Verify reset to 0 using test tag
        composeTestRule.onNodeWithTag("counter_text").assertTextEquals("Counter: 0")
    }
}