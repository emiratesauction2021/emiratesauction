package com.ea.emiratesauction.core.validation.rules

import com.ea.emiratesauction.core.validation.isNumeric
import com.ea.emiratesauction.core.validation.results.ValidationResults

/**
 * NumericValidatorRules which hold Numbers validation cases and regex
 * */
class NumericValidatorRules : ValidationRule {
    override fun apply(input: String): ValidationResults {
        return if (input.isNumeric())
            ValidationResults.Valid
        else
            ValidationResults.InValid("input is not numbers")
    }
}