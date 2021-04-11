package space.stroesku.tochkatesttask.ui.login

import com.google.gson.annotations.SerializedName
import space.stroesku.tochkatesttask.R

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val userId: Int = 0,
        val displayName: String = "",
        val photo: Int = R.drawable.ic_launcher_foreground
)