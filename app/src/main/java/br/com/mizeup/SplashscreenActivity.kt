package br.com.mizeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        var handler = Handler()
        handler(handler)
    }

    private fun handler(h: Handler) {
        h.postDelayed(Runnable {
            kotlin.run { initializeApp() }
        }, 3000)
    }

    private fun initializeApp(){
        startActivity(Intent(this, LoginFormsActivity::class.java))
        finish()
    }

}
