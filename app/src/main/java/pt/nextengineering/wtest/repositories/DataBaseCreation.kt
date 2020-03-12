package pt.nextengineering.wtest.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.models.PostalCodesColumns
import pt.nextengineering.wtest.models.PostalCodesColumns.TABLE_NAME

//class responsavel criar a bd
class DatabaseCreation(context: Context?) : SQLiteOpenHelper(context, BuildConfig.DATABASE_NAME, null, BuildConfig.DATABASE_VERSION.toInt()) {


    val postalCode_table = "CREATE TABLE ${PostalCodesColumns.TABLE_NAME}" +
            "(${PostalCodesColumns.COL_NUM_COD_POSTAL} TEXT, " +
            "${PostalCodesColumns.COL_EXT_COD_POSTAL1} TEXT, " +
            "${PostalCodesColumns.COL_DESIG_POSTAL} TEXT);"
    val dropTable = "DROP TABLE IF EXISTS ${PostalCodesColumns.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(postalCode_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(dropTable)
        onCreate(db)
    }
}