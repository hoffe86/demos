package com.customvoice.app;

import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class  MainService : CarAppService()  {
    override fun createHostValidator(): HostValidator {
        if ((getApplicationInfo().flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        } else {
            return HostValidator.Builder(getApplicationContext())
                    //.addAllowedHosts(com.customvoice.app.R)
                    .build()
        }
    }

    override fun onCreateSession(): Session {
        return MainSession()
    }
}

