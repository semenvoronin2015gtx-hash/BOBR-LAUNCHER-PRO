package com.bobr.launcherpro

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(20, 20, 30)
        window.navigationBarColor = Color.BLACK

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(25, 35, 25, 35)
        layout.setBackgroundColor(Color.rgb(18, 20, 32))

        val title = TextView(this)
        title.text = "🦫 BOBR LAUNCHER PRO"
        title.textSize = 27f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER
        layout.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Minecraft Java Edition Launcher"
        subtitle.textSize = 15f
        subtitle.setTextColor(Color.LTGRAY)
        subtitle.gravity = Gravity.CENTER
        layout.addView(subtitle)

        val space = TextView(this)
        space.text = "\n\n"
        layout.addView(space)

        addButton(layout, "▶ ИГРАТЬ") {
            Toast.makeText(this,
                "Сначала необходимо установить Minecraft Java Runtime",
                Toast.LENGTH_LONG).show()
        }

        addButton(layout, "📦 ВЕРСИИ MINECRAFT") {
            showMessage("Выбор версий Minecraft")
        }

        addButton(layout, "⚙ НАСТРОЙКИ") {
            showMessage("Настройки BOBR Launcher")
        }

        addButton(layout, "👤 АККАУНТ") {
            showMessage("Управление аккаунтом")
        }

        addButton(layout, "ℹ О ЛАУНЧЕРЕ") {
            showMessage("BOBR LAUNCHER PRO V1.0")
        }

        setContentView(layout)
    }

    private fun addButton(
        layout: LinearLayout,
        text: String,
        action: () -> Unit
    ) {
        val button = Button(this)
        button.text = text
        button.textSize = 16f
        button.setOnClickListener { action() }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            65
        )
        params.setMargins(0, 9, 0, 9)

        layout.addView(button, params)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
