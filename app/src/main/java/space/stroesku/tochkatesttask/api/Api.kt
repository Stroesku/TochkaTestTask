package space.stroesku.tochkatesttask.api

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import space.stroesku.tochkatesttask.data.model.ResponseModel
import space.stroesku.tochkatesttask.data.model.UserResponse

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 10d94619a76bd822af5ba3fc8bc940c3b84d74f4")
    fun getSearchUsers(@Query("q") query: String,
//                       @Query("&page") page:Int
    ): Single<ResponseModel>
}