package com.example.newsapp.presentation.login.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@Composable
fun PassField(
    value: String = "",
    isError: Boolean = false,
    setValue: (String) -> Unit = {},
    onDone: () -> Unit = {}
) {
    val isPassHidden = rememberSaveable {
        mutableStateOf(true)
    }

    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            value = value,
            onValueChange = {
                setValue.invoke(it)
            },
            maxLines = 1,
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = if (isPassHidden.value) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onDone.invoke()
                }
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPassHidden.value = !isPassHidden.value
                    }
                ) {
                    Icon(
                        imageVector = if (isPassHidden.value) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (isPassHidden.value) stringResource(R.string.show_password) else stringResource(
                            R.string.hide_password
                        )
                    )
                }
            },
            singleLine = true
        )

        if (isError) Text(
            text = "Password is not valid",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}