package space.stroesku.tochkatesttask.data.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("avatar_url")
    val avatarUrl: String = ""
)