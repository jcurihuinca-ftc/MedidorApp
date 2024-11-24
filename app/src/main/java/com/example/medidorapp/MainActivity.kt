package com.example.medidorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.medidorapp.ui.theme.MedidorAppTheme
import com.example.medidorapp.viewmodel.MedidorViewModel
import androidx.activity.viewModels

class MainActivity : ComponentActivity() {
    private val viewModel: MedidorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedidorAppTheme {
                Navigation(viewModel)
            }
        }
    }
}
