package com.ea.emiratesauction.core.validation.results

/**
* ValidationResults Apply the rules on the input will be valid or not
* */
sealed class ValidationResults {
    object Valid : ValidationResults()
    data class InValid(val error: String) : ValidationResults()
}