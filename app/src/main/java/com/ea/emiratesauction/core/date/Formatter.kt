package com.ea.emiratesauction.core.date

import android.util.Log
import org.joda.time.DateTimeZone
import org.joda.time.chrono.ISOChronology
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Formatter {
    fun getFormattedDate(
        date: String,
        toFormat: String,
        local: Locale =  Locale.ENGLISH

    ): String? {

        list.forEach { fromFormat ->
            try {
                var df = SimpleDateFormat(fromFormat)
                val newDate = df.parse(date)

                df = SimpleDateFormat(toFormat, local)
                return df.format(newDate)
            }catch (e:Exception){ }

        }

        return ""
    }

    fun getFormattedDate(
        date: ASDate,
        toFormat: String = "yyyy-MM-dd",
        local: Locale =  Locale("ar")

    ): String? {
        val df = SimpleDateFormat(toFormat, Locale.US)
        return df.format(Date(date.getTime))
    }

val to = "yyyy.MM.dd G 'at' HH:mm:ss z"
val TAG = "TAGTAG"
    init {
        val v1 = getFormattedDate(
            "Wed, Jul 4, '01",
            "yyyy.MM.dd G 'at' HH:mm:ss z"
        )

        val v2 = getFormattedDate(
            "02001.July.04 AD 12:08 PM",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        )


     val v3 = getFormattedDate(
            "Wed, 4 Jul 2001 12:08:56 -0700",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        )

        list.forEach {f1->
            val date = "Wed, 4 Jul 2001 12:08:56 -0700"
                list.forEach { fromFormat ->
                    try {
                        var df = SimpleDateFormat(f1)
                        val newDate = df.parse(date)

                        df = SimpleDateFormat(fromFormat)

                        Log.d(TAG, ": ${df.format(newDate)}")

                    }catch (e:Exception){ }

            }
        }

        testLib()
    }

    private fun testLib() {
        val rfc3339Formatter1: DateTimeFormatter = DateTimeFormat
            .forPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSSZ") //                                                        .withZone(DateTimeZone.getDefault())
            .withZone(DateTimeZone.UTC)
            .withLocale(Locale.US)
            .withChronology(ISOChronology.getInstance())

       for (i in 0 until(100)){
//           val v = getFormattedDate(ASDate.LongDate(Calendar.getInstance().timeInMillis))
//           val v = getFormattedDate(ASDate.StringDate("28-10-1990", "dd-MM-yyyy"))
//           val v = getFormattedDate(ASDate.Date(Date()))
           val v = getFormattedDate(ASDate.StringBuilderDate(
               listOf(
                   DayStyle.zeroPaddedNumber,
                   Separator.dash,
                   MonthStyle.zeroPaddedNumber,
                   Separator.dash,
                   YearStyle.fourDigits
               )
               ,"28-10-1990"))
           Log.d(TAG, "testLibtestLib:$i $v")
       }

    }


    sealed class ASDate{
        abstract var getTime: Long
        class Date(val date: java.util.Date): ASDate(){
           override var getTime: Long = date.time
        }
        class LongDate(date: Long): ASDate(){
            override var getTime: Long = date
        }
        class StringDate(val date: String, val fromFormat: String): ASDate(){
            override var getTime: Long = 0
            get() {
                    try {
                        var df = SimpleDateFormat(fromFormat)
                        val newDate = df.parse(date)

                        return newDate.time
                    }catch (e:Exception){ }

                return 0
            }
        }
        class StringBuilderDate(val formateList: List<ASDateStyleProtocol>, val date: String): ASDate(){
            override var getTime: Long = 0
            get() {

                val formate = formateList.joinToString(
                    transform = { it.value },
                    separator = ""
                )

                var df = SimpleDateFormat(formate)
                val newDate = df.parse(date)
                return newDate.time

            }
        }
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
