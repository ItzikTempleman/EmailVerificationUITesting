package com.itzik.user_with_testing.project.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@HiltViewModel
class UserViewModel : ViewModel() {

    private var dateSelected = ""
    private val pattern = "dd/MM/yyyy"
    private var today: String = SimpleDateFormat(pattern, Locale.US).format(Calendar.getInstance().time)

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


    fun validateDate(chosenDate: Calendar) {
        dateSelected = formattedDate(chosenDate, pattern)

        val result = compareDates(today, dateSelected, pattern)
        if(result>0) {
            return
        }
//        when {
//            result < 0 -> Log.d("TAG", "$today comes earlier")
//            result > 0 -> Log.d("TAG", "$dateSelected comes earlier")
//            else -> Log.d("TAG", "Both dates are equal")
//        }
    }


    private fun formattedDate(calendar: Calendar, pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.US)
        return dateFormat.format(calendar.time)
    }


    private fun compareDates(dateString1: String, dateString2: String, pattern: String): Int {
        val dateFormat = SimpleDateFormat(pattern, Locale.US)
        val date1 = dateFormat.parse(dateString1)
        val date2 = dateFormat.parse(dateString2)
        return date1.compareTo(date2)
    }
}
