package com.example.newsapp.presentation.login.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.newsapp.R

@Composable
fun ErrorDialog() {

    val isDialogOpen = remember { mutableStateOf(true) }
    val dialogDesc = stringResource(R.string.content_error_dialog)

    if (isDialogOpen.value) AlertDialog(
        onDismissRequest = {
            isDialogOpen.value = false
        },
        title = {
            Text(text = stringResource(R.string.login_dialog_title))
        },
        text = {
            Text(text = stringResource(R.string.something_went_wrong))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    isDialogOpen.value = false
                }
            ) {
                Text(text = stringResource(R.string.ok))
            }
        },
        modifier = Modifier.semantics { contentDescription = dialogDesc }
    )
}