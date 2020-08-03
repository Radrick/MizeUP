package br.com.mizeup.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.mizeup.R
import br.com.mizeup.viewmodel.CreateAccountViewModel
import kotlinx.android.synthetic.main.activity_created_account.*


class CreateAccountActivity : AppCompatActivity() {

    private lateinit var mCreateUser: CreateAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_account)

        mCreateUser = ViewModelProvider(this).get(CreateAccountViewModel::class.java)

        btn_create_account.setOnClickListener {

            var name = tField_name_create_account.text.toString()
            var surname = tField_surname_create_account.text.toString()
            var email = tField_email_create_account.text.toString()
            var password = tField_password_create_account.text.toString()

            validateForm(name, surname, email, password)
        }
        observe()
    }

    private fun validateForm(name: String,surname: String,email: String,password: String){

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.empty_form, Toast.LENGTH_LONG).show()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, R.string.email_invalidate, Toast.LENGTH_LONG).show()
        } else if (password.length < 8) {
            Toast.makeText(this, R.string.password_length, Toast.LENGTH_LONG).show()
        } else {
            mCreateUser.save(name, surname, email, password)
        }
    }

    private fun observe(){
        mCreateUser.createUser.observe(this, Observer {
            if(it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, ObjectiveOnBoardingActivity::class.java))

            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_LONG).show()
            }
        })

    }
}
