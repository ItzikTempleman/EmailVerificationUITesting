package com.itzik.user_with_testing.project.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.itzik.user_with_testing.project.models.AirportCodeName
import com.itzik.user_with_testing.project.models.Gender
import com.itzik.user_with_testing.project.models.User
import com.itzik.user_with_testing.project.models.flight_models.FlightInfoResponse
import com.itzik.user_with_testing.project.repositories.IRepository
import com.itzik.user_with_testing.project.ui.navigation.ScreenContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class AppViewModel
@Inject constructor(
    private val repository: IRepository,
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

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList: StateFlow<List<User>> get() = _userList

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    init {
        checkLoginStatus()
        observeUserList()
    }
    private fun checkLoginStatus() {
        viewModelScope.launch {
            _isLoggedIn.value = repository.getSignedInUser() != null
        }
    }

    private fun observeUserList() {
        viewModelScope.launch {
            _userList.value = repository.getAllUsers()
        }
    }

    fun createUser() {
        viewModelScope.launch {
            val user = User(0, firstAndMiddleNameList, familyName, age, gender, email, password, phoneNumber, dateSelected, isSignedIn = true)
            repository.saveUser(user)
            _isLoggedIn.value = true
        }
    }


    fun signOut() {
        viewModelScope.launch {

            val loggedInUser = repository.getSignedInUser()
            if (loggedInUser != null) {
                loggedInUser.isSignedIn = false
                repository.saveUser(loggedInUser)
                _isLoggedIn.value = false
            }
        }
    }

    suspend fun getUsers(): Flow<List<User>> {
        val userList = flow {
            val updatedUserList = repository.getAllUsers()
            if (updatedUserList.isNotEmpty()) {
                emit(updatedUserList)
            } else return@flow
        }
        return userList
    }

    suspend fun getUserFromUserNameAndPassword(userName: String, password: String): Flow<User?> {
        val user = flow {
            val updatedUser = repository.getUserFromUserNameAndPassword(userName, password)
            emit(updatedUser)
        }
        return user
    }

    suspend fun getCodeName(
        searchCityQuery: String,
    ): MutableList<String> {
        val airportCodeName = mutableListOf<String>()
        getAirportCodeName(searchCityQuery)
            .collect {
                it.data.forEach { singleAirportCode ->
                    airportCodeName.add(singleAirportCode.shortName)
                }
            }
        Log.d("TAG", "list of code names")
        return airportCodeName
    }


    private suspend fun getAirportCodeName(query: String): Flow<AirportCodeName> {
        val codeNameResponseList = flow {
            val response = repository.getAirportCodeName(query)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(responseBody)
                } else Log.d("TAG", "Failure message: " + response.message())
                return@flow
            } else Log.d("TAG", "Failure message: " + response.message())
            return@flow
        }
        return codeNameResponseList
    }


    suspend fun getFlightInfo(
        sourceAirportCode: String,
        destinationAirportCode: String,
        takeoffDate: String,
        itineraryType: String,
        numberOfAdults: Int,
        classOfService: String,
        currency: String,
        returnDate: String,
    ): Flow<FlightInfoResponse> {
        val flightInfo = flow {
            val response = repository.getFlightInfo(
                sourceAirportCode,
                destinationAirportCode,
                takeoffDate,
                itineraryType,
                numberOfAdults,
                classOfService,
                currency,
                returnDate
            )
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    emit(responseBody)
                } else Log.d("TAG", "Failure message: " + response.message())
                return@flow
            } else Log.d("TAG", "Failure message: " + response.message())
            return@flow
        }
        return flightInfo
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

    fun maxOutAtAYearAhead(chosenDate: Calendar): Boolean {
        dateSelected = formattedDate(chosenDate)
        val chosenYear = chosenDate.get(Calendar.YEAR)
        val currentYear = Year.now().value
        Log.d("TAG", "chosenYear: $chosenYear, and currentYear: $currentYear")
        return chosenYear > currentYear
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
    private fun isValidEmail(): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


    private fun isValidPassword(): Boolean =
        password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".toRegex())


    fun isValidLoginEmail(email: String): Boolean =
        email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())


    fun isValidLoginPassword(password: String): Boolean =
        password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".toRegex())

    private fun isPhoneNumberOk(): Boolean = phoneNumber.isNotBlank()
    fun isAllFieldsOk(): Boolean =
        isValidName() && isValidEmail() && isValidPassword() && isPhoneNumberOk() && age > 9

    //fun createUser() = User(0, firstAndMiddleNameList, familyName, age, gender, email, password, phoneNumber, dateSelected)



    fun setErrors() {

    }

    fun moveToHomeScreen(
        isSuccessfulData: Boolean,
        navHostController: NavHostController,
        user: User,
    ) {
        if (isSuccessfulData) {
            navHostController.navigate(ScreenContainer.Search.route)
        }
    }

    fun isTextFieldsLoginValidFormat() = isValidLoginEmail(email) && isValidLoginPassword(password)
}