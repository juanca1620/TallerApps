package com.example.myapplication.models

class Pacient(
    id: Int,
    fullName: String,
    email: String,
    gender: String,
    val phoneNumber: String,
    val direction: Direction
) : Person(id, fullName, email, gender)
