package com.ea.emiratesauction.features.test_toBeDeleted.network.ui

import androidx.appcompat.app.AlertDialog
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.deviceData.manager.DevicePersistenceManager
import com.ea.emiratesauction.core.deviceData.manager.PersistenceType
import com.ea.emiratesauction.core.logger.Emojis
import com.ea.emiratesauction.core.logger.LogType
import com.ea.emiratesauction.core.logger.printMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {

    @Inject lateinit var manager: DevicePersistenceManager
    override fun fragment(): BaseFragment {

        GlobalScope.launch(IO) {
            setData()
            delay(1000)
            showData()
        }
        return PupularPeopleListFragment()
    }

    private suspend fun showData() {
        val str = manager.get<String>("EA", PersistenceType.SECURE)
        val strN = manager.get<String>("EA", PersistenceType.NORMAL)
        val num = manager.get<Int>("EA_INT", PersistenceType.SECURE)
        val user = manager.get<User>("User", PersistenceType.SECURE)
        withContext(Main) {
            AlertDialog.Builder(this@PopularPeopleListActivity)
                .setTitle("SharedPreferences")
                .setMessage(
                    "${user}\n$str\n$strN\nNum = $num"
                )
                .setCancelable(false)
                .setPositiveButton(
                    "ok"
                ) { dialog, which ->
                    // Whatever...
                }.show()
        }
        printMessage(
            messageTxt = "${user}\n$str\n$strN\nNum = $num",
            type = LogType.v,
            tag = "MyCustomTag",
            emojiUnicode = 0x1F525,
            emoji = Emojis.Sad

        )


    }

    private fun setData() {
        manager.update("EA", "Secure String", PersistenceType.SECURE)
        manager.update("EA", "Normal String ", PersistenceType.NORMAL)
        manager.save("EA_INT", 2021, PersistenceType.SECURE)
        manager.save("User", User(), PersistenceType.SECURE)
    }

    data class User(val id: Int = 5, val name: String = "EmiratesAuction")
}