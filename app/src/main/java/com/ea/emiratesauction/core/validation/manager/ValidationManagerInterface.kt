package com.ea.emiratesauction.core.validation.manager

import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.rules.ValidationRule

interface ValidationManagerInterface {

    fun validate(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): ValidationResource?

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
