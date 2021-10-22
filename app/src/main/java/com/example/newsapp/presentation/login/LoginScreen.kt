package com.example.newsapp.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen() {
//    var isPassHidden by rememberSaveable {
//        mutableStateOf(true)
//    }
    val viewModel: LoginViewModel = getViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "TimeRomanNews.",
            fontSize = 28.sp,
            modifier = Modifier.padding(top = 62.dp),
        )
        Spacer(modifier = Modifier.height(94.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.email.value,
            onValueChange = { newValue ->
                viewModel.setEmail(newValue)
            },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
        Spacer(modifier = Modifier.height(54.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.password.value,
            onValueChange = { newValue ->
                viewModel.setPass(newValue)
            },
            label = { Text(text = "Password") },
//            visualTransformation = if (isPassHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            trailingIcon = {
//                IconButton(onClick = { isPassHidden = !isPassHidden }) {
//
//                }
//            }
        )
        Spacer(modifier = Modifier.height(54.dp))
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Login")
        }
    }
}