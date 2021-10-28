package com.example.newsapp.presentation.login.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.newsapp.R

@Composable
fun EmailField(
    value: String = "",
    setValue: (String) -> Unit = {},
    onNext: () -> Unit = {}
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        value = value,
        onValueChange = {
            setValue.invoke(it)
        },
        maxLines = 1,
        label = {
            Text(text = stringResource(R.string.email))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNext.invoke()
            }
        ),
        trailingIcon = {
            if (value.isNotBlank()) IconButton(
                onClick = {
                    setValue.invoke("")
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.erase_email)
                )
            }
        },
        singleLine = true
    )
}