package br.com.mizeup.service.contants

import android.provider.BaseColumns

class DataBaseConstants private constructor(){

    object USER{
        const val TABLE_NAME = "User"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val SURNAME = "surname"
            const val EMAIL = "email"
        }


    }

}