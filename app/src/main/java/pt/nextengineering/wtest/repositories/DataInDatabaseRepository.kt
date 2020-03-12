package pt.nextengineering.wtest.repositories

import android.content.Context
import android.database.Cursor
import org.jetbrains.anko.doAsync
import pt.nextengineering.wtest.models.PostalCodes
import pt.nextengineering.wtest.models.PostalCodesColumns
import java.lang.Exception

class DataInDatabaseRepository {
    var postalCodesList = ArrayList<PostalCodes>()

    companion object {
        val sharedInstance = DataInDatabaseRepository()
    }

    fun getDataInDataBase(context: Context, success: (List<PostalCodes>) -> Unit, isRead : (Boolean) -> Unit) {
        //de forma a que a progressBar continue visivel usamos outra thread
        doAsync {

            //se a list dos códigos postais estiver vazia
            if(postalCodesList.size == 0) {
                //lista que auxilia a utilização dos dados
                val list = ArrayList<PostalCodes>()
                var sql: DatabaseCreation? = null
                sql = DatabaseCreation(context)

                val db = sql!!.readableDatabase
                val queries = "select * from " + PostalCodesColumns.TABLE_NAME;

                val cursor: Cursor = db.rawQuery(queries, null)
                if (cursor.moveToFirst()) {
                    do {
                        val num_cod_postal: String = cursor.getString(0)
                        val ext_cod_postal: String = cursor.getString(1)
                        val desig_postal: String = cursor.getString(2)

                        //adicinar à lista
                        list.add(
                            PostalCodes(num_cod_postal, ext_cod_postal, desig_postal)
                        )

                    } while (cursor.moveToNext())
                }
                postalCodesList=list

                //list de dados
                success(postalCodesList)
                //notifica que foi feita a leitura dos dados
                isRead(true)
            }
        }
    }
}