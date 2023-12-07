package com.itzik.user_with_testing.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itzik.user_with_testing.project.models.Gender
import com.itzik.user_with_testing.project.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@HiltViewModel
class UserViewModel : ViewModel() {

    private var firstName = listOf<String>()
    private var familyName = ""
    private var age = 0
    private var gender = ""


    private var dateSelected = ""
    private val pattern = "dd/MM/yyyy"
    private val timeFormat = SimpleDateFormat(pattern, Locale.US)
    private var today = timeFormat.format(Calendar.getInstance().time)



    fun splitUserNameIntoFirstAndFamilyName(fullName: String): Pair<List<String>, String> {
        val nameParts = fullName.split(" ")

        firstName = if (nameParts.size > 1) {
            nameParts.subList(0, nameParts.size - 1)
        } else {
            listOf(fullName)
        }

        familyName = if (nameParts.size > 1) {
            nameParts.last()
        } else {
            ""
        }
        return Pair(firstName, familyName)
    }


    fun validateDate(chosenDate: Calendar) {

        dateSelected = formattedDate(chosenDate)

        val result = compareDates(today, dateSelected)
        extractAgeFromDate(dateSelected)
        if (result < 0) {
            logD("Can't choose future birth date ")
        } else if (result > 0) {
            logD("$dateSelected comes earlier")
        } else logD("Both dates are equal")
    }

    private fun formattedDate(calendar: Calendar): String {
        val dateFormat = timeFormat

        return dateFormat.format(calendar.time)
    }

    fun extractAgeFromDate(dateSelected: String): Int {
        val formatter = DateTimeFormatter.ofPattern(pattern)

        val thisYear = LocalDate.parse(today, formatter).year
        val selectedYear = LocalDate.parse(dateSelected, formatter).year
        val thisDayOfMonth = LocalDate.parse(today, formatter).dayOfMonth
        val selectedDayOfMonth = LocalDate.parse(dateSelected, formatter).dayOfMonth

        age = thisYear - selectedYear
        if (thisDayOfMonth < selectedDayOfMonth) {
            age--
        }
        logD("$age")
        return age
    }

    private fun compareDates(todayString: String, selectedDateString: String): Int {
        val dateFormat = timeFormat
        val today = dateFormat.parse(todayString)
        val selectedDate = dateFormat.parse(selectedDateString)
        return today.compareTo(selectedDate)
    }


    fun getGenderString(selectedGender: Gender): String {

        gender = when (selectedGender) {
            is Gender.MALE -> "Male"
            is Gender.FEMALE -> "Female"
            is Gender.OTHER -> "Other"
        }
        return gender
    }


    fun validateEmail(
        email:String
    ){

    }





















    fun createUser(

        id: Long,
        firstName: List<String>,
        familyName: String,
        age: Int,
        gender: String,
        email: String,
        password: String,
        phoneNumber: String,
        birthDate: String,

        ): User {
        return User(
            0,
            firstName,
            familyName,
            age,
            gender,
            "",
            "",
            "",
            ""
        )
    }


    private fun logD(message: String) {
        Log.d("TAG", message)
    }
}

