package com.ea.emiratesauction.core.validation.results

/**
 * ValidationResults Apply the rules on the input will be valid or not
 * */
sealed class ValidationResults {
    /**
    * Valid If the input passed the rule validations
    * */
    object Valid : ValidationResults()
    /**
     * InValid If the input did not passed the rule validations
     * */
    data class InValid(val en: RulesError) : ValidationResults()
}