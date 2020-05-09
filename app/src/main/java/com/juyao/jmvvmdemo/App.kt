package com.juyao.jmvvmdemo

import android.app.Application
import com.tencent.smtt.sdk.QbSdk

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true)
        QbSdk.initX5Environment(this,object:QbSdk.PreInitCallback{
            override fun onCoreInitFinished() {

            }

            override fun onViewInitFinished(p0: Boolean) {
            }
        })
    }
}