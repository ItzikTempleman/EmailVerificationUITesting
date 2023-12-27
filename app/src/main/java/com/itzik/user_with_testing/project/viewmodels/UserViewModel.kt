package com.itzik.user_with_testing.project.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.models.Gender
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.navigation.HomeGraph
import com.itzik.user_with_testing.project.repositories.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class UserViewModel
@Inject constructor(
    private val repository: IUserRepository
) : ViewModel() {

    var user by mutableStateOf<User?>(null)
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


    suspend fun saveUser(user: User) = repository.saveUser(user)

    suspend fun getAllUsers(): Flow<List<User>> {
        val userList = flow {
            val updatedUserList = repository.getUsers()
            if (updatedUserList.isNotEmpty()) {
                emit(updatedUserList)
            } else return@flow
        }
        return userList
    }

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

    private fun isValidName(): Boolean = fullName.isNotEmpty()
    fun isValidEmail(): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


    fun isValidPassword(): Boolean =
        password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".toRegex())


    fun isValidLoginEmail(email: String): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


    fun isValidLoginPassword(password: String): Boolean =
        password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".toRegex())

    private fun isPhoneNumberOk(): Boolean = phoneNumber.isNotBlank()
    fun isAllFieldsOk(): Boolean =
        isValidName() && isValidEmail() && isValidPassword() && isPhoneNumberOk() && age > 9

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
        return user as User
    }

    fun updateUser(newUser: User) {
        user = newUser
    }


    fun setErrors() {

    }

    fun moveToHomeScreen(isSuccessfulData: Boolean, navHostController: NavHostController) {
        if (isSuccessfulData) {
            navHostController.navigate(HomeGraph.HomePage.route)
        }
    }
}