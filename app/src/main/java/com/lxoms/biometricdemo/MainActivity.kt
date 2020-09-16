package com.lxoms.biometricdemo


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tv_identify.setOnClickListener {
            checkHardwareSupport()
        }
    }


    private fun checkHardwareSupport() {
        val manager = BiometricManager.from(this)

        when (manager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                "生物识别可使用".toast()
                showBiometric()
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                "硬件暂时不可用".toast()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                "未注册生物识别特征".toast()
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                "没有生物识别硬件".toast()
            }
        }
    }


    private val handler = Handler()
    private val executor = Executor { command -> handler.post(command) }


    private fun showBiometric() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("生物特征识别")
                .setDescription("描述")
                .setNegativeButtonText("cancel")
                .setSubtitle("技术")
                .build()
        BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                "识别成功".toast()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                errString.toString().toast()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                "识别失败".toast()
            }
        }).authenticate(promptInfo)

    }


}