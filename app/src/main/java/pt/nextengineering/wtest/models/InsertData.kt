package pt.nextengineering.wtest.models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import pt.nextengineering.wtest.BuildConfig


class InsertData {
    var sql : DatabaseCreation? = null
    constructor(context: Context){
        sql = DatabaseCreation(context)
    }

    //open csv and storage in sqlite
    fun insertData(appDirectory : String) : Long{
        val db = sql!!.writableDatabase

        db.beginTransaction()
        val cv = ContentValues()

        //evita que se guarde a 1ª posição
        val flag = true
        csvReader().open(appDirectory.plus("/").plus(BuildConfig.FILE_NAME)) {
            readAllAsSequence().forEach { row ->
                if (flag == false) {
                    cv.put(PostalCodesColumns.COL_COD_DISTRITO, row[0])
                    cv.put(PostalCodesColumns.COL_COD_CONCELHO, row[1])
                    cv.put(PostalCodesColumns.COL_COD_LOCALIDADE, row[2])
                    cv.put(PostalCodesColumns.COL_NOME_LOCALIDADE, row[3])
                    cv.put(PostalCodesColumns.COL_COD_ARTERIA, row[4])
                    cv.put(PostalCodesColumns.COL_TIPO_ARTERIA, row[5])
                    cv.put(PostalCodesColumns.COL_PREP1, row[6])
                    cv.put(PostalCodesColumns.COL_TITULO_ARTERIA, row[7])
                    cv.put(PostalCodesColumns.COL_PREP2, row[8])
                    cv.put(PostalCodesColumns.COL_NOME_ARTERIA, row[9])
                    cv.put(PostalCodesColumns.COL_LOCAL_ARTERIA, row[10])
                    cv.put(PostalCodesColumns.COL_TROCO, row[11])
                    cv.put(PostalCodesColumns.COL_PORTA, row[12])
                    cv.put(PostalCodesColumns.COL_CLIENTE, row[13])
                    cv.put(PostalCodesColumns.COL_NUM_COD_POSTAL, row[14])
                    cv.put(PostalCodesColumns.COL_EXT_COD_POSTAL1, row[15])
                    cv.put(PostalCodesColumns.COL_DESIG_POSTAL, row[16])
                }
                else{
                    //vamos para a 2ª linha
                    flag == false
                }
            }
        }

        db.setTransactionSuccessful()
        db.endTransaction()

        return db!!.insert(PostalCodesColumns.TABLE_NAME, null, cv)
    }
}
