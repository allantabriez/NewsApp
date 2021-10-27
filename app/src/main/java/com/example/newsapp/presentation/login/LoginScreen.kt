package com.example.newsapp.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = getViewModel()
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isPassHidden = rememberSaveable {
        mutableStateOf(true)
    }

    Scaffold {
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
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                value = viewModel.email.value,
                onValueChange = { newValue ->
                    viewModel.setEmail(newValue)
                },
                maxLines = 1,
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                trailingIcon = {
                    if (viewModel.email.value.isNotBlank()) IconButton(onClick = {
                        viewModel.setEmail("")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Erase email"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(54.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                value = viewModel.password.value,
                onValueChange = { newValue ->
                    viewModel.setPass(newValue)
                },
                maxLines = 1,
                label = { Text(text = "Password") },
                visualTransformation = if (isPassHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                        keyboardManager?.hide()
                    }
                ),
                trailingIcon = {
                    IconButton(onClick = { isPassHidden.value = !isPassHidden.value }) {
                        Icon(
                            imageVector = if (isPassHidden.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (isPassHidden.value) "Show password" else "Hide password"
                        )
                    }
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                contentAlignment = Alignment.Center
            ) {
//            For loading when fetching data
//            LinearProgressIndicator(
//                modifier = Modifier.fillMaxWidth()
//            )
            }
            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}