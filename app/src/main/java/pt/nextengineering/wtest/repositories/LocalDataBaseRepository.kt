package pt.nextengineering.wtest.repositories

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.jetbrains.anko.doAsync
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.models.PostalCodesColumns

//class responsavel criar a bd
class LocalDataBaseRepository() {

    var sql : DatabaseCreation? = null
    constructor(context: Context) : this() {
        sql = DatabaseCreation(context)
    }

    //verificar na sharedpreference fez ou não o storage do ficheiro
    fun importFileDataToDataBase(appDirectory: String, context: Context, onfinnish : (Boolean) -> Unit) {
        //verificar se é true ou false a informação guardada com sharedpreference e envia-la
        val infoSharedPreference = getStoredOnDataBaseOnSharedpreference(context)

        if(infoSharedPreference) {
            onfinnish (true)
        }
        else {
            insertData(appDirectory){
                if(it){
                    //sharedpreference com a informação que já foi feita a inserção na bd
                    storedOnDataBaseOnSharedpreference(context, true)
                    onfinnish(true)
                }
                else{
                    onfinnish(false)
                }
            }
        }
    }

    private fun insertData(appDirectory : String, onFinishInsert : (Boolean) -> Unit) {
        //usado o doAsync de forma a serem usados dois threads, um para storage na bd e o outro para apresentar dados na activity
        doAsync {
            try {
                val db = sql!!.writableDatabase

                db.beginTransaction()
                val cv = ContentValues(100)
                csvReader().open(appDirectory.plus("/").plus(BuildConfig.FILE_NAME)) {
                    //    readAllWithHeader().forEach { row ->
                    val a = readAllWithHeader()
                    //for para correr até aos 100
                    loop@ for (i in 1..10) {
                        val row = a[i]
                        cv.put(PostalCodesColumns.COL_NUM_COD_POSTAL, row["num_cod_postal"])
                        cv.put(PostalCodesColumns.COL_EXT_COD_POSTAL1, row["ext_cod_postal"])
                        cv.put(PostalCodesColumns.COL_DESIG_POSTAL, row["desig_postal"])

                        //inserção dos dados do ficheiro na bd
                        db!!.insert(PostalCodesColumns.TABLE_NAME, null, cv)
                    }
                }
                db.setTransactionSuccessful()
                db.endTransaction()
                onFinishInsert(true)
            }
            //ficheiro não ficou guardado
            catch (ex: Exception) {
                Log.d(ContentValues.TAG, "${ex.message}")
                onFinishInsert(false)
            }
        }
    }

    fun storedOnDataBaseOnSharedpreference(context: Context, state: Boolean) {
        val sharedPref =
            context.getSharedPreferences(BuildConfig.PREFS_GLOBAL, Context.MODE_PRIVATE)

        val editor = sharedPref.edit()
        editor.putBoolean(BuildConfig.PREFS_NAME_STORAGE_SQLITE, state)
        editor.apply()
        editor.commit()
    }

    fun getStoredOnDataBaseOnSharedpreference(context: Context): Boolean {
        val sharedPref =
            context.getSharedPreferences(BuildConfig.PREFS_GLOBAL, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(BuildConfig.PREFS_NAME_STORAGE_SQLITE, false)
    }
}