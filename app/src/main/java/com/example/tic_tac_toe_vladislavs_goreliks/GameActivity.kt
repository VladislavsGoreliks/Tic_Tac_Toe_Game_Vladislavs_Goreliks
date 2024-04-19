package com.example.tic_tac_toe_vladislavs_goreliks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Array<Button>>
    private val game = Game()
    private var col = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)


        game.firstPlayer.name = intent.getStringExtra("first_player_name").toString()
        game.firstPlayer.role = "X"
        game.secondPlayer.name = intent.getStringExtra("second_player_name").toString()
        game.secondPlayer.role = "0"

        val roleChanged = intent.getBooleanExtra("switch_role", false)
        if (roleChanged) {
            game.switchPlayersNames()
        }

        game.singleGame = game.firstPlayer.name == "Computer" || game.secondPlayer.name == "Computer"

        buttons = initializeButtons(gridLayout)
        if(game.singleGame&&game.firstPlayer.name=="Computer"&&game.firstPlayerTurn){
            computerMovie()
        }
        setButtonClickListeners()

    }
    private fun initializeButtons(gridLayout: GridLayout): Array<Array<Button>> {
        val buttonsArray = Array(3) { i ->
            Array<Button>(3) { j ->
                Button(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 0
                        height = 0
                        columnSpec = GridLayout.spec(j, 1f)
                        rowSpec = GridLayout.spec(i, 1f)
                    }
                    gridLayout.addView(this)
                }
            }
        }
        return buttonsArray
    }

    private fun setButtonClickListeners() {
        for (i in buttons.indices) {
            for (j in buttons[i].indices) {
                buttons[i][j].setOnClickListener {
                    onButtonClick(i, j)
                }
            }
        }
    }

    private fun onButtonClick(row: Int, col: Int) {
        val button = buttons[row][col]
        var freeQuantity = 0
        for(i in 0..2){
            for (j in 0..2){
                if(buttons[i][j].text.isEmpty()){
                    freeQuantity += 1
                }
            }
        }
        if (button.text.isEmpty()) {
            button.text = if (game.firstPlayerTurn) "X" else "0"
            checkForWin()
            game.firstPlayerTurn = !game.firstPlayerTurn
            if (game.singleGame) {
                GlobalScope.launch {

                    computerMovie()
                    checkForWin()
                }


            }
        } else {
            Toast.makeText(this, "Cell is already occupied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun computerMovie() {

        var freeCells = mutableListOf<Pair<Int, Int>>()


        for (i in buttons.indices) {
            for (j in buttons[i].indices) {
                if (buttons[i][j].text.isEmpty()) {
                    freeCells.add(Pair(i, j))
                }
            }
        }

        if (freeCells.size == 0) {

        } else {

            var row: Int
            var col: Int
            do {
                row = (0..2).random()
                col = (0..2).random()
            } while (buttons[row][col].text.isNotEmpty())

            buttons[row][col].text = if (game.firstPlayerTurn) "X" else "0"


            game.firstPlayerTurn = !game.firstPlayerTurn

        }

    }

    private fun checkForWin() {
        var role = ""
        var quantity = 9
        for (i in buttons.indices) {
            for (j in buttons[i].indices) {
                if (buttons[i][j].text.isNotEmpty()) {
                    quantity -= 1
                }
            }
        }
        for (i in 0..2) {
            if (buttons[i][0].text.toString() == buttons[i][1].text.toString() && buttons[i][0].text.toString() == buttons[i][2].text.toString() && buttons[i][0].text.toString().isNotEmpty()) {
                role = buttons[i][0].text.toString()
                startActivityWithResult(role)
                return
            }
            if (buttons[0][i].text.toString() == buttons[1][i].text.toString() && buttons[0][i].text.toString() == buttons[2][i].text.toString() && buttons[0][i].text.toString().isNotEmpty()) {
                role = buttons[0][i].text.toString()
                startActivityWithResult(role)
                return
            }
        }
        if (buttons[0][0].text.toString() == buttons[1][1].text.toString() && buttons[0][0].text.toString() == buttons[2][2].text.toString() && buttons[0][0].text.toString().isNotEmpty()) {
            role = buttons[0][0].text.toString()
            startActivityWithResult(role)
            return
        }
        if (buttons[2][0].text.toString() == buttons[1][1].text.toString() && buttons[2][0].text.toString() == buttons[0][2].text.toString() && buttons[2][0].text.toString().isNotEmpty()) {
            role = buttons[2][0].text.toString()
            startActivityWithResult(role)
            return
        }
        if (quantity == 0) {
            role = "Game 50/50"
            startActivityWithResult(role)
            return
        }
    }

    private fun startActivityWithResult(role: String) {
        if (col == 0){
            col+=1
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("result", role)
            intent.putExtra("firstPlayerName",game.firstPlayer.name.toString())
            intent.putExtra("secondPlayerName",game.secondPlayer.name.toString())
            startActivity(intent)
        }
    }
}
