package com.picpay.desafio.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.picpay.desafio.android.domain.models.User
import com.picpay.desafio.android.presentation.theme.PicPayTheme

@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 0.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.img,
            contentDescription = "Foto de ${user.name}",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.username,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
            Text(
                text = user.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UserItemPreview() {
    PicPayTheme {
        UserItem(
            user = User(
                id = 1,
                name = "Eduardo Santos",
                username = "@eduardo.santos",
                img = "https://randomuser.me/api/portraits/men/9.jpg"
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UserItemLongNamePreview() {
    PicPayTheme {
        UserItem(
            user = User(
                id = 2,
                name = "Maria Fernanda Oliveira Silva",
                username = "@maria.fernanda.silva",
                img = "https://randomuser.me/api/portraits/women/25.jpg"
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UserItemShortNamePreview() {
    PicPayTheme {
        UserItem(
            user = User(
                id = 3,
                name = "Ana",
                username = "@ana",
                img = "https://randomuser.me/api/portraits/women/5.jpg"
            )
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun UserItemListPreview() {
    PicPayTheme {
        Column(modifier = Modifier.background(Color.Black)) {
            UserItem(
                user = User(
                    id = 1,
                    name = "Bruno Costa",
                    username = "@bruno.costa",
                    img = "https://randomuser.me/api/portraits/men/15.jpg"
                )
            )
            UserItem(
                user = User(
                    id = 2,
                    name = "Carla Oliveira",
                    username = "@carla.oliveira",
                    img = "https://randomuser.me/api/portraits/women/20.jpg"
                )
            )
            UserItem(
                user = User(
                    id = 3,
                    name = "Diego Santos",
                    username = "@diego.santos",
                    img = "https://randomuser.me/api/portraits/men/30.jpg"
                )
            )
        }
    }
}