package com.picpay.desafio.android.presentation.feature.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.presentation.components.ErrorScreen
import com.picpay.desafio.android.presentation.components.EmptyScreen
import com.picpay.desafio.android.presentation.components.UserItem
import com.picpay.desafio.android.presentation.theme.Green
import com.picpay.desafio.android.presentation.theme.PicPayTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersRoute(
    viewModel: UsersViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    UsersScreen(
        uiState = uiState,
        onRetry = viewModel::retry
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    uiState: UsersUiState,
    onRetry: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.contacts_title),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        containerColor = Color.Black
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        color = Green,
                        strokeWidth = 3.dp
                    )
                }

                uiState.error != null -> {
                    ErrorScreen(
                        message = uiState.error,
                        onRetry = onRetry,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                uiState.users.isEmpty() -> {
                    EmptyScreen(modifier = Modifier.fillMaxSize())
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        items(uiState.users) { user ->
                            UserItem(
                                user = user,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UsersScreenPreview() {
    PicPayTheme {
        UsersScreen(
            uiState = UsersUiState(
                users = listOf(
                    User(
                        id = 1,
                        name = "Eduardo Santos",
                        username = "@eduardo.santos",
                        img = "https://randomuser.me/api/portraits/men/9.jpg"
                    ),
                    User(
                        id = 2,
                        name = "Marina Silva",
                        username = "@marina.silva",
                        img = "https://randomuser.me/api/portraits/women/9.jpg"
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UsersScreenLoadingPreview() {
    PicPayTheme {
        UsersScreen(
            uiState = UsersUiState(isLoading = true)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UsersScreenErrorPreview() {
    PicPayTheme {
        UsersScreen(
            uiState = UsersUiState(error = "Falha na conexão de rede")
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UsersScreenEmptyPreview() {
    PicPayTheme {
        UsersScreen(
            uiState = UsersUiState(users = emptyList())
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UsersScreenManyUsersPreview() {
    PicPayTheme {
        UsersScreen(
            uiState = UsersUiState(
                users = listOf(
                    User(
                        img = "https://randomuser.me/api/portraits/women/1.jpg",
                        name = "Ana Silva",
                        id = 1,
                        username = "@ana.silva"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/men/2.jpg",
                        name = "Bruno Costa",
                        id = 2,
                        username = "@bruno.costa"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/women/3.jpg",
                        name = "Carla Oliveira",
                        id = 3,
                        username = "@carla.oliveira"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/men/4.jpg",
                        name = "Diego Santos",
                        id = 4,
                        username = "@diego.santos"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/women/5.jpg",
                        name = "Eduarda Lima",
                        id = 5,
                        username = "@eduarda.lima"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/men/6.jpg",
                        name = "Felipe Rodrigues",
                        id = 6,
                        username = "@felipe.rodrigues"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/women/7.jpg",
                        name = "Gabriela Martins",
                        id = 7,
                        username = "@gabriela.martins"
                    ),
                    User(
                        img = "https://randomuser.me/api/portraits/men/8.jpg",
                        name = "Henrique Alves",
                        id = 8,
                        username = "@henrique.alves"
                    )
                )
            )
        )
    }
}