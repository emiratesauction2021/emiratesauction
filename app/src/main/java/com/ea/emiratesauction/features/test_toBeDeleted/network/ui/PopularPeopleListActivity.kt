package com.ea.emiratesauction.features.test_toBeDeleted.network.ui

import androidx.appcompat.app.AlertDialog
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.deviceData.manager.DevicePersistenceManager
import com.ea.emiratesauction.core.deviceData.manager.PersistenceType
import com.ea.emiratesauction.core.logger.Emojis
import com.ea.emiratesauction.core.logger.LogType
import com.ea.emiratesauction.core.logger.LoggingManager
import com.ea.emiratesauction.core.logger.log
import com.ea.emiratesauction.core.validation.manager.ValidationManager
import com.ea.emiratesauction.core.validation.manager.ValidationStyle
import com.ea.emiratesauction.core.validation.results.RulesError
import com.ea.emiratesauction.core.validation.results.ValidationResource
import com.ea.emiratesauction.core.validation.rules.EmailValidatorRules
import com.ea.emiratesauction.core.validation.rules.NumericValidatorRules
import com.ea.emiratesauction.core.validation.rules.StringsRules
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

    @Inject lateinit var validationManager: ValidationManager
    @Inject lateinit var manager: DevicePersistenceManager
    override fun fragment(): BaseFragment {

        GlobalScope.launch(IO) {
            setData()
            delay(1000)
            showData()
        }

        //validateInputWithGroupOfRules()
        validateListOfInputsWithGroupOfRules()
        validateInputWithGroupOfRules()
//        printMessage(messageObj = (validationResult as ValidationResource.OrderedResult).result)


//        when (validationResult) {
//            is GeneralValidationResult.GroupResult -> {
//                printMessage(messageObj = validationResult.results)
//            }
//            is GeneralValidationResult.OrderedResult -> {
//                printMessage(messageObj = validationResult.result)
//            }
//            else -> {
//                printMessage(messageObj = "Input Is Valid")
//            }
//
//        }


        return PupularPeopleListFragment()
    }


    private fun validateListOfInputsWithGroupOfRules() {
        val validationResult =
            validationManager.validate<ValidationStyle.Group, ValidationResource.GroupResult>(
                arrayListOf(
                    Pair("abc", StringsRules()),
                    Pair("abc.com", EmailValidatorRules()),
                    Pair("12t3", NumericValidatorRules())
                ), ValidationStyle.Group
            )
        val messages = arrayListOf<String>()
        validationResult.results.map {
            when (it) {
                RulesError.EmailError -> {
                    messages.add("Mail Invalid")
                }
                RulesError.PasswordError -> {
                    messages.add("Password Invalid")
                }

                RulesError.NumericError -> {
                    messages.add("number Invalid")
                }
                RulesError.StringsError -> {
                    messages.add("string Invalid")
                }else->{}
            }
        }
        log.debug(message = messages)
    }

    private fun validateInputWithGroupOfRules() {
        val validationResult =
            validationManager.validate<ValidationStyle.Ordered, ValidationResource.OrderedResult>(
                "test@ss.com", arrayListOf(
                    //StringsRules(),
                   // NumericValidatorRules(),
                    EmailValidatorRules()
                ), ValidationStyle.Ordered
            )
        val messages = arrayListOf<String>()
//        validationResult.results.map {
//            when (it) {
//                RulesError.EmailError -> {
//                    messages.add("Mail Invalid")
//                }
//                RulesError.PasswordError -> {
//                    messages.add("Password Invalid")
//                }else->{}
//            }
//        }
        log.debug(message = validationResult.result)
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
        log.debug(
            message = "${user}\n$str\n$strN\nNum = $num",
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