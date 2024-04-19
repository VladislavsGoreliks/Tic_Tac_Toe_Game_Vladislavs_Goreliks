package com.example.tic_tac_toe_vladislavs_goreliks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val resultTv = findViewById<TextView>(R.id.result_tw)
        val restart_bt = findViewById<Button>(R.id.restart_bt)
        val winnerRole = intent.getStringExtra("result")
        if (winnerRole == "X"){
            resultTv.setText("Winner: " + intent.getStringExtra("firstPlayerName")+" "  + winnerRole)
        }else if (winnerRole == "0"){
            resultTv.setText("Winner: " + intent.getStringExtra("secondPlayerName")+" " + winnerRole)
        }else{
            resultTv.setText(winnerRole)
        }
        restart_bt.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
