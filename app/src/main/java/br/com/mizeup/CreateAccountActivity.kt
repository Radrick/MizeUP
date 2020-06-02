package br.com.mizeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_created_account.*

class CreateAccountActivity : AppCompatActivity() {

    private var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_account)

        btn_create_account.setOnClickListener {

            var name = tField_name_create_account.text.toString()
            var surname = tField_surname_create_account.text.toString()
            var email = tField_email_create_account.text.toString()
            var password = tField_password_create_account.text.toString()

            createAccount(name, surname, email, password)
        }
    }

   /* override fun onClick(v: View?) {
    }*/

    private fun createAccount(name: String, surname: String, email: String, password: String) {

        if (validateForm(name, surname, email, password)) {
            fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(this, "Usuário criado com sucesso", Toast.LENGTH_LONG)
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Log.d(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun validateForm(name: String, surname: String, email: String, password: String): Boolean {
        var valid = true

        if (name.isEmpty()  || surname.isEmpty()  || email.isEmpty() || password.isBlank()){
            Toast.makeText(this, R.string.empty_form, Toast.LENGTH_LONG)
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, R.string.email_invalidate, Toast.LENGTH_LONG).show()
            valid = false
        }

        return valid
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}