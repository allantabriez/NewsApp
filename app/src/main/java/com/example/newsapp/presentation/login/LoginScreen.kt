package com.example.newsapp.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.presentation.login.composables.EmailField
import com.example.newsapp.presentation.login.composables.ErrorDialog
import com.example.newsapp.presentation.login.composables.PassField
import com.example.newsapp.presentation.theme.NewsAppTheme
import com.example.newsapp.utils.Resource
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    onLogin: () -> Unit = {}
) {
    val viewModel: LoginViewModel = getViewModel()
    val keyboardManager = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.login_title),
                style = MaterialTheme.typography.h5.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.padding(top = 62.dp),
            )
            Spacer(modifier = Modifier.height(94.dp))
            EmailField(
                value = viewModel.email.value,
                isError = viewModel.emailError.value,
                setValue = {
                    viewModel.setEmail(it)
                },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
            Spacer(modifier = Modifier.height(54.dp))
            PassField(
                value = viewModel.password.value,
                isError = viewModel.passError.value,
                setValue = {
                    viewModel.setPass(it)
                },
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                    keyboardManager?.hide()
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.state.value is Resource.Loading) LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = {
                    if (viewModel.areInputsValid()) viewModel.doLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.W700)
                )
            }
            when (viewModel.state.value) {
                is Resource.Success -> {
                    viewModel.resetState()
                    onLogin.invoke()
                }
                is Resource.Error -> ErrorDialog()
                else -> Unit
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    NewsAppTheme {
        LoginScreen()
    }
}