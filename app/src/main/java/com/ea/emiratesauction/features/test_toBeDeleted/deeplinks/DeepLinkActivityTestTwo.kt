package com.ea.emiratesauction.features.test_toBeDeleted.deeplinks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ea.emiratesauction.R

class DeepLinkActivityTestTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deep_link_test_two)

        val data = intent.extras?.get(EmiratesAuctionDestinationsType.PLATES.name)
        Toast.makeText(this, "${data}", Toast.LENGTH_SHORT).show()
    }
}