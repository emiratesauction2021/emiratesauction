package com.ea.emiratesauction.core.validation

import androidx.core.util.PatternsCompat
import com.ea.emiratesauction.core.validation.InputsConstants.EMAIL_PATTERN
import com.ea.emiratesauction.core.validation.InputsConstants.PASSWORD_PATTERN
import com.ea.emiratesauction.core.validation.InputsConstants.PHONE_PATTERN
import java.util.regex.Pattern

/**
 * @param regexPattern check if the String is match the sent pattern
 * it maybe Mail,Pass,Phone, or any regex pattern
 * */
fun String.isPatternMatches(regexPattern: String): Boolean {
    val pattern = Pattern.compile(regexPattern)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

/**
 * It validates the length for the string if it is equal to X val
 * */
fun String.isLengthValid(length: Int) = this.length == length

/**
 * It validates  the string if it is in the range
 * */
fun String.isInRange(min: Int, max: Int) = this.length in min..max

/**
 * It validate the string if it is only numbers
 * */
fun String.isNumeric() = this.matches(Regex("[0-9]+"))

/**
 * It validate the string is  an email with kotlin PatternCompat class
 * */
fun String.isEmailFormValid() = PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

/**
 * It validate the string is  a web with kotlin PatternCompat class
 * */
fun String.isWebUrlFormValid() = PatternsCompat.WEB_URL.matcher(this).matches()

fun String.isNull() = this.isBlank()

fun String.isEqual(st: String) = this == st

fun String.isContains(value: String, start: Int, end: Int, isSensitive: Boolean = false): Boolean {
    val length = this.length
    if (end <= length) {
        val subStr = this.substring(start, end) ?: ""
        return subStr.contains(value, !isSensitive)
    }
    return false
}

fun String.isContains(str: String, isSensitive: Boolean = false) =
    this.contains(str, !isSensitive)


fun String.isPhoneValid() = this.isPatternMatches(PHONE_PATTERN)

object InputsConstants {
    const val PASSWORD_PATTERN = "[a-zA-Z0-9\\!\\@\\#\\$]{5,20}"
    const val PHONE_PATTERN =
        "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*\$\n"
    const val EMAIL_PATTERN =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
}

/*fun main() {
    val mail = "m@m.com".isPatternMatches(EMAIL_PATTERN)
    val mail2 = "msassa@.com".isEmailFormValid()
    val pass = "1666@lkA3".isPatternMatches(PASSWORD_PATTERN)
    val web = "www.google.com".isWebUrlFormValid()
    val match = "abd".isLengthValid(4)
    val range = "abcdegf".isInRange(15, 25)
    val num = "1w32".isNumeric()
    println("mail 1-> $mail")
    println("mail 2-> $mail2")
    println("pass 1-> $pass")
    println("web-> $web")
    println("match case-> $mail")
    println("is in range ->$range")
    println("is numeric ->$num")
    println(num)

}*/
