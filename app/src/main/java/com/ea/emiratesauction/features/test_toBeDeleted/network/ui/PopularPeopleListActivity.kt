package com.ea.emiratesauction.features.test_toBeDeleted.network.ui


import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.ea.emiratesauction.R
import com.ea.emiratesauction.core.common.base.ui.BaseActivity
import com.ea.emiratesauction.features.test_toBeDeleted.network.viewmodel.PupularPeopleListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PopularPeopleListActivity : BaseActivity() {
    private val viewModel: PupularPeopleListViewModel by viewModels()
    override fun layoutId(): Int {
        return R.layout.activity_base
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(viewModel)

        show_Notification()

    }

    /**
    * This is notifications test method will be removed
    * it just to make sure that is deeplink manager works with notifications
    * */
    @TargetApi(Build.VERSION_CODES.O) @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN) fun show_Notification() {
        val intent = Intent(applicationContext, this::class.java)
        intent.setAction(Intent.ACTION_VIEW);
        intent.data = Uri.parse("www.example.com/plates");
        val CHANNEL_ID = "MYCHANNEL"
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_LOW)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, 0)
        val notification = Notification.Builder(
            applicationContext, CHANNEL_ID
        )
            .setContentText("Heading")
            .setContentTitle("subheading")
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.sym_action_chat, "Title", pendingIntent)
            .setChannelId(CHANNEL_ID)
            .setSmallIcon(android.R.drawable.sym_action_chat)
            .build()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, notification)
    }

    override fun onRetry() {
        TODO("Not yet implemented")
    }

}