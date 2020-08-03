package br.com.mizeup.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import br.com.mizeup.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_email.*
import kotlinx.android.synthetic.main.activity_login_email.view.*

class LoginEmailActivity : AppCompatActivity(), View.OnClickListener {

    private var fbAuth = FirebaseAuth.getInstance()

    lateinit var str: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)

        button_login.setOnClickListener(this)
        button_forgot_password.setOnClickListener(this)
        button_other_login.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v) {
            v.button_login -> signIn(
                text_login_email.text.toString(),
                text_login_password.text.toString()
            )
            v.button_forgot_password -> Toast.makeText(this, "Tratar esse bunton", Toast.LENGTH_SHORT).show()
            v.button_other_login -> startActivity(Intent(this, LoginFormsActivity::class.java))
        }
    }

    private fun signIn(email: String, password: String) {

        if (validateForm(email, password)) {
            fbAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        //val user = fbAuth.currentUser
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun validateForm(email: String, password: String): Boolean{
        var valid = true
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.empty_form, Toast.LENGTH_LONG).show()
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, R.string.email_invalidate, Toast.LENGTH_LONG).show()
            valid = false
        }
        return valid
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}
