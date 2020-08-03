package br.com.mizeup.viewmodel


import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mizeup.model.UserModel
import br.com.mizeup.service.UserRepository
import com.google.firebase.auth.FirebaseAuth


class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {

    private val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private var context: Context = application.applicationContext
    private var mUserRepository: UserRepository = UserRepository(context)

    private var mCreateUser = MutableLiveData<Boolean>()
    val createUser: LiveData<Boolean> = mCreateUser

    fun save(name: String, surname: String, email: String, password: String) {
        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = UserModel(name, surname, email)
                mCreateUser.value = mUserRepository.save(user)
            } else {
                mCreateUser.value = false
            }
        }
    }
}



