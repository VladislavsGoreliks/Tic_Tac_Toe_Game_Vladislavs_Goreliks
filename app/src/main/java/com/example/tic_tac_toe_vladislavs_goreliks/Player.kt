package com.example.tic_tac_toe_vladislavs_goreliks

open class Player() {

    private var _name: String = ""
    private var _role: String = ""
    var name:String
        get()= _name
        set(value){
            _name=value
        }

    var  role:String
        get() = _role
        set(value){
            _role = value
        }
}