package com.android.movieapp.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.let { navigate(direction) }
}

fun NavController.safeNavigate(direction: Int) {
    currentDestination?.getAction(direction)?.let { navigate(direction) }
}

fun NavController.safeNavigate(actionId: Int, args: Bundle?) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId, args) }
}