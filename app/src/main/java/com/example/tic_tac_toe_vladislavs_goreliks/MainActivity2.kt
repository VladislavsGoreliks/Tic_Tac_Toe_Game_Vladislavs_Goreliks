package com.example.tic_tac_toe_vladislavs_goreliks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.activity.enableEdgeToEdge

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        val firstPlayerName_et = findViewById<EditText>(R.id.firstPlayerName_et)
        val secondPlayerName_et = findViewById<EditText>(R.id.secondPlayerName_et)
        val start_bt = findViewById<Button>(R.id.start_bt)
        val xo_switch=findViewById<Switch>(R.id.xo_switch)

        var secondPlayerName = ""

        var roleSwitch = false

        xo_switch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                roleSwitch = true
            }else{
                roleSwitch = false
            }
        }
        val singleGame = intent.getBooleanExtra("Single_Player",false)

        if(singleGame) {
            secondPlayerName_et.visibility = View.INVISIBLE
            xo_switch.visibility = View.VISIBLE
            secondPlayerName = "Computer"
        }else{
            secondPlayerName_et.visibility = View.VISIBLE
            xo_switch.visibility = View.INVISIBLE
        }
        val intent = Intent(this, GameActivity::class.java)
        start_bt.setOnClickListener {
            if(singleGame){
                intent.putExtra("switch_role",roleSwitch)
                intent.putExtra("first_player_name",firstPlayerName_et.text.toString())
                intent.putExtra("second_player_name",secondPlayerName)
                startActivity(intent)
            }else{
                intent.putExtra("switch_role",roleSwitch)
                intent.putExtra("first_player_name",firstPlayerName_et.text.toString())
                intent.putExtra("second_player_name",secondPlayerName_et.text.toString())
                startActivity(intent)
            }
        }
    }
}