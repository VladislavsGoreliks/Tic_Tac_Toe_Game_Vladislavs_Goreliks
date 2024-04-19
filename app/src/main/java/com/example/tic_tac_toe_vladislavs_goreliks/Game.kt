package com.example.tic_tac_toe_vladislavs_goreliks

open class Game()  {
    private var _firstPlayer=Player()
    private var _secondPlayer=Player()
    private var _singleGame = true
    private var _firstPlayerTurn = true
    var firstPlayerTurn:Boolean
        get() = _firstPlayerTurn
        set(value){
            _firstPlayerTurn=value
        }
    var singleGame:Boolean
        get() = _singleGame
        set(value){
            _singleGame=value
        }
    var firstPlayer:Player
        get() = _firstPlayer
        set(value){
            _firstPlayer = value
        }
    var secondPlayer : Player
        get() = _secondPlayer
        set(value){
            _secondPlayer = value
        }

    fun switchPlayersNames(){
        val value = _firstPlayer.name
        _firstPlayer.name = _secondPlayer.name
        _secondPlayer.name = value

    }
}