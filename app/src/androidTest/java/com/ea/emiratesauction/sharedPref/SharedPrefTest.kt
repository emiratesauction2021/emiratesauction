package com.ea.emiratesauction.sharedPref

import com.ea.emiratesauction.core.deviceData.manager.DevicePersistenceManager
import com.ea.emiratesauction.core.deviceData.manager.PersistenceType
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
@HiltAndroidTest
class SharedPrefTest {
    @Inject lateinit var manager: DevicePersistenceManager

    @get:Rule var hiltRule = HiltAndroidRule(this)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun normal_shared_pref_data_save_normal_object_data() = runBlocking(IO + Job()) {
        val user = TempUser()
        manager.save("TEST_USER", user, PersistenceType.NORMAL)
        delay(800)
        val savedUser = manager.get<TempUser>("TEST_USER", PersistenceType.NORMAL)
        Assert.assertEquals(savedUser, user)
    }

    @Test
    fun normal_shared_pref_data_save_normal_int_data() = runBlocking(Dispatchers.Default) {
        manager.save("TEST_INT", 25, PersistenceType.NORMAL)
        delay(900)
        Assert.assertEquals(manager.get<Int>("TEST_INT", PersistenceType.NORMAL), 25)
    }

    @Test
    fun normal_shared_pref_data_remove_normal_data() = runBlocking(Dispatchers.Default) {
        manager.delete("TEST_INT", PersistenceType.NORMAL)
        delay(900)
        Assert.assertEquals(manager.get<Int>("TEST_INT", PersistenceType.NORMAL), null)
    }

    @Test
    fun normal_shared_pref_data_clear_normal_data() = runBlocking(Dispatchers.Default) {
        manager.clear(PersistenceType.NORMAL)
        delay(900)
        Assert.assertEquals(manager.get<Int>("TEST_INT", PersistenceType.NORMAL), null)
    }
}