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
import java.util.regex.Pattern

@HiltViewModel
class UserViewModel : ViewModel() {

    var firstName = listOf<String>()
    var familyName = ""
    var age = 0
    var gender = ""
    var email = ""
    var password = ""
    var phoneNumber = ""
    var dateSelected = ""
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

    private fun extractAgeFromDate(dateSelected: String): Int {
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

    fun validateEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})([.]{1})(.{1,})$"
        val pattern = Pattern.compile(emailRegex)
        return pattern.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        // Password criteria: At least 8 characters, one uppercase letter, one lowercase letter, and one digit
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"
        val pattern = Pattern.compile(passwordRegex)
        return pattern.matcher(password).matches()
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
        birthDate: String

        ): User {
        val user= User(
            0,
            firstName,
            familyName,
            age,
            gender,
            email,
            password,
            phoneNumber,
            dateSelected
        )
        logD(user.toString())
        return user
    }

    private fun logD(message: String) {
        Log.d("TAG", message)
    }


    fun updateEmail(createEmail: String): String {
        email = createEmail
        return email
    }

    fun updatePassword(createPassword: String): String {
        password = createPassword
        return password
    }

    fun updatePhoneNumber(newPhoneNumber: String): String {
        phoneNumber = newPhoneNumber
        return phoneNumber
    }
}

