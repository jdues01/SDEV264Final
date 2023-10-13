package com.androidatc.finaltictactoe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddPlayers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_players)

        val playerOne: EditText = findViewById(R.id.playerOneName)
        val playerTwo: EditText = findViewById(R.id.playerTwoName)
        val startGame: Button = findViewById(R.id.startGame)

        startGame.setOnClickListener {
            val getPlayerOneName: String = playerOne.text.toString()
            val getPlayerTwoName: String = playerTwo.text.toString()

            if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                Toast.makeText(this@AddPlayers, "Please enter player names", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@AddPlayers, MainActivity::class.java)
                intent.putExtra("playerOne", getPlayerOneName)
                intent.putExtra("playerTwo", getPlayerTwoName)

                // Use Log for debugging
                Log.d("AddPlayers", "Starting MainActivity with playerOne: $getPlayerOneName, playerTwo: $getPlayerTwoName")

                startActivity(intent)
            }
        }


        val helpButton = findViewById<Button>(R.id.helpButton)
        helpButton.setOnClickListener {
            val intent = Intent(this@AddPlayers, help::class.java)
            startActivity(intent)
        }

        val settingsBtn = findViewById<Button>(R.id.settingsButton)
        settingsBtn.setOnClickListener {
            val intent = Intent(this@AddPlayers, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}