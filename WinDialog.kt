package com.androidatc.finaltictactoe

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinDialog(context: Context?, private val message: String, private val mainActivity: MainActivity) : Dialog(context ?: throw IllegalArgumentException("Context must not be null")) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.win_dialog_layout)

        val messageTextView = findViewById<TextView>(R.id.message)
        val startAgainButton = findViewById<Button>(R.id.startAgain)
        messageTextView.text = message

        startAgainButton.setOnClickListener {
            mainActivity.restartGame()
            dismiss()
        }
    }
}