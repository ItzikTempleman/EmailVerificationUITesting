package com.itzik.user_with_testing.project.models

import android.os.Parcelable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val firstName: List<String>,
    val familyName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val password: String,
    val phoneNumber:String,
    val birthDate:String
): Parcelable, Serializable

sealed class Gender(val name:String, val icon: ImageVector) {
    object MALE : Gender(name="Male", icon = Icons.Default.Male)
    object FEMALE : Gender(name="Female", icon = Icons.Default.Female)
    object OTHER : Gender(name="Other", icon = Icons.Default.Transgender)
}

