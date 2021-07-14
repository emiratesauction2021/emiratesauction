package com.ea.emiratesauction.core.validation.manager

import com.ea.emiratesauction.core.validation.results.RulesError
import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.results.ValidationResults
import com.ea.emiratesauction.core.validation.rules.ValidationRule
import javax.inject.Inject


class ValidationManager @Inject constructor() :
    ValidationManagerInterface<ValidationStyle, ValidationResource> {
    override fun <s : ValidationStyle, v : ValidationResource> validate(
        input: String,
        rules: List<ValidationRule>,
        style: s
    ): v {
       return  getGeneralRes(input, rules, style) as v
    }


    override fun <s : ValidationStyle, v : ValidationResource> validate(
        inputs: List<Pair<String, ValidationRule>>,
        style: s
    ): v {
        val results = arrayListOf<RulesError>()
        inputs.map {
            val (input, rule) = it
            when (val res = rule.apply(input)) {
                is ValidationResults.InValid -> results.add(res.en)
                else -> {
                }
            }
        }
        return ValidationResource.GroupResult(
            results
        ) as v
    }


    override fun getGeneralRes(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): ValidationResource? {
        return when (style) {
            ValidationStyle.Group -> getGroupResultsValidation(rules, input)

            ValidationStyle.Ordered -> getOrderedResultValidation(rules, input)
            else -> return null
        }
    }


    override fun getGroupResultsValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource {
        val results = arrayListOf<RulesError>()
        rules.forEach { rule ->
            when (val res = rule.apply(input)) {
                is ValidationResults.InValid -> results.add(res.en)
            }
        }
        return ValidationResource.GroupResult(
            results
        )
    }

    override fun getOrderedResultValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource {
        var result: ValidationResource = ValidationResource.OrderedResult(RulesError.Empty)
        rules.singleOrNull { rule ->
            when (val res = rule.apply(input)) {
                is ValidationResults.InValid -> {
                    result = ValidationResource.OrderedResult(res.en)
                    true
                }
                else -> false
            }
        }

        return result
    }
}
