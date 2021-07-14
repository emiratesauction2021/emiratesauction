package com.ea.emiratesauction.core.date.date_formatter

import com.ea.emiratesauction.core.date.date_format.ASDate
import java.util.*

interface DateFormatterInterface {

    fun getFormattedDate(
        date: ASDate,
        toFormat: String = "yyyy-MM-dd",
        local: Locale =  Locale.US

    ): String?

    fun getDate(date: ASDate): Date

    fun isAfter(date1: ASDate, date2: ASDate): Boolean

    fun isBefore(date1: ASDate, date2: ASDate): Boolean

    fun isBetween(date1: ASDate, date2: ASDate, date3: ASDate): Boolean

}