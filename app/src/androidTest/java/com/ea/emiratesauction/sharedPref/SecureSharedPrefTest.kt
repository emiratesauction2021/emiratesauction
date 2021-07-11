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
class SecureSharedPrefTest {
    @Inject lateinit var manager: DevicePersistenceManager

    @get:Rule var hiltRule = HiltAndroidRule(this)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun secured_data_save_secure_object_data() = runBlocking(IO + Job()) {
        val user = TempUser()
        manager.save("TEST_USER", user, PersistenceType.SECURE)
        delay(800)
        val savedUser = manager.get<TempUser>("TEST_USER", PersistenceType.SECURE)
        Assert.assertEquals(savedUser, user)

    }

    @Test
    fun secured_data_save_secure_int_data() = runBlocking(Dispatchers.Default) {
        manager.save("TEST_INT", 25, PersistenceType.SECURE)
        delay(900)
        Assert.assertEquals(manager.get<Int>("TEST_INT", PersistenceType.SECURE), 25)
    }

    @Test
    fun secured_data_remove_secure_data() = runBlocking(Dispatchers.Default) {
        manager.delete("TEST_INT", PersistenceType.SECURE)
        delay(900)
        Assert.assertEquals(manager.get<Int>("TEST_INT", PersistenceType.SECURE), null)
    }

    @Test
    fun secured_data_clear_secure_data() = runBlocking(Dispatchers.Default) {
        manager.clear(PersistenceType.SECURE)
        delay(900)
        Assert.assertEquals(manager.get<Int>("TEST_INT", PersistenceType.SECURE), null)
    }

}

data class TempUser(val id: Int = 55, val name: String = "Test User One ")
