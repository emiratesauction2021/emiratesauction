package com.ea.emiratesauction.core.date.date_format

import com.ea.emiratesauction.core.date.style.ASDateStyleInterface
import java.text.SimpleDateFormat
import java.util.*


sealed class ASDate : ASDateInterface{

    class Date(val date: java.util.Date) : ASDate() {
        override var getTimeInMS: Long = date.time
        override fun getDateValue(): java.util.Date {
            return date
        }
    }

    class LongDate(val date: Long) : ASDate() {
        override var getTimeInMS: Long = date
        override fun getDateValue(): java.util.Date {
            return Date(date)
        }
    }

    class StringDate(val date: String, val fromFormat: String) : ASDate() {
        override var getTimeInMS: Long = 0
            get() {
                return try {
                    val df = SimpleDateFormat(fromFormat)
                    val newDate = df.parse(date)

                    newDate?.time ?: 0
                } catch (e: Exception) {
                    0
                }
            }


        override fun getDateValue(): java.util.Date {
            return Date(getTimeInMS)
        }

    }

    class StringBuilderDate(val formateList: List<ASDateStyleInterface>, val date: String) :
        ASDate() {
        override var getTimeInMS: Long = 0
            get() {

                val format = formateList.joinToString(
                    transform = { it.value },
                    separator = ""
                )

                var df = SimpleDateFormat(format)
                val newDate = df.parse(date)
                return newDate?.time ?: 0

            }

        override fun getDateValue(): java.util.Date {
            return Date(getTimeInMS)
        }
    }
}
