package com.itzik.user_with_testing.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class UserViewModel(

) : ViewModel() {

    fun splitUserNameIntoFirstAndFamilyName(fullName: String): Pair<List<String>, String> {
        val nameParts = fullName.split(" ")

        val firstName = if (nameParts.size > 1) {
            nameParts.subList(0, nameParts.size - 1)
        } else {
            listOf(fullName)
        }

        val familyName = if (nameParts.size > 1) {
            nameParts.last()
        } else {
            ""
        }
        val splitNames=Pair(firstName, familyName)
        Log.d("TAG", "First name: $firstName and Family name: $familyName")
        return splitNames
    }

//    fun generateUser(
//        fullName: String,
//        age: Int,
//        gender: Gender,
//        emailOrUserName: String,
//        password: String,
//        phoneNumber: String,
//    ): User {
// }
//
//
}
