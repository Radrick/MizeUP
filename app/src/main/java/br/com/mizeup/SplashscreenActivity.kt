package br.com.mizeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashscreenActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        button_email_login.setOnClickListener(this)
        button_other_login.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_email_login) {
            startActivity(Intent(this, EmailLoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
