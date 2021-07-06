package com.ea.emiratesauction.core.validation.rules

import com.ea.emiratesauction.core.validation.results.ValidationResults
import com.ea.emiratesauction.core.validation.isEmailFormValid

/**
* EmailValidatorRules which hold email validation cases and regex
* */
class EmailValidatorRules : ValidationRule {
    override fun apply(input: String): ValidationResults {
        return if (input.isEmailFormValid())
            ValidationResults.Valid
        else
            ValidationResults.InValid("email is not valid")

    }
}
