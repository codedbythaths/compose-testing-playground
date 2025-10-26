package com.example.composetestingplayground

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composetestingplayground.ui.theme.ComposeTestingPlaygroundTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GreetingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun greeting_displayCorrectText(){
        // Arrange
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                Greeting("World")
            }
        }
        // Act: No action needed for this test

        // Assert: Verify the expected outcome
        composeTestRule
            .onNodeWithText("Hello World!")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun greeting_displayDifferentName(){

        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                Greeting("Alice")
            }
        }

        composeTestRule
            .onNodeWithText("Hello Alice!")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun greeting_displayEmptyName()
    {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                Greeting("")
            }
        }

        composeTestRule
            .onNodeWithText("Hello !")
            .assertExists()
    }

    @Test
    fun greeting_specialCharacters_displayCorrectly(){

        val specialName = "Test@#\$%^&*()_+-=[]{}|;':\\\",./<>?"

        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                Greeting(specialName)
            }
        }

        composeTestRule
            .onNodeWithText("Hello $specialName!")
            .assertExists()
            .assertTextContains("Hello Test@#\$%^&*()_+-=[]{}|;':\\\",./<>?!")
    }

    @Test
    fun greeting_veryLongName_displayCorrectly(){

        val longName = "A".repeat(100) // 100 character name

        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                Greeting(longName)
            }
        }

        composeTestRule
            .onNodeWithText("Hello $longName!")
            .assertExists()
    }
}