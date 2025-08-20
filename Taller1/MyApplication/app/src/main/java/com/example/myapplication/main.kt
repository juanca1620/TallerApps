package com.example.myapplication

import com.example.myapplication.models.*

fun main() {
    val hospital = Hospital(
        NIT = "900123456",
        direction = Direction(
            street = "Calle 10",
            streetNumber = "25A",
            city = "Bogotá",
            neighborhood = "Centro",
            postalCode = "110111"
        ),
        name = "Hospital Central"
    )

    while (true) {
        println("\n===== MENÚ HOSPITAL =====")
        println("1) CRUD Médicos")
        println("2) CRUD Pacientes")
        println("3) Total salarios de todos los médicos")
        println("4) Total salarios por especialidad")
        println("5) Porcentaje por género (usa Hospital.getGenderMPorcent)")
        println("6) Cantidad de médicos por especialidad")
        println("7) Médico más antiguo: especialidad")
        println("0) Salir")
        when (readInt("Elige opción")) {
            1 -> menuCrudMedicos(hospital)
            2 -> menuCrudPacientes(hospital)
            3 -> println("💰 Total salarios: ${hospital.getMedicCost()}")
            4 -> {
                val esp = readText("Especialidad")
                println("💼 Total en $esp: ${hospital.getCostBySpecialty(esp)}")
            }
            5 -> {
                val g = readText("Género a consultar (ej: M o F)")
                println("📊 Porcentaje: ${hospital.getGenderMPorcent(g)}%")
            }
            6 -> {
                val esp = readText("Especialidad")
                println("📈 Cantidad de médicos en $esp: ${hospital.getMedicAcountBySpecialty(esp)}")
            }
            7 -> {
                if (hospital.medics.isEmpty()) {
                    println("⚠️ No hay médicos registrados.")
                } else {
                    println("🏅 Especialidad del médico más antiguo: ${hospital.getSpecialityByMostOldMedic()}")
                }
            }
            0 -> { println("👋 Hasta luego."); return }
            else -> println("❌ Opción inválida")
        }
    }
}

/* ===================== CRUD MÉDICOS ===================== */

private fun menuCrudMedicos(hospital: Hospital) {
    while (true) {
        println("\n--- CRUD MÉDICOS ---")
        println("1) Crear")
        println("2) Listar")
        println("3) Buscar por ID")
        println("4) Actualizar")
        println("5) Eliminar")
        println("0) Volver")
        when (readInt("Elige opción")) {
            1 -> crearMedico(hospital)
            2 -> listarMedicos(hospital)
            3 -> buscarMedico(hospital)
            4 -> actualizarMedico(hospital)
            5 -> eliminarMedico(hospital)
            0 -> return
            else -> println("❌ Opción inválida")
        }
    }
}

private fun crearMedico(hospital: Hospital) {
    val id = readInt("ID")
    val fullName = readText("Nombre completo")
    val email = readText("Email")
    val gender = readText("Género")
    val license = readText("Licencia profesional")
    val specialty = readText("Especialidad")
    val year = readInt("Año de ingreso")
    val salary = readDouble("Salario")

    val medic = Medic(id, fullName, email, gender, license, specialty, year, salary)
    hospital.addMedic(medic)
    println("✅ Médico agregado.")
}

private fun listarMedicos(hospital: Hospital) {
    if (hospital.medics.isEmpty()) {
        println("No hay médicos registrados.")
        return
    }
    println("\nID | Nombre | Especialidad | AñoIngreso | Salario")
    hospital.medics.forEach {
        println("${it.id} | ${it.fullName} | ${it.specialty} | ${it.incomeYear} | ${it.salary}")
    }
}

private fun buscarMedico(hospital: Hospital) {
    val id = readInt("ID a buscar")
    val medic = hospital.searchMedic(id)
    if (medic == null) println("❌ No encontrado")
    else println("✔️ ${medic.id} - ${medic.fullName} (${medic.specialty}), ingreso ${medic.incomeYear}, salario ${medic.salary}")
}

private fun actualizarMedico(hospital: Hospital) {
    val id = readInt("ID del médico a actualizar")
    val old = hospital.searchMedic(id)
    if (old == null) {
        println("❌ No existe un médico con ese ID.")
        return
    }
    val newId = readInt("Nuevo ID", old.id)
    val fullName = readText("Nuevo nombre", old.fullName)
    val email = readText("Nuevo email", old.email)
    val gender = readText("Nuevo género", old.gender)
    val license = readText("Nueva licencia", old.licenseNumber)
    val specialty = readText("Nueva especialidad", old.specialty)
    val year = readInt("Nuevo año de ingreso", old.incomeYear)
    val salary = readDouble("Nuevo salario", old.salary)

    val updated = Medic(newId, fullName, email, gender, license, specialty, year, salary, old.patientsAssigned)
    val ok = hospital.updateMedic(id, updated)
    println(if (ok) "✏️ Médico actualizado." else "❌ No se pudo actualizar.")
}

