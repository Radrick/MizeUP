package br.com.mizeup.service

import android.content.ContentValues
import android.content.Context
import br.com.mizeup.model.UserModel
import br.com.mizeup.service.contants.DataBaseConstants
import br.com.mizeup.service.repository.DataBaseHelper

class UserRepository(context: Context) {

    private var mDataBaseHelper: DataBaseHelper = DataBaseHelper(context)

    fun save(user: UserModel): Boolean{

        return try {
            val db = mDataBaseHelper.writableDatabase
            val contentValue = ContentValues().apply {
                put(DataBaseConstants.USER.COLUMNS.ID, user.id)
                put(DataBaseConstants.USER.COLUMNS.NAME, user.name)
                put(DataBaseConstants.USER.COLUMNS.SURNAME, user.surname)
                put(DataBaseConstants.USER.COLUMNS.EMAIL, user.email)
            }

            db.insert(DataBaseConstants.USER.TABLE_NAME,null, contentValue)
            db.close()
            true
        } catch (e: Exception){
            false
        }
    }

    fun remove(){}
    fun update(){}
    fun get(){}
}