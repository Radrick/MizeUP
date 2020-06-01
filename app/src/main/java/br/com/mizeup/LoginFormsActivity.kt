package br.com.mizeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login_forms.*
import kotlinx.android.synthetic.main.activity_login_forms.view.*

class LoginFormsActivity : AppCompatActivity(), View.OnClickListener {

    private var googleApiClient: GoogleApiClient? = null
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_forms)

        initGoogleSignIn()

        button_email_login.setOnClickListener(this)
        button_google_login.setOnClickListener(this)
        button_apple_login.setOnClickListener(this)
        button_create_account.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this) { showErrorSignIn() }
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()
    }

    private fun signIn(){
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN )
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                } else {
                    showErrorSignIn()
                }
            } else {
                showErrorSignIn()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this) {task ->
            if (task.isSuccessful) {
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                showErrorSignIn()
            }
        }
    }

    private fun showErrorSignIn(){
        Toast.makeText(this, R.string.error_google_sign_in, Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View) {
        when (v) {
            v.button_email_login -> startActivity(Intent(this, LoginEmailActivity::class.java))
            v.button_google_login -> signIn()
            v.button_apple_login -> startActivity(Intent(this, MainActivity::class.java))
            v.button_create_account -> startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    companion object {
        const val RC_GOOGLE_SIGN_IN = 1
    }
}
