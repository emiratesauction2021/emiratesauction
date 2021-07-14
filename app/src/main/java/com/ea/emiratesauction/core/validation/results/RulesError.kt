package com.ea.emiratesauction.core.validation.results

/**
 * RulesError which hold all types or errors that rules may return it
 * */
sealed class RulesError {
    object EmailError : RulesError()
    object PasswordError : RulesError()
    object NumericError : RulesError()
    object StringsError : RulesError()
    object Empty : RulesError()
}