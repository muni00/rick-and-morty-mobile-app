package com.muazzeznihalbahadir.invio_case.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.muazzeznihalbahadir.invio_case.R
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.File
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val FILENAME = "my_file.txt"
        val file = File(filesDir, FILENAME)

        if (!file.exists()) {
            // uygulama ilk kez açılıyor
            txtSaplash.text = "Welcome!"
            file.createNewFile()
        }
        else {
            // uygulama daha önce açılmıştı
            txtSaplash.text = "Hello!"
        }

       // txtSaplash.text = "Merhaba"

        Timer().schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)

    }
}