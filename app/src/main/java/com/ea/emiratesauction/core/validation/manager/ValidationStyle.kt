package com.ea.emiratesauction.core.validation.manager

/**
 * ValidationStyle which hold types of result that can be returned from
 * the validation
 * */
sealed class ValidationStyle {
    /**
     * Ordered this type get first rule result in case of invalid input
     * */
    object Ordered : ValidationStyle()

    /**
     * Group this type get all results from the rules together
     * */
    object Group : ValidationStyle()
}