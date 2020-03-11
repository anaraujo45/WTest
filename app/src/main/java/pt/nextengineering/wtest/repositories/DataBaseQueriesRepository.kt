package pt.nextengineering.wtest.repositories

import android.content.ContentValues
import android.content.Context
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.models.PostalCodesColumns


class DataBaseQueriesRepository {
    var sql : LocalDataBaseRepository? = null
    constructor(context: Context){
        sql = LocalDataBaseRepository(context)
    }

    //open csv and storage in sqlite
    fun insertData(appDirectory : String) : Long{
        val db = sql!!.writableDatabase

        db.beginTransaction()
        val cv = ContentValues()

        //evita que se guarde a 1ª posição
        val flag = true


        csvReader().open(appDirectory.plus("/").plus(BuildConfig.FILE_NAME)) {
            readAllWithHeader().forEach { row ->
                cv.put(PostalCodesColumns.COL_COD_DISTRITO, row["cod_distrito"])
                cv.put(PostalCodesColumns.COL_COD_CONCELHO, row["cod_concelho"])
                cv.put(PostalCodesColumns.COL_COD_LOCALIDADE, row["cod_localidade"])
                cv.put(PostalCodesColumns.COL_NOME_LOCALIDADE, row["nome_localidade"])
                cv.put(PostalCodesColumns.COL_COD_ARTERIA, row["cod_arteria"])
                cv.put(PostalCodesColumns.COL_TIPO_ARTERIA, row["tipo_arteria"])
                cv.put(PostalCodesColumns.COL_PREP1, row["prep1"])
                cv.put(PostalCodesColumns.COL_TITULO_ARTERIA, row["titulo_arteria"])
                cv.put(PostalCodesColumns.COL_PREP2, row["prep2"])
                cv.put(PostalCodesColumns.COL_NOME_ARTERIA, row["nome_arteria"])
                cv.put(PostalCodesColumns.COL_LOCAL_ARTERIA, row["local_arteria"])
                cv.put(PostalCodesColumns.COL_TROCO, row["troco"])
                cv.put(PostalCodesColumns.COL_PORTA, row["porta"])
                cv.put(PostalCodesColumns.COL_CLIENTE, row["cliente"])
                cv.put(PostalCodesColumns.COL_NUM_COD_POSTAL, row["num_cod_postal"])
                cv.put(PostalCodesColumns.COL_EXT_COD_POSTAL1, row["ext_cod_postal"])
                cv.put(PostalCodesColumns.COL_DESIG_POSTAL, row["desig_postal"])
            }
        }


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


    //vir buscar dados na bd
    //viewmodel novo para isto

    //casar 1º a nova view com o novo viewmodel

    //tempo que demora a pôr o ficheiro no sql
}
