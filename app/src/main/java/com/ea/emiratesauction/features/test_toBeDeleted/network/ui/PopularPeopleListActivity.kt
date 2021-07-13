package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import android.util.Log
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.date.date_format.ASDate
import com.ea.emiratesauction.core.date.fate_formatter.DateFormatterInterface
import com.ea.emiratesauction.core.date.fate_formatter.Formatter
import com.ea.emiratesauction.core.date.style.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {

    override fun fragment(): BaseFragment {
        testDateUtil()
        return PupularPeopleListFragment()

    }

    private fun testDateUtil() {
        val formatter: DateFormatterInterface = Formatter()
        val v1 = formatter.getFormattedDate(ASDate.LongDate(Calendar.getInstance().timeInMillis))
        val v2 = formatter.getFormattedDate(ASDate.Date(Date()))
        val v3 = formatter.getFormattedDate(ASDate.StringDate("28-10-1990", "dd-MM-yyyy"))
        val v4 = formatter.getFormattedDate(
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

        Log.d("TAGTAG date", "test: \n $v1 \n $v2 \n $v3 \n $v4 ")
    }
}