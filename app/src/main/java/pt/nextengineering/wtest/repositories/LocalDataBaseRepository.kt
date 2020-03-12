package pt.nextengineering.wtest.repositories

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.jetbrains.anko.doAsync
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.models.PostalCodesColumns

//class responsavel criar a bd
class LocalDataBaseRepository(context: Context?) : SQLiteOpenHelper(context,
    BuildConfig.DATABASE_NAME, null, BuildConfig.DATABASE_VERSION.toInt()) {

    private val postalCode_table = "CREATE TABLE ${PostalCodesColumns.TABLE_NAME}" +
            "${PostalCodesColumns.COL_NUM_COD_POSTAL} TEXT, " +
            "${PostalCodesColumns.COL_EXT_COD_POSTAL1} TEXT, " +
            "${PostalCodesColumns.COL_DESIG_POSTAL} TEXT);"
    private lateinit var sql: LocalDataBaseRepository
    private val dropTable = "DROP TABLE IF EXISTS ${PostalCodesColumns.TABLE_NAME}"

    //verificar na sharedpreference fez ou não o storage do ficheiro
    fun importFileDataToDataBase(appDirectory: String, context: Context, onfinnish : (Boolean) -> Unit) {
        //verificar se é true ou false a informação guardada com sharedpreference e envia-la
        val infoSharedPreference = getStoredOnDataBaseOnSharedpreference(context)

        if(infoSharedPreference) {
            onfinnish (infoSharedPreference)
        }
        else {
            insertData(appDirectory, context){
                onfinnish(it)
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(postalCode_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(dropTable)
        onCreate(db)
    }

    fun insertData(appDirectory : String, context :Context, onFinishInsert : (Boolean) -> Unit) {
        try {
            //usado o doAsync de forma a serem usados dois threads, um para storage na bd e o outro para apresentar dados na activity
            doAsync {
                sql = LocalDataBaseRepository(context)
                val db = sql.writableDatabase

                db.beginTransaction()
                val cv = ContentValues()

                //for para correr até aos 100

                csvReader().open(appDirectory.plus("/").plus(BuildConfig.FILE_NAME)) {
                    //    readAllWithHeader().forEach { row ->
                    val a = readAllWithHeader()
                    loop@ for (i in 1..100) {
                        val row = a[i]
                        cv.put(PostalCodesColumns.COL_NUM_COD_POSTAL, row["num_cod_postal"])
                        cv.put(PostalCodesColumns.COL_EXT_COD_POSTAL1, row["ext_cod_postal"])
                        cv.put(PostalCodesColumns.COL_DESIG_POSTAL, row["desig_postal"])
                    }
                }
                db.setTransactionSuccessful()
                db.endTransaction()

                //inserção dos dados do ficheiro na bd
                db!!.insert(PostalCodesColumns.TABLE_NAME, null, cv)

                //sharedpreference com a informação que já foi feita a inserção na bd
                storedOnDataBaseOnSharedpreference(context, true)
                onFinishInsert(true)
            }
        }
        //ficheiro não ficou guardado
        catch (ex: Exception) {
            Log.d(ContentValues.TAG, "file not insert in database")
            onFinishInsert(false)
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