package com.ea.emiratesauction.core.date.fate_formatter

import android.util.Log
import com.ea.emiratesauction.core.date.date_format.ASDate
import com.ea.emiratesauction.core.date.style.*
import java.text.SimpleDateFormat
import java.util.*

class Formatter: DateFormatterInterface {
    val TAG = "TAGTAG"

    override fun getFormattedDate(
        date: ASDate,
        toFormat: String, // TODO: 7/13/2021 add to enum
        local: Locale

    ): String? {
        val df = SimpleDateFormat(toFormat, local)
        return df.format(date.getDateValue())
    }

    override fun getDate(date: ASDate): Date {
        return date.getDateValue()
    }

    override fun isAfter(date1: ASDate, date2: ASDate): Boolean {
        return date1.getTimeInMS > date2.getTimeInMS
    }

    override fun isBefore(date1: ASDate, date2: ASDate): Boolean {
        return date1.getTimeInMS > date2.getTimeInMS
    }

    override fun isBetween(date1: ASDate, date2: ASDate, date3: ASDate): Boolean {
        return date1.getTimeInMS < date2.getTimeInMS && date2.getTimeInMS > date3.getTimeInMS
    }

    init {
        testLib()
    }

    private  fun testLib() {
//           val v = getFormattedDate(ASDate.LongDate(Calendar.getInstance().timeInMillis))
//           val v = getFormattedDate(ASDate.StringDate("28-10-1990", "dd-MM-yyyy"))
//           val v = getFormattedDate(ASDate.Date(Date()))
        val v = getFormattedDate(
            ASDate.StringBuilderDate(
                listOf(
                    MonthStyle.zeroPaddedNumber,
                    Separator.dash,
                    DayStyle.zeroPaddedNumber,
                    CustomStyle("-"), // same as @Separator.dash
                    YearStyle.fourDigits
                ), "10-28-1990"
            )
        )

        Log.d(TAG, "testLibtestLib: $v")

    }




}

val list by lazy {
    listOf(
        "yyyy-MM-dd'T'HH:mm:ss",
        "dd MMMM yyyy",
        "yyyyMMdd_HHmmss",
        "yyyy-MM-dd hh:mm:ss z",
        "EEE, d MMM yyyy HH:mm:ss Z",
        "EEE, MMM d, ''yy",
        "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
        "yyyyy.MMMMM.dd GGG hh:mm aaa",
        "yyyy'-'MM'-'dd'T'HH':'mm':'ssZ",
        "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSSZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "yyyy-MM-dd HH:mm:ss",
        "dd-MM-yyyy HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "h:mm:ss A",
        "h:mm A",
        "dd-MM-yyyy",
        "MM-dd-yyyy",
        "yyyy-MM-dd",
        "MMMM d, yyyy",
        "MMMM d, yyyy LT",
        "dddd, MMMM D, yyyy LT",
        "yyyyyy-MM-dd",
        "yyyy-MM-dd",
        "yyyy-'W'ww-E",
        "GGGG-'['W']'ww-E",
        "yyyy-'W'ww",
        "GGGG-'['W']'ww",
        "yyyy'W'ww",
        "yyyy-ddd",
        "HH:mm:ss.SSSS",
        "HH:mm:ss",
        "HH:mm",
        "HH"
    )

}
