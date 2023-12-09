package com.itzik.user_with_testing.project.models

interface InterfaceAgeAndDateVerification {
    fun updateIsAgeValid(isAgeValid: Boolean)

    fun updateIsFutureDate(isDateValid: Boolean)
}
