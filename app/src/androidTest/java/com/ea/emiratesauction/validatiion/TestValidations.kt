package com.ea.emiratesauction.validatiion

import com.ea.emiratesauction.BaseHiltTest
import com.ea.emiratesauction.core.validation.manager.ValidationManager
import com.ea.emiratesauction.core.validation.manager.ValidationStyle
import com.ea.emiratesauction.core.validation.results.RulesError
import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.rules.EmailValidatorRules
import com.ea.emiratesauction.core.validation.rules.NumericValidatorRules
import com.ea.emiratesauction.core.validation.rules.StringsRules
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class TestValidations :BaseHiltTest(){
    @Inject lateinit var manager: ValidationManager


    @Test
    fun validationGroupOfInputsWithInvalidResults() {
        val validationResult =
            manager.validate<ValidationStyle.Group, ValidationResource.GroupResult>(
                arrayListOf(
                    Pair("abc", StringsRules()),
                    Pair("abc.com", EmailValidatorRules()),
                    Pair("12t3", NumericValidatorRules())
                ), ValidationStyle.Group
            )
        val messages = arrayListOf<String>()
        validationResult.results.map {
            when (it) {
                RulesError.EmailError -> messages.add("Mail Invalid")
                RulesError.NumericError -> messages.add("number Invalid")
                RulesError.StringsError -> messages.add("string Invalid")
                else -> {
                }

            }
        }
        assertEquals(messages.size, 3)
    }

    @Test
    fun validationGroupOfInputsWithValidResults() {
        val validationResult =
            manager.validate<ValidationStyle.Group, ValidationResource.GroupResult>(
                arrayListOf(
                    Pair("abc123456798", StringsRules()),
                    Pair("abc@c.com", EmailValidatorRules()),
                    Pair("123", NumericValidatorRules())
                ), ValidationStyle.Group
            )
        val messages = arrayListOf<String>()
        validationResult.results.map {
            when (it) {
                RulesError.EmailError -> messages.add("Mail Invalid")
                RulesError.NumericError -> messages.add("number Invalid")
                RulesError.StringsError -> messages.add("string Invalid")
                RulesError.StringsError -> messages.add("string Invalid")
                else -> {
                }

            }
        }
        assertEquals(messages.size, 0)
    }

    @Test
    fun validationInputWithGroupOfRulesWithInValidResults() {
        val validationResult =
            manager.validate<ValidationStyle.Group, ValidationResource.GroupResult>(
                "abc", arrayListOf(
                    StringsRules(),
                    EmailValidatorRules(),
                    NumericValidatorRules()
                ), ValidationStyle.Group
            )
        val messages = arrayListOf<String>()
        validationResult.results.map {
            when (it) {
                RulesError.EmailError -> messages.add("Mail Invalid")
                RulesError.NumericError -> messages.add("number Invalid")
                RulesError.StringsError -> messages.add("string Invalid")
                else -> {
                }
            }
        }
        assertEquals(messages.size, 3)
    }

    @Test
    fun validationInputWithGroupOfRulesWithValidResults() {
        val validationResult =
            manager.validate<ValidationStyle.Group, ValidationResource.GroupResult>(
                "abcbd@abc.com", arrayListOf(
                    StringsRules(),
                    EmailValidatorRules()
                ), ValidationStyle.Group
            )
        val messages = arrayListOf<String>()
        validationResult.results.map {
            when (it) {
                RulesError.EmailError -> messages.add("Mail Invalid")
                RulesError.NumericError -> messages.add("number Invalid")
                RulesError.StringsError -> messages.add("string Invalid")
                else -> {
                }
            }
        }
        assertEquals(messages.size, 0)
    }

    @Test
    fun validationInputWithGroupOfRulesWithValidResult() {
        val validationResult =
            manager.validate<ValidationStyle.Ordered, ValidationResource.OrderedResult>(
                "abcb@d.com", arrayListOf(
                    StringsRules(),
                    EmailValidatorRules()
                ), ValidationStyle.Ordered
            )
        assertEquals(RulesError.Empty,validationResult.result)
    }

    @Test
    fun validationInputWithGroupOfRulesWithInValidResult() {
        var message = "Valid"
        val validationResult =
            manager.validate<ValidationStyle.Ordered, ValidationResource.OrderedResult>(
                "abcbd.com", arrayListOf(
                    StringsRules(),
                    EmailValidatorRules()
                ), ValidationStyle.Ordered
            )

        when (validationResult.result) {
            RulesError.EmailError -> {
                message = "mail error"
            }
            RulesError.NumericError -> {
                message = "number error"
            }
            RulesError.PasswordError -> {
                message = "password error"
            }
            RulesError.StringsError -> {
                message = "string error"
            }
        }
        assertEquals("mail error",message)
    }

}