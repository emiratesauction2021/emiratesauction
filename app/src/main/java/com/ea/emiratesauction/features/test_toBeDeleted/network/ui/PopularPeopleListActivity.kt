package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.core.common.base.ui.BaseFragment
import com.ea.emiratesauction.core.deviceData.manager.DevicePersistenceManager
import com.ea.emiratesauction.core.deviceData.manager.PersistenceType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {

    @Inject lateinit var manager: DevicePersistenceManager
    override fun fragment(): BaseFragment {
        manager.save("d", "d", PersistenceType.NORMAL)
        manager.get("d",  PersistenceType.SECURE)
        return PupularPeopleListFragment()

    }
}