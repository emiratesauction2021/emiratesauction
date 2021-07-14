package com.ea.emiratesauction.core.date.date_format

import java.util.*

interface ASDateInterface{
    var getTimeInMS: Long
    fun getDateValue(): Date
}