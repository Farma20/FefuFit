package com.example.fefufit.presentation.main_menu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen() {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp)
        ) {
            mainMenuUppBar()
        }
    }
}

fun mainMenuUppBar() {

}
