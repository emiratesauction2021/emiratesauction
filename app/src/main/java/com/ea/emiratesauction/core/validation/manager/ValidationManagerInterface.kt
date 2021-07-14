package com.ea.emiratesauction.core.validation.manager

import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.results.ValidationResults
import com.ea.emiratesauction.core.validation.rules.ValidationRule

/**
 * ValidationManager which validate the inputs by applying list of rules
 * the result of validation is two type
 * OrderedResult {return on first case that is not valid}
 * Or
 * GroupResult {return all invalid cases result }
 * */
interface ValidationManagerInterface<in S : ValidationStyle, V : ValidationResource> {

    /**
     * @param input the entered value from editText
     * @param rules rules which will be applied on the input inherited from ValidationRule
     * @param style which can be :
     *          Ordered {return with first case not valid OrderedResult}
     *          Group  {return all invalid cases together GroupResult}
     * @return  GeneralValidationResult{Single or Group} based on the style
     **/
    fun <s : S, v : V> validate(
        input: String,
        rules: List<ValidationRule>,
        style: s
    ): v

    /**
     * @param inputs its list of input with rules
     * @param style which can be :
     *          Single {return with first case not valid OrderedResult}
     *          Group  {return all invalid cases together GroupResult}
     * @return  GeneralValidationResult{Single or Group} based on the style
     **/
    fun <s : S, v : V> validate(
        inputs: List<Pair<String, ValidationRule>>,
        style: s
    ): v

    /**
     * It just return single or group Result
     * */
    fun getGeneralRes(
        input: String,
        rules: List<ValidationRule>,
        style: ValidationStyle
    ): ValidationResource?

    /**
     * @param rules which will be applied on the input
     * @param input that user will type it
     * @return GroupResult<List<ValidationRule>> which hold all invalid rules
     * @see ValidationRule
     * */
    fun getGroupResultsValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource

    /**
     * @param rules which will be applied on the input
     * @param input that user will type it
     * @return OrderedResult<ValidationRule> which hold only first invalid case
     * @see ValidationRule
     * */
    fun getOrderedResultValidation(
        rules: List<ValidationRule>,
        input: String
    ): ValidationResource?
}
