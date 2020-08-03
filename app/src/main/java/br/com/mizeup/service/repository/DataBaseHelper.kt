package br.com.mizeup.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.mizeup.service.contants.DataBaseConstants

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MizeUP.db"

        private const val TABLE_USER = "CREATE TABLE ${DataBaseConstants.USER.TABLE_NAME} (" +
                "${DataBaseConstants.USER.COLUMNS.NAME} TEXT," +
                "${DataBaseConstants.USER.COLUMNS.SURNAME} TEXT," +
                "${DataBaseConstants.USER.COLUMNS.EMAIL} TEXT)"
    }
}