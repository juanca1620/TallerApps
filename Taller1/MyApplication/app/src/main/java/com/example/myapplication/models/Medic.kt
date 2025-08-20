package com.example.myapplication.models

class Medic(
    id: Int,
    fullName: String,
    email: String,
    gender: String,
    val licenseNumber: String,
    val specialty: String,
    val incomeYear: Int,
    val salary: Double,
    val patientsAssigned: MutableList<String> = mutableListOf()
) : Person(id, fullName, email, gender)