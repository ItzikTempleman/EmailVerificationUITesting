package com.itzik.user_with_testing.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        return Pair(firstName, familyName)
    }


    fun getBirthDate(day: String, month: String, year: String): Date? {

        val dataString = if (day.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty()) { "$day-$month-$year"
        } else "Invalid date"

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dataString)

        Log.d("TAG", "The date is: $dateFormat")
        return dateFormat
    }


    fun getAge(day: String, month: String, year: String): Int {
        return 0
    }


}
