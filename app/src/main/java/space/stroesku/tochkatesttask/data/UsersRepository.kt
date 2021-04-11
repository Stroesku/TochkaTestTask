package space.stroesku.tochkatesttask.data

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import space.stroesku.tochkatesttask.api.RetrofitInstance
import space.stroesku.tochkatesttask.data.model.UserResponse
import space.stroesku.tochkatesttask.ui.main.User

object UsersRepository {

    fun setDataFromApi(query: String): Single<ArrayList<User>> {

        return RetrofitInstance.apiInstance.getSearchUsers(query)
            .subscribeOn(Schedulers.newThread())
            .map { it ->
                it.items.map {
                    it.toUser()
                } as ArrayList
            }
    }

    private fun UserResponse.toUser(): User {
        return User(login, id, avatarUrl)
    }
}

