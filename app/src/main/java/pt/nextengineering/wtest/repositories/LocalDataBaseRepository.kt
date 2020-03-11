package pt.nextengineering.wtest.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.models.PostalCodesColumns

//class responsavel criar a bd
class LocalDataBaseRepository(context: Context?) : SQLiteOpenHelper(context, BuildConfig.DATABASE_NAME, null, BuildConfig.DATABASE_VERSION.toInt()) {
    val postalCode_table = "CREATE TABLE ${PostalCodesColumns.TABLE_NAME}" +
            "(${PostalCodesColumns.COL_COD_DISTRITO} TEXT, " +
            "${PostalCodesColumns.COL_COD_CONCELHO} INTEGER, " +
            "${PostalCodesColumns.COL_COD_LOCALIDADE} INTEGER, " +
            "${PostalCodesColumns.COL_NOME_LOCALIDADE} TEXT," +
            "${PostalCodesColumns.COL_COD_ARTERIA} INTEGER, " +
            "${PostalCodesColumns.COL_TIPO_ARTERIA} INTEGER, " +
            "${PostalCodesColumns.COL_PREP1} INTEGER, " +
            "${PostalCodesColumns.COL_TITULO_ARTERIA} TEXT," +
            "${PostalCodesColumns.COL_PREP2} INTEGER, " +
            "${PostalCodesColumns.COL_NOME_ARTERIA} TEXT," +
            "${PostalCodesColumns.COL_LOCAL_ARTERIA} INTEGER, " +
            "${PostalCodesColumns.COL_TROCO} INTEGER, " +
            "${PostalCodesColumns.COL_PORTA} INTEGER, " +
            "${PostalCodesColumns.COL_CLIENTE} TEXT," +
            "${PostalCodesColumns.COL_NUM_COD_POSTAL} INTEGER, " +
            "${PostalCodesColumns.COL_EXT_COD_POSTAL1} INTEGER, " +
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