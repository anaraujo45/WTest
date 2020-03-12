package pt.nextengineering.wtest.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.nextengineering.wtest.models.PostalCodes
import pt.nextengineering.wtest.repositories.DataInDatabaseRepository

class PostalCodesViewModel : ViewModel() {
    private var repositoryDataInDatabaseRepository : DataInDatabaseRepository = DataInDatabaseRepository.sharedInstance
    private var mPostalCodes: MutableLiveData<List<PostalCodes>> = MutableLiveData()
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()

    fun init(context: Context){
        //se a lista de códigos for nula (não existe) devemos ir buscar dados
        if (mPostalCodes.value == null){
            //continua o processo de updating
            mIsUpdating.value=true

            //iniciar repositório que vai buscar os dados
            repositoryDataInDatabaseRepository.getDataInDataBase(context, {
                mPostalCodes.postValue(it)
                mIsUpdating.postValue(false)
            },{
                //
            })
        }
    }

    fun getPostalCodesLiveData(): LiveData<List<PostalCodes>>? {
        return mPostalCodes
    }

    fun getIsUpdatingLiveData(): LiveData<Boolean>? {
        return mIsUpdating
    }
}