package com.itzik.user_with_testing.project.models

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.itzik.user_with_testing.project.utils.Constants.USER_TABLE
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val firstName: List<String>,
    val familyName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val password: String,
    val phoneNumber:String,
    val birthDate:String
): Parcelable

sealed class Gender(val name:String, val icon: ImageVector) {
    data object MALE : Gender(name="Male", icon = Icons.Default.Male)
    data object FEMALE : Gender(name="Female", icon = Icons.Default.Female)
    data object OTHER : Gender(name="Other", icon = Icons.Default.Transgender)
}

