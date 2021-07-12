package com.ea.emiratesauction.core.date

import android.util.Log
import org.joda.time.DateTimeZone
import org.joda.time.chrono.ISOChronology
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Formatter {
    fun getFormattedDate(
        date: String,
        toFormat: String,
        local: Locale =  Locale("ar")

    ): String? {

            list.forEach { fromFormat ->
                try {
                    var df = SimpleDateFormat(fromFormat)
                    val newDate = df.parse(date)

                    df = SimpleDateFormat(toFormat, Locale.ENGLISH)
                    return df.format(newDate)
                }catch (e:Exception){ }

            }

        return ""
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

                        df = SimpleDateFormat(fromFormat, Locale("ar"))

                        Log.d(TAG, ": ${df.format(newDate)}")

                    }catch (e:Exception){ }

            }
        }




//
//        Log.d(TAG, ": $v1")
//        Log.d(TAG, ": $v2")
//        Log.d(TAG, ": $v3")


        testLib()
    }

    private fun testLib() {
        val rfc3339Formatter1: DateTimeFormatter = DateTimeFormat
            .forPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSSZ") //                                                        .withZone(DateTimeZone.getDefault())
            .withZone(DateTimeZone.UTC)
            .withLocale(Locale.US)
            .withChronology(ISOChronology.getInstance())


    }


}

val list by lazy {
    listOf(
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
        "yyyy-MM-dd",
        "h:mm:ss A",
        "h:mm A",
        "dd-MM-yyyy",
        "MM-dd-yyyy",
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
