package com.example.composetestingplayground

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composetestingplayground.ui.theme.ComposeTestingPlaygroundTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserFormTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun userForm_displayInitialState() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        composeTestRule.onNodeWithTag("form_title").assertIsEnabled()

        composeTestRule.onNodeWithTag("name_field").assertIsEnabled()

        composeTestRule.onNodeWithTag("email_field").assertIsEnabled()

        composeTestRule.onNodeWithTag("submit_button").assertTextEquals("Submit").assertIsEnabled()
    }

    @Test
    fun userForm_displaysCorrectTitle() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        composeTestRule.onNodeWithText("User Form").assertExists().assertTextEquals("User Form")
    }

    @Test
    fun userForm_allElementsAreDisplayed() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Verify all form elements are displayed
        composeTestRule.onNodeWithText("User Form").assertExists().assertIsDisplayed()

        composeTestRule.onNodeWithText("Name").assertExists().assertIsDisplayed()

        composeTestRule.onNodeWithText("Email").assertExists().assertIsDisplayed()

        composeTestRule.onNodeWithText("Submit").assertExists().assertIsDisplayed()
    }

    @Test
    fun userForm_nameFieldAcceptsInput() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        composeTestRule.onNodeWithTag("name_field").performTextInput("John Doe")

        composeTestRule.onNodeWithTag("name_field").assertExists()
    }

    @Test
    fun userForm_submitButtonWorks() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Fill form
        composeTestRule.onNodeWithText("Name").performTextInput("Submit Test")

        composeTestRule.onNodeWithText("Email").performTextInput("submit@example.com")

        // Submit form
        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission result
        composeTestRule.onNodeWithText("Submitted: Submit Test (submit@example.com)").assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun userForm_submissionShowsCorrectData() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Fill form with specific data
        composeTestRule.onNodeWithText("Name").performTextInput("Alice Johnson")

        composeTestRule.onNodeWithText("Email").performTextInput("alice@company.com")

        // Submit form
        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission shows correct data
        composeTestRule.onNodeWithText("Submitted: Alice Johnson (alice@company.com)")
            .assertExists().assertTextEquals("Submitted: Alice Johnson (alice@company.com)")
    }

    @Test
    fun userForm_clearAndRefill() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Fill form initially
        composeTestRule.onNodeWithText("Name").performTextInput("First User")

        composeTestRule.onNodeWithText("Email").performTextInput("first@example.com")

        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify initial submission
        composeTestRule.onNodeWithText("Submitted: First User (first@example.com)").assertExists()

        // Clear and refill
        composeTestRule.onNodeWithText("Name").performTextReplacement("")

        composeTestRule.onNodeWithText("Name").performTextInput("Second User")

        composeTestRule.onNodeWithText("Email").performTextReplacement("")

        composeTestRule.onNodeWithText("Email").performTextInput("second@example.com")

        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify new submission
        composeTestRule.onNodeWithText("Submitted: Second User (second@example.com)").assertExists()
    }

    @Test
    fun userForm_emptySubmission() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Submit form without filling fields
        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission with empty fields
        composeTestRule.onNodeWithText("Submitted:  ()").assertExists()
    }

    @Test
    fun userForm_partialFilling() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Fill only name field
        composeTestRule.onNodeWithText("Name").performTextInput("Partial User")

        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission with partial data
        composeTestRule.onNodeWithText("Submitted: Partial User ()").assertExists()
    }

    @Test
    fun userForm_longTextInput() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Test with very long text
        val longName = "A".repeat(100)
        val longEmail = "very.long.email.address.that.might.cause.issues@verylongdomainname.com"

        composeTestRule.onNodeWithText("Name").performTextInput(longName)

        composeTestRule.onNodeWithText("Email").performTextInput(longEmail)

        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission with long text
        composeTestRule.onNodeWithText("Submitted: $longName ($longEmail)").assertExists()
    }

    @Test
    fun userForm_specialCharacters() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Test with special characters
        val specialName = "Test@#$%^&*()_+-=[]{}|;':\",./<>?"
        val specialEmail = "test+special@example-domain.co.uk"

        composeTestRule.onNodeWithText("Name").performTextInput(specialName)

        composeTestRule.onNodeWithText("Email").performTextInput(specialEmail)

        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission with special characters
        composeTestRule.onNodeWithText("Submitted: $specialName ($specialEmail)").assertExists()
    }

    @Test
    fun userForm_unicodeCharacters() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Test with unicode characters
        val unicodeName = "José María 中文 🚀"
        val unicodeEmail = "josé@café.com"

        composeTestRule.onNodeWithText("Name").performTextInput(unicodeName)

        composeTestRule.onNodeWithText("Email").performTextInput(unicodeEmail)

        composeTestRule.onNodeWithText("Submit").performClick()

        // Verify submission with unicode characters
        composeTestRule.onNodeWithText("Submitted: $unicodeName ($unicodeEmail)").assertExists()
    }

    @Test
    fun userForm_rapidSubmit() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        // Fill form
        composeTestRule.onNodeWithText("Name").performTextInput("Rapid User")

        composeTestRule.onNodeWithText("Email").performTextInput("rapid@example.com")

        // Rapid submit clicks
        repeat(5) {
            composeTestRule.onNodeWithText("Submit").performClick()
        }

        // Verify submission result
        composeTestRule.onNodeWithText("Submitted: Rapid User (rapid@example.com)").assertExists()
    }

    @Test
    fun userForm_emailFieldAcceptanceEmailFormat() {
        composeTestRule.setContent {
            ComposeTestingPlaygroundTheme {
                UserForm()
            }
        }

        val emailFormats = listOf(
            "user@example.com",
            "user.name@example.com",
            "user+tag@example.com",
            "user@sub.example.com"
        )

        for (email in emailFormats) {
            composeTestRule.onNodeWithText("Email").performTextReplacement("")

            composeTestRule.onNodeWithText("Email").performTextInput(email)

            composeTestRule.onNodeWithText("Submit").performClick()

            composeTestRule.onNodeWithText("Submitted:  ($email)").assertExists()
        }
    }
}