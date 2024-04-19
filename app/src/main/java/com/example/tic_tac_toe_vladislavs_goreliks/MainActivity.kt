package com.example.tic_tac_toe_vladislavs_goreliks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonSingle = findViewById<Button>(R.id.single_bt)
        val buttonMultiplayer = findViewById<Button>(R.id.multiplayer_bt)
        buttonSingle.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            val singlePlayer = true
            intent.putExtra("Single_Player",singlePlayer)
            startActivity(intent)
        }
        buttonMultiplayer.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            val multiplayer = true
            intent.putExtra("MultiPlayer",multiplayer)
            startActivity(intent)
        }
    }
}