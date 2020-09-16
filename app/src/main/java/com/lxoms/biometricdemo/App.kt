package com.lxoms.biometricdemo

import android.app.Application
import android.content.Context


/**
 * @date      2020/9/16
 * @author    Pengshuwen
 * @describe
 */
class App : Application() {


    companion object {
        var mApplication: Context? = null

        fun getContext(): Context? {
            return mApplication
        }

    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this;
    }

    fun getContext(): Context = applicationContext

}