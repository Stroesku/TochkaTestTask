package space.stroesku.tochkatesttask.data.model

import com.google.gson.annotations.SerializedName
import space.stroesku.tochkatesttask.R

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
        @SerializedName("id")
        val userId: Int = 0,
        @SerializedName("name")
        val displayName: String = "",
        @SerializedName("uri_photo")
        val photo: Int = R.drawable.ic_launcher_foreground
)