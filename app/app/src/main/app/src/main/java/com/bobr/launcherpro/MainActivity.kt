package com.bobr.launcherpro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF07110D)
private val Panel = Color(0xFF10221A)
private val Green = Color(0xFF55E08A)
private val Muted = Color(0xFF9BB5A5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BobrApp()
        }
    }
}

@Composable
fun BobrApp() {
    var page by remember { mutableStateOf("Главная") }
    var version by remember { mutableStateOf("1.20.1") }
    var ram by remember { mutableFloatStateOf(3f) }

    val context = LocalContext.current

    val pages = listOf(
        "Главная",
        "Версии",
        "Моды",
        "Ресурспаки",
        "Профиль",
        "Настройки"
    )

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Bg,
            surface = Panel,
            primary = Green
        )
    ) {
        Scaffold(
            containerColor = Bg,
            bottomBar = {
                NavigationBar(
                    containerColor = Panel
                ) {
                    val icons = listOf(
                        Icons.Default.Home,
                        Icons.Default.VideogameAsset,
                        Icons.Default.Extension,
                        Icons.Default.Palette,
                        Icons.Default.Person,
                        Icons.Default.Settings
                    )

                    pages.forEachIndexed { i, label ->
                        NavigationBarItem(
                            selected = page == label,
                            onClick = { page = label },
                            icon = {
                                Icon(
                                    icons[i],
                                    contentDescription = label
                                )
                            },
                            label = {
                                Text(label, fontSize = 9.sp)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Green,
                                selectedTextColor = Green,
                                indicatorColor = Color(0xFF1D3A2B),
                                unselectedIconColor = Muted,
                                unselectedTextColor = Muted
                            )
                        )
                    }
                }
            }
        ) { padding ->

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(18.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🦫", fontSize = 30.sp)

                    Spacer(Modifier.width(10.dp))

                    Column {
                        Text(
                            "BOBR LAUNCHER",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            "PRO • JAVA EDITION",
                            fontSize = 10.sp,
                            color = Green
                        )
                    }
                }

                Spacer(Modifier.height(22.dp))

                when (page) {

                    "Главная" -> {

                        Text(
                            "ПРИВЕТ, ИГРОК!",
                            color = Muted
                        )

                        Spacer(Modifier.height(12.dp))

                        CardBlock {

                            Text(
                                "ТВОЯ СБОРКА",
                                color = Muted
                            )

                            Text(
                                "Minecraft Java $version",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                "Профиль: Игрок",
                                color = Muted
                            )

                            Spacer(Modifier.height(18.dp))

                            Button(
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        "Игровой движок ещё не подключён!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Green
                                )
                            ) {
                                Text(
                                    "▶ ИГРАТЬ",
                                    color = Bg,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 17.sp
                                )
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        Text(
                            "БЫСТРЫЙ ДОСТУП",
                            color = Muted
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            SmallTile("🎮", "Версии") {
                                page = "Версии"
                            }

                            SmallTile("🧩", "Моды") {
                                page = "Моды"
                            }

                            SmallTile("⚙️", "Настройки") {
                                page = "Настройки"
                            }
                        }

                        Spacer(Modifier.height(18.dp))

                        CardBlock {
                            Text(
                                "СТАТУС ЛАУНЧЕРА",
                                color = Green
                            )

                            Text(
                                "Интерфейс готов. Игровой движок не подключён.",
                                color = Muted
                            )
                        }
                    }

                    "Версии" -> {

                        Text(
                            "ВЫБОР ВЕРСИИ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(12.dp))

                        listOf(
                            "1.21.1",
                            "1.20.1",
                            "1.19.4",
                            "1.18.2",
                            "1.16.5",
                            "1.12.2",
                            "1.8.9"
                        ).forEach {
                            SelectRow(
                                it,
                                version == it
                            ) {
                                version = it
                            }
                        }
                    }

                    "Моды" -> {
                        InfoPage(
                            "МЕНЕДЖЕР МОДОВ",
                            "Здесь будет установка модов Forge и Fabric.",
                            "🧩"
                        )
                    }

                    "Ресурспаки" -> {
                        InfoPage(
                            "РЕСУРСПАКИ",
                            "Управление ресурс-паками Minecraft.",
                            "🎨"
                        )
                    }

                    "Профиль" -> {
                        InfoPage(
                            "ПРОФИЛЬ ИГРОКА",
                            "Локальный профиль игрока.",
                            "👤"
                        )
                    }

                    "Настройки" -> {

                        Text(
                            "НАСТРОЙКИ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(12.dp))

                        CardBlock {

                            Text(
                                "Оперативная память: ${ram.toInt()} ГБ"
                            )

                            Slider(
                                value = ram,
                                onValueChange = { ram = it },
                                valueRange = 1f..8f,
                                colors = SliderDefaults.colors(
                                    thumbColor = Green,
                                    activeTrackColor = Green
                                )
                            )

                            Text(
                                "Демонстрационная настройка RAM.",
                                color = Muted
                            )
                        }

                        Spacer(Modifier.height(12.dp))

                        CardBlock {
                            Text(
                                "BOBR LAUNCHER PRO",
                                color = Green
                            )

                            Text(
                                "Версия 1.0 • Android",
                                color = Muted
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardBlock(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Panel
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(18.dp),
            content = content
        )
    }
}

@Composable
fun SmallTile(
    emoji: String,
    title: String,
    onClick: () -> Unit
) {
    Card(
        Modifier
            .weight(1f)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = Panel
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emoji, fontSize = 24.sp)
            Text(title, fontSize = 11.sp)
        }
    }
}

@Composable
fun SelectRow(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                Color(0xFF1D3A2B)
            else Panel
        )
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Minecraft $label",
                Modifier.weight(1f)
            )

            if (selected) {
                Text(
                    "ВЫБРАНО",
                    color = Green,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun InfoPage(
    title: String,
    description: String,
    emoji: String
) {
    Text(
        title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(Modifier.height(12.dp))

    CardBlock {
        Text(emoji, fontSize = 34.sp)

        Spacer(Modifier.height(10.dp))

        Text(
            description,
            color = Muted
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "РАЗДЕЛ ПОДГОТОВЛЕН",
            color = Green
        )
    }
}
