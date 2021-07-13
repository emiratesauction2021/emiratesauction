package com.ea.emiratesauction

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
open class BaseHiltTest {
    @get:Rule var hiltRule = HiltAndroidRule(this)
    @Before
    fun init() {
        hiltRule.inject()
    }

}