package com.ea.emiratesauction.core.validation.rules

import com.ea.emiratesauction.core.validation.results.ValidationResults
/**
 * ValidationRule that apply the rule
 * */
interface ValidationRule {
    /**
     * @param input {user input}
     * @return ValidationResult which will be Valid or Invalid
     * */
    fun apply(input: String): ValidationResults
}