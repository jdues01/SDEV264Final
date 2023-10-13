package com.androidatc.finaltictactoe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var playerTurn = 1
    private var totalSelectedBoxes = 1
    private lateinit var playerOneLayout: LinearLayout
    private lateinit var playerTwoLayout: LinearLayout
    private lateinit var playerOneNameTextView: TextView
    private lateinit var playerTwoNameTextView: TextView
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView
    private lateinit var image4: ImageView
    private lateinit var image5: ImageView
    private lateinit var image6: ImageView
    private lateinit var image7: ImageView
    private lateinit var image8: ImageView
    private lateinit var image9: ImageView
    private val combinationsList = mutableListOf<IntArray>()
    private var playerOneName = ""
    private var playerTwoName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        playerOneName = intent.getStringExtra("playerOne") ?: ""
        playerTwoName = intent.getStringExtra("playerTwo") ?: ""

        playerOneNameTextView = findViewById(R.id.playerOneName)
        playerTwoNameTextView = findViewById(R.id.playerTwoName)

        playerOneNameTextView.text = playerOneName
        playerTwoNameTextView.text = playerTwoName

        playerOneLayout = findViewById(R.id.playerOneLayout)
        playerTwoLayout = findViewById(R.id.playerTwoLayout)

        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)
        image4 = findViewById(R.id.image4)
        image5 = findViewById(R.id.image5)
        image6 = findViewById(R.id.image6)
        image7 = findViewById(R.id.image7)
        image8 = findViewById(R.id.image8)
        image9 = findViewById(R.id.image9)

        combinationsList.add(intArrayOf(0, 1, 2))
        combinationsList.add(intArrayOf(3, 4, 5))
        combinationsList.add(intArrayOf(6, 7, 8))
        combinationsList.add(intArrayOf(0, 3, 6))
        combinationsList.add(intArrayOf(1, 4, 7))
        combinationsList.add(intArrayOf(2, 5, 8))
        combinationsList.add(intArrayOf(2, 4, 6))
        combinationsList.add(intArrayOf(0, 4, 8))


        image1.setOnClickListener {
            if (isBoxSelectable(0)) {
                performAction(image1, 0)
            }
        }

        image2.setOnClickListener {
            if (isBoxSelectable(1)) {
                performAction(image2, 1)
            }
        }

        image3.setOnClickListener {
            if (isBoxSelectable(2)) {
                performAction(image3, 2)
            }
        }

        image4.setOnClickListener {
            if (isBoxSelectable(3)) {
                performAction(image4, 3)
            }
        }

        image5.setOnClickListener {
            if (isBoxSelectable(4)) {
                performAction(image5, 4)
            }
        }

        image6.setOnClickListener {
            if (isBoxSelectable(5)) {
                performAction(image6, 5)
            }
        }

        image7.setOnClickListener {
            if (isBoxSelectable(6)) {
                performAction(image7, 6)
            }
        }

        image8.setOnClickListener {
            if (isBoxSelectable(7)) {
                performAction(image8, 7)
            }
        }

        image9.setOnClickListener {
            if (isBoxSelectable(8)) {
                performAction(image9, 8)
            }
        }

        val helpBtn = findViewById<Button>(R.id.helpBtn)
        helpBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, help::class.java)
            startActivity(intent)
        }

        val settingsBtn = findViewById<Button>(R.id.settingsBtn)
        settingsBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
        }
        }
    fun performAction(imageView: ImageView, selectedBoxPosition: Int) {
        boxPositions[selectedBoxPosition] = playerTurn

        Log.d("MainActivity", "Player One Name: $playerOneName")
        Log.d("MainActivity", "Player Two Name: $playerTwoName")

        val playerName = when (playerTurn) {
            1 -> playerOneName
            2 -> playerTwoName
            else -> ""
        }

        imageView.setImageResource(if (playerTurn == 1) R.drawable.tictac_x else R.drawable.tictac0)

        if (checkPlayerWin()) {
            Log.d("MainActivity", "Player Name in WinDialog: $playerName")
            WinDialog(this, "$playerName has won!", this@MainActivity).apply {
                setCancelable(false)
                show()
            }
        } else if (totalSelectedBoxes == 9) {
            WinDialog(this, "It is a tie!", this@MainActivity).apply {
                setCancelable(false)
                show()
            }
        } else {
            changePlayerTurn(3 - playerTurn)
            totalSelectedBoxes++
        }
    }



    fun changePlayerTurn(currentPlayerTurn: Int) {
    playerTurn = currentPlayerTurn

    if (playerTurn == 1) {
        playerOneLayout.setBackgroundResource(R.drawable.playeronelayout)
        playerTwoLayout.setBackgroundResource(R.drawable.playertwolayout)
    } else {
        playerTwoLayout.setBackgroundResource(R.drawable.playertwolayout)
        playerOneLayout.setBackgroundResource(R.drawable.playeronelayout)
    }
}

fun checkPlayerWin(): Boolean {
    var response = false

    for (i in 0 until combinationsList.size) {
        val combination = combinationsList[i]

        if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
            response = true
        }
    }
    return response
}

fun isBoxSelectable(boxPosition: Int): Boolean {
    var response = false

    if (boxPositions[boxPosition] == 0) {
        response = true
    }
    return response
}

fun restartGame() {
    boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    playerTurn = 1
    totalSelectedBoxes = 1

    image1.setImageResource(R.drawable.transparent_back)
    image2.setImageResource(R.drawable.transparent_back)
    image3.setImageResource(R.drawable.transparent_back)
    image4.setImageResource(R.drawable.transparent_back)
    image5.setImageResource(R.drawable.transparent_back)
    image6.setImageResource(R.drawable.transparent_back)
    image7.setImageResource(R.drawable.transparent_back)
    image8.setImageResource(R.drawable.transparent_back)
    image9.setImageResource(R.drawable.transparent_back)
}
}