package pt.nextengineering.wtest.models

import android.accessibilityservice.GestureDescription
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import pt.nextengineering.wtest.BuildConfig

val DATABASE_NAME = BuildConfig.DATABASE_NAME
val TABLE_NAME = "PostalCodes"
var COL_COD_DISTRITO = "cod_distrito"
var COL_COD_CONCELHO = "cod_concelho"
var COL_COD_LOCALIDADE = "cod_localidade"
var COL_NOME_LOCALIDADE = "nome_localidade"
var COL_COD_ARTERIA = "cod_arteria"
var COL_TIPO_ARTERIA = "tipo_arteria"
var COL_PREP1 = "prep1"
var COL_TITULO_ARTERIA = "titulo_arteria"
var COL_PREP2 = "prep2"
var COL_NOME_ARTERIA = "nome_arteria"
var COL_LOCAL_ARTERIA = "local_arteria"
var COL_TROCO = "troco"
var COL_PORTA = "porta"
var COL_CLIENTE = "cliente"
var COL_NUM_COD_POSTAL = "num_cod_postal"
var COL_EXT_COD_POSTAL1 = "ext_cod_postal"
var COL_DESIG_POSTAL = "desig_postal"


//class responsavel pôr "ligar a bd"
class SQLiteConection (var context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_COD_DISTRITO + "INTEGER, " +
            COL_COD_CONCELHO + "INTEGER, " +
            COL_COD_LOCALIDADE + "INTEGER, " +
            COL_NOME_LOCALIDADE + "VARCHAR(256)," +
            COL_COD_ARTERIA + "INTEGER, " +
            COL_TIPO_ARTERIA + "INTEGER, " +
            COL_PREP1 + "INTEGER, " +
            COL_TITULO_ARTERIA + "VARCHAR(256)," +
            COL_PREP2 + "INTEGER, " +
            COL_NOME_ARTERIA + "VARCHAR(256)," +
            COL_LOCAL_ARTERIA + "INTEGER, " +
            COL_TROCO + "INTEGER, " +
            COL_PORTA + "INTEGER, " +
            COL_CLIENTE + "VARCHAR(256)," +
            COL_NUM_COD_POSTAL + "INTEGER, " +
            COL_EXT_COD_POSTAL1 + "INTEGER, " +
            COL_DESIG_POSTAL + "VARCHAR(256);"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}