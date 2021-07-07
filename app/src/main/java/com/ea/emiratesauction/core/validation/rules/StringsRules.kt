package com.ea.emiratesauction.core.validation.rules

import com.ea.emiratesauction.core.validation.isInRange
import com.ea.emiratesauction.core.validation.results.RulesError
import com.ea.emiratesauction.core.validation.results.ValidationResults

/**
 * StringsRules which hold Strings validation cases and regex
 * */
class StringsRules : ValidationRule {
    override fun apply(input: String): ValidationResults {
        return if (input.isInRange(10, 100))
            ValidationResults.Valid
        else
            ValidationResults.InValid(RulesError.PasswordError)
    }
}