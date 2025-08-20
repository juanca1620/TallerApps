package com.example.myapplication.models

// Hospital.kt
class Hospital(
    val NIT: String,
    val direction: Direction,
    val name: String,
    val medics: MutableList<Medic> = mutableListOf(),
    val patients: MutableList<Pacient> = mutableListOf()
) {
    fun addMedic(medic: Medic) {
        medics.add(medic)
    }

    fun addPacient(pacient: Pacient) {
        patients.add(pacient)
    }

    fun searchMedic(id : Int) : Medic? {
        return medics.find { it.id == id }
    }

    fun searchPacient(id : Int) : Pacient? {
        return patients.find { it.id == id }
    }

    fun deleteMedic (id : Int) : Boolean {
        val medic = searchMedic(id)
        if (medic != null) {
            medics.remove(medic)
            return true
        }
        return false
    }

    fun deletePacient (id : Int) : Boolean {
        val patient = searchPacient(id)
        if (patient != null) {
            patients.remove(patient)
            return true
        }
        return false
    }

    fun updateMedic(id: Int, updatedMedic: Medic) : Boolean {
        val index = medics.indexOfFirst { it.id == id }
        if (index != -1) {
            medics[index] = updatedMedic
            return true
        }
        return false
    }

    fun updatePacient(id: Int, updatedPacient: Pacient) : Boolean {
        val index = patients.indexOfFirst { it.id == id }
        if (index != -1) {
            patients[index] = updatedPacient
            return true
        }
        return false
    }

    fun getMedicCost () : Double {
        var cost = 0.0
        for (medic in medics) {
            cost += medic.salary
        }
        return cost
    }

    fun getCostBySpecialty(specialty: String) : Double {
        var cost = 0.0
        for (medic in medics) {
            if (medic.specialty == specialty) {
                cost += medic.salary
            }
        }
        return cost
    }

    fun getGenderMPorcent (gender : String) : Double {
        var count = 0
        for (Pacient in patients) {
            if (Pacient.gender == gender) {
                count++
            }
        }
        return (count.toDouble() / medics.size.toDouble()) * 100
    }

    fun getMedicAcountBySpecialty(specialty: String) : Int {
        var count = 0
        for (medic in medics) {
            if (medic.specialty == specialty) {
                count++
            }
        }
        return count
    }

    fun getSpecialityByMostOldMedic() : String {
        var mostOldMedic = medics[0]
        for (medic in medics) {
            if (medic.incomeYear > mostOldMedic.incomeYear) {
                mostOldMedic = medic
            }
        }
        return mostOldMedic.specialty
    }
}
