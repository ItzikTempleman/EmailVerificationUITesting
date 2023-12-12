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

    private lateinit var user: User
    private var fullName = ""
    private var firstAndMiddleNameList = listOf<String>()
    private var familyName = ""
    private var email = ""
    private var password = ""
    private var phoneNumber = ""
    private var dateSelected = ""
    private var age = 0
    private lateinit var gender: String
    private val pattern = "dd/MM/yyyy"
    private val timeFormat = SimpleDateFormat(pattern, Locale.US)
    private var today = timeFormat.format(Calendar.getInstance().time)


    fun splitUserNameIntoFirstAndFamilyName(fullNameAsParam: String): Pair<List<String>, String> {
        val nameParts = fullNameAsParam.split(" ")
        fullName = fullNameAsParam
        firstAndMiddleNameList = if (nameParts.size > 1) {
            nameParts.subList(0, nameParts.size - 1)
        } else {
            listOf(fullNameAsParam)
        }

        familyName = if (nameParts.size > 1) {
            nameParts.last()
        } else {
            ""
        }
        return Pair(firstAndMiddleNameList, familyName)
    }

    fun isValidName(): Boolean = fullName.isNotEmpty()


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

    fun validateAge(chosenDate: Calendar): Boolean {
        dateSelected = formattedDate(chosenDate)
        age = extractAgeFromDate(dateSelected)
        return age > 9
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

        var tempAge = thisYear - selectedYear
        if (thisDayOfMonth < selectedDayOfMonth) {
            tempAge--
        }
        return tempAge
    }


    fun getGenderString(selectedGender: Gender): String {
        gender = when (selectedGender) {
            is Gender.MALE -> "Male"
            is Gender.FEMALE -> "Female"
            is Gender.OTHER -> "Other"
        }
        return gender
    }

    private fun isValidEmail(): Boolean = email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())

     fun isValidPassword(): Boolean =
        /**
        ^ # start-of-string
        (?=.*[0-9]) # a digit must occur at least once
        (?=.*[a-z]) # a lower case letter must occur at least once
        (?=.*[A-Z]) # an upper case letter must occur at least once
        (?=.*[@#$%^&+=]) # a special character must occur at least once replace with your special characters
        (?=\\S+$) # no whitespace allowed in the entire string
        33 .{8,} # anything, at least six places though
        $ # end-of-string
         **/

//        val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$"
//        val pattern = Pattern.compile(passwordRegex)
//        return pattern.matcher(password).matches()

    password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".toRegex())
    fun setErrors() {

    }

    private fun isPhoneNumberOk(): Boolean = phoneNumber.isNotBlank()


    fun isAllFieldsOk(): Boolean =
        isValidName() && isValidEmail() && isValidPassword()
                && isPhoneNumberOk() && age > 9

    fun createUser(): User {
        user =
            User(
                0,
                firstAndMiddleNameList,
                familyName,
                age,
                gender,
                email,
                password,
                phoneNumber,
                dateSelected
            )
        Log.d("TAG", "$user")
        return user
    }
}


