package com.ea.emiratesauction.core.validation.results

/**
 * ValidationResults Apply the rules on the input will be valid or not
 * */
sealed class ValidationResults {
    object Valid : ValidationResults()
    data class InValid(val en: RulesError) : ValidationResults()
}

sealed class RulesError {
    object EmailError : RulesError()
    object PasswordError : RulesError()
}