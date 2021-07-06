package com.ea.emiratesauction.core.validation.manager

import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.results.ValidationResults
import com.ea.emiratesauction.core.validation.rules.ValidationRule
import javax.inject.Inject

/**
 * ValidationManager which validate the inputs by applying list of rules
 * the result of validation is two type
 * SingleResult {return on first case that is not valid}
 * Or
 * GroupResult {return all invalid cases result }
 * */
class ValidationManager @Inject constructor() : ValidationManagerInterface {

    /**
     * @param input the entered value from editText
     * @param rules rules which will be applied on the input inherited from ValidationRule
     * @param style which can be :
     *          Single {return with first case not valid SingleResult}
     *          Group  {return all invalid cases together GroupResult}
     * @return  GeneralValidationResult{Single or Group} based on the style
     **/
    override fun validate(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): ValidationResource {
        return getGeneralRes(input, rules, style)
    }

    /**
     * It just return single or group Result
     * */
    override fun getGeneralRes(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): ValidationResource {
        return when (style) {
            ValidationStyle.Group -> getGroupResultsValidation(rules, input)

            ValidationStyle.Single -> getSingleResultValidation(rules, input)

        }
    }

    /**
     * @param rules which will be applied on the input
     * @param input that user will type it
     * @return GroupResult<List<ValidationRule>> which hold all invalid rules
     * @see ValidationRule
     * */
    override fun getGroupResultsValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource {
        val results = arrayListOf<String>()
        rules.forEach { rule ->
            when (val res = rule.apply(input)) {
                is ValidationResults.InValid -> results.add(res.error)
            }
        }
        return ValidationResource.GroupResult(
            results
        )
    }

    /**
     * @param rules which will be applied on the input
     * @param input that user will type it
     * @return SingleResult<ValidationRule> which hold only first invalid case
     * @see ValidationRule
     * */
    override fun getSingleResultValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource {
        var result: ValidationResource = ValidationResource.Empty
        rules.first { rule ->
            when (val res = rule.apply(input)) {
                is ValidationResults.InValid -> {
                    result = ValidationResource.SingleResult(res.error)
                    true
                }
                else -> false
            }
        }

        return result
    }
}
