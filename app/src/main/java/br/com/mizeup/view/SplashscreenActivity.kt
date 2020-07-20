package br.com.mizeup.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.mizeup.LoginFormsActivity
import br.com.mizeup.R

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
