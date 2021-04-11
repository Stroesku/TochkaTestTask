package space.stroesku.tochkatesttask.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("items")
    val items:ArrayList<UserResponse>
)