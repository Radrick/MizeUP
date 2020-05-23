package br.com.mizeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login_forms.*
import kotlinx.android.synthetic.main.activity_login_forms.view.*

class LoginFormsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_forms)

        button_email_login.setOnClickListener(this)
        button_google_login.setOnClickListener(this)
        button_apple_login.setOnClickListener(this)
        button_create_account.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v) {
            v.button_email_login -> startActivity(Intent(this, MainActivity::class.java))
            v.button_google_login -> startActivity(Intent(this, MainActivity::class.java))
            v.button_apple_login -> startActivity(Intent(this, MainActivity::class.java))
            v.button_create_account -> startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
