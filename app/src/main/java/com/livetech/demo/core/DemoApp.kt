package com.livetech.demo.core

import android.app.Application
import com.livetech.demo.core.AppConstants.BASE_URL
import com.livtech.common.core.network.ApiClient

class DemoApp : Application(){
    override fun onCreate() {
        super.onCreate()
        ApiClient.initClient(BASE_URL, ApiAuthenticator.authInterceptor)
    }
}