package com.theways.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(
    queryString: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    focusManager: FocusManager = LocalFocusManager.current
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        OutlinedTextField(
            value = queryString,
            enabled = true,
            onValueChange = onTextChanged,
            label = { Text(text = "Input query string here") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        Button(onClick = onSearchClicked, modifier = modifier.padding(top = 16.dp)) {
            Text("Search")
        }
    }
}