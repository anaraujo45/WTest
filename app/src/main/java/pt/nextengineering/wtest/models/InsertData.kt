package pt.nextengineering.wtest.models

import android.content.ContentValues
import android.widget.Toast

/*
class InsertData {
    fun insertData(postalCodes : PostalCodes){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_COD_DISTRITO, postalCodes.cod_distrito)
        cv.put(COL_COD_CONCELHO, postalCodes.cod_concelho)
        cv.put(COL_COD_LOCALIDADE, postalCodes.cod_localidade)
        cv.put(COL_NOME_LOCALIDADE, postalCodes.nome_localidade)
        cv.put(COL_COD_ARTERIA, postalCodes.cod_arteria)
        cv.put(COL_TIPO_ARTERIA, postalCodes.tipo_arteria)
        cv.put(COL_PREP1, postalCodes.prep1)
        cv.put(COL_TITULO_ARTERIA, postalCodes.titulo_arteria)
        cv.put(COL_PREP2, postalCodes.prep2)
        cv.put(COL_NOME_ARTERIA, postalCodes.nome_arteria)
        cv.put(COL_LOCAL_ARTERIA, postalCodes.local_arteria)
        cv.put(COL_TROCO, postalCodes.troco)
        cv.put(COL_PORTA, postalCodes.porta)
        cv.put(COL_CLIENTE, postalCodes.cliente)
        cv.put(COL_NUM_COD_POSTAL, postalCodes.num_cod_postal)
        cv.put(COL_EXT_COD_POSTAL1, postalCodes.ext_cod_postal)
        cv.put(COL_DESIG_POSTAL, postalCodes.desig_postal)

        val result = db.insert(TABLE_NAME, null, cv)
        if(result == (-1).toLong()){
            Toast.makeText(context, "Failed SQLite insertion.", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success SQLite insertion.", Toast.LENGTH_SHORT).show()
        }
    }
}
 */