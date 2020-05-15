package com.squeezymo.lokalisedemo

import android.app.Application
import co.lokalise.android.sdk.LokaliseSDK

class LokaliseDemoApplication : Application() {

    override fun onCreate() {

        super.onCreate()
        // Initialise Lokalise SDK with projects SDK token and project id
        // It is important to call this right after the "super.onCreate()"
        // If you are using AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        // make sure it is called before LokaliseSDK.init()
        LokaliseSDK.init("6f9e78fbf74afe1bbd2f73c3a99f9ccd690df376", "489062175ebe9f28a34f39.21733538", this)

        // Add this only if you want to use pre-release bundles
        LokaliseSDK.setPreRelease(true)

        // Fetch the latest translations from Lokalise (can be called anywhere)
        LokaliseSDK.updateTranslations()
    }

}
