package com.theways.bookshelf.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theways.bookshelf.ui.BookUiState
import com.theways.bookshelf.ui.BookViewModel
import com.theways.bookshell.R


enum class ScreenType {
    SEARCH_SCREEN,
    BOOKSHELF_SCREEN
}

@Composable
fun BookApp(
    modifier: Modifier = Modifier
) {
    val viewModel: BookViewModel = viewModel(factory = BookViewModel.Factory)
    val navController = rememberNavController()
    //val backStackEntry by navController.currentBackStackEntryAsState()
    //val currentScreen = ContentType.valueOf(backStackEntry?.destination?.route ?: ContentType.HOME_SCREEN.name)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name))},
                modifier = modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { navController.popBackStack(ScreenType.SEARCH_SCREEN.name, false) }) {
                        Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                    }
                }
            )
        }
    ) {
        BookHomeScreen(
            navController = navController,
            viewModel = viewModel,
            modifier = modifier.padding(it)
        )
    }
}

@Composable
fun BookHomeScreen(
    navController: NavHostController,
    viewModel: BookViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenType.SEARCH_SCREEN.name,
        modifier = modifier
    ) {
        composable(route = ScreenType.SEARCH_SCREEN.name) {
            SearchScreen(
                queryString = viewModel.queryString,
                onTextChanged = { viewModel.updateQueryString(it) },
                onSearchClicked = { viewModel.getBooks(viewModel.queryString)
                    navController.navigate(ScreenType.BOOKSHELF_SCREEN.name) }
            )
        }
        composable(route = ScreenType.BOOKSHELF_SCREEN.name) {
            when (viewModel.uiState)  {
                is BookUiState.Success -> BookListScreen(thumbnails = viewModel.thumbnails)
                is BookUiState.Loading -> LoadingScreen()
                is BookUiState.Error -> ErrorScreen(viewModel.errorMessage)
                else -> {}
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Text("Loading...")
}

@Composable
private fun ErrorScreen(errorMessage: String) {
    Text(errorMessage)
}