package com.squeezymo.lokalisedemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.lokalise.android.sdk.core.LokaliseContextWrapper


class MainActivity : AppCompatActivity() {

    private lateinit var resourcesClassNameTv: TextView
    private lateinit var testKeyTv: TextView

    override fun attachBaseContext(newBase: Context?) {
        // Inject the Lokalise SDK into the activity context
        super.attachBaseContext(LokaliseContextWrapper.wrap(newBase))
    }

    override fun getResources(): Resources {
        val resources = super.getResources()
        return resources
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resourcesClassNameTv = findViewById(R.id.resources_class_name_tv)
        testKeyTv = findViewById(R.id.test_key_tv)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        resourcesClassNameTv.text = "Using ${resources::class.java.canonicalName}"
        testKeyTv.text = getString(R.string.test_key)
    }

}
