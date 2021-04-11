package space.stroesku.tochkatesttask.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding.widget.RxTextView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.stroesku.tochkatesttask.api.RetrofitInstance
import space.stroesku.tochkatesttask.data.UsersRepository

import space.stroesku.tochkatesttask.data.model.UserResponse
import space.stroesku.tochkatesttask.data.model.ResponseModel

class MainViewModel : ViewModel() {
    private val listUsers = MutableLiveData<List<User>>()
    val repo  = UsersRepository


    fun setSearchUsers(query: String) {
        Log.e("STROESKU","$query")
        repo.setDataFromApi(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ it ->
                listUsers.postValue(it)
            }
    }

    fun getSearchUsers(): LiveData<List<User>> {
        return listUsers
    }
}