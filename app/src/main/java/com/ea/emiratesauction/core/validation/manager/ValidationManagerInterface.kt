package com.ea.emiratesauction.core.validation.manager

import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.rules.ValidationRule

interface ValidationManagerInterface<in S : ValidationStyle, V : ValidationResource> {

    fun<s:S,v:V> validate(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): v

    fun getGeneralRes(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): ValidationResource?

    fun getGroupResultsValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource

    fun getSingleResultValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource?
}
