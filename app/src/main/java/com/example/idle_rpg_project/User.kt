package com.example.idle_rpg_project

class User {
    var id : Int = 0
    var username : String = ""
    var name : String = ""
    var lastName : String = ""
    var email : String = ""
    var password : String = ""
    var urlImage : String = ""
    var idGuild : Int = 0

    constructor(username:String, name:String, lastName:String, password:String, email:String, urlImage:String){
        this.username = username
        this.name = name
        this.lastName = lastName
        this.password = password
        this.email = email
        this.urlImage = urlImage
    }

    constructor(){}
}