private fun eliminarMedico(hospital: Hospital) {
    val id = readInt("ID a eliminar")
    val ok = hospital.deleteMedic(id)
    println(if (ok) "🗑️ Médico eliminado." else "❌ No se encontró.")
}

/* ===================== CRUD PACIENTES ===================== */

private fun menuCrudPacientes(hospital: Hospital) {
    while (true) {
        println("\n--- CRUD PACIENTES ---")
        println("1) Crear")
        println("2) Listar")
        println("3) Buscar por ID")
        println("4) Actualizar")
        println("5) Eliminar")
        println("0) Volver")
        when (readInt("Elige opción")) {
            1 -> crearPaciente(hospital)
            2 -> listarPacientes(hospital)
            3 -> buscarPaciente(hospital)
            4 -> actualizarPaciente(hospital)
            5 -> eliminarPaciente(hospital)
            0 -> return
            else -> println("❌ Opción inválida")
        }
    }
}

private fun crearPaciente(hospital: Hospital) {
    val id = readInt("ID")
    val fullName = readText("Nombre completo")
    val email = readText("Email")
    val gender = readText("Género")
    val phone = readText("Teléfono")

    println("— Dirección —")
    val street = readText("Calle")
    val number = readText("Número")
    val city = readText("Ciudad")
    val neighborhood = readText("Barrio")
    val postal = readText("Código postal")

    val pacient = Pacient(id, fullName, email, gender, phone,
        Direction(street, number, city, neighborhood, postal))
    hospital.addPacient(pacient)
    println("✅ Paciente agregado.")
}

private fun listarPacientes(hospital: Hospital) {
    if (hospital.patients.isEmpty()) {
        println("No hay pacientes registrados.")
        return
    }
    println("\nID | Nombre | Teléfono | Ciudad")
    hospital.patients.forEach {
        println("${it.id} | ${it.fullName} | ${it.phoneNumber} | ${it.direction.city}")
    }
}

private fun buscarPaciente(hospital: Hospital) {
    val id = readInt("ID a buscar")
    val p = hospital.searchPacient(id)
    if (p == null) println("❌ No encontrado")
    else {
        val d = p.direction
        println("✔️ ${p.id} - ${p.fullName} (${p.email}) • ${p.phoneNumber}")
        println("   Dir: ${d.street} #${d.streetNumber}, ${d.neighborhood}, ${d.city} (${d.postalCode})")
    }
}

private fun actualizarPaciente(hospital: Hospital) {
    val id = readInt("ID del paciente a actualizar")
    val old = hospital.searchPacient(id)
    if (old == null) {
        println("❌ No existe un paciente con ese ID.")
        return
    }
    val newId = readInt("Nuevo ID", old.id)
    val fullName = readText("Nuevo nombre", old.fullName)
    val email = readText("Nuevo email", old.email)
    val gender = readText("Nuevo género", old.gender)
    val phone = readText("Nuevo teléfono", old.phoneNumber)

    val dOld = old.direction
    val street = readText("Calle", dOld.street)
    val number = readText("Número", dOld.streetNumber)
    val city = readText("Ciudad", dOld.city)
    val neighborhood = readText("Barrio", dOld.neighborhood)
    val postal = readText("Código postal", dOld.postalCode)

    val updated = Pacient(newId, fullName, email, gender, phone,
        Direction(street, number, city, neighborhood, postal))

    val ok = hospital.updatePacient(id, updated)
    println(if (ok) "✏️ Paciente actualizado." else "❌ No se pudo actualizar.")
}

private fun eliminarPaciente(hospital: Hospital) {
    val id = readInt("ID a eliminar")
    val ok = hospital.deletePacient(id)
    println(if (ok) "🗑️ Paciente eliminado." else "❌ No se encontró.")
}

/* ===================== UTILIDADES DE ENTRADA ===================== */

private fun readText(prompt: String, default: String? = null): String {
    print(if (default == null) "$prompt: " else "$prompt [$default]: ")
    val s = readln()
    return if (default != null && s.isBlank()) default else s
}

private fun readInt(prompt: String, default: Int? = null): Int {
    while (true) {
        print(if (default == null) "$prompt: " else "$prompt [$default]: ")
        val s = readln()
        if (default != null && s.isBlank()) return default
        s.toIntOrNull()?.let { return it }
        println("   → Ingresa un número válido.")
    }
}

private fun readDouble(prompt: String, default: Double? = null): Double {
    while (true) {
        print(if (default == null) "$prompt: " else "$prompt [$default]: ")
        val s = readln()
        if (default != null && s.isBlank()) return default
        s.replace(',', '.').toDoubleOrNull()?.let { return it }
        println("   → Ingresa un número (usa punto o coma).")
    }
}
