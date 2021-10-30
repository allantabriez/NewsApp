package com.example.newsapp.presentation.home.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R

@Composable
fun LoginDialog(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss.invoke()
        },
        title = {
            Text(text = stringResource(R.string.login_dialog_title))
        },
        text = {
            Text(text = stringResource(R.string.login_dialog_text))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm.invoke()
                }
            ) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )
}