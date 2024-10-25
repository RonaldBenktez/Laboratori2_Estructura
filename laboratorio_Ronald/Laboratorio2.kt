// Importando LinkedList, Queue y Stack desde la librería Java

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

// Definiendo clase para representar las pupusas
data class pupusa(val tipo: String, val cantidad: Int)

// Definiendo clase para representar una orden
data class orden (val cliente: String, val pupusas: List<pupusa>)

// Gestión de ordenes de pupusas
class sistemaPupuseria{
    private val ordenesPorDespachar: Queue<orden> = LinkedList() // Ordenes pendientes (cola)
    private val ordenesDespachadas: Stack<orden> = Stack() // Ordenes despachadas (pila)

    // Registrando una nueva orden
    fun nuevaOrden() {
        println("Ingresa el nombre del cliente: ")
        val nombreCliente = readLine() ?: ""

        println("Ingresa cuantos tipos de pupusas deseas ordenar: ")
        val cantidadT = readln()?.toIntOrNull() ?: 0

        val pupusas = mutableListOf<pupusa>()
        for (i in 1..cantidadT) {
            println("Ingresa el tipo de pupusa #$i: ")
            val tipo = readLine() ?: ""
            println("Ingresa la cantidad de pupusas de $tipo:")
            val cantidad = readLine()?.toIntOrNull() ?: 0
            pupusas.add(pupusa(tipo, cantidad))
        }

        val orden = orden(nombreCliente, pupusas)
        ordenesPorDespachar.add(orden)
        println("Orden existosa para $nombreCliente: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
    }

        // Visualizar ordenes pendientes
        fun historialOrdenesPendientes(){
            if (ordenesPorDespachar.isEmpty()){
                println("No existen ordenes pendientes.")
            } else {
                println("Ordenes pendientes: ")
                ordenesPorDespachar.forEachIndexed { index, orden ->
                    println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
                }
            }
        }

        // Despacho de orden
        fun despacharOrden(){
            if (ordenesPorDespachar.isNotEmpty()){
                val ordenCompletada = ordenesPorDespachar.poll()
                ordenesDespachadas.push(ordenCompletada)
                println("Despachando orden de: ${ordenCompletada.cliente}: ${ordenCompletada.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
            } else {
                println("No existen ordenes pendientes por despachar.")
            }
        }

        // Ordenes despachadas
        fun historialOrdenesDespachadas(){
            if(ordenesDespachadas.isEmpty()){
                println("No existen ordenes despachadas.")
            } else {
                println("Historial de ordenes despachadas: ")
                ordenesDespachadas.forEachIndexed { index, orden ->
                    println("${index + 1}. ${orden.cliente}: ${orden.pupusas.joinToString { "${it.cantidad} pupusas de ${it.tipo}" }}")
                }
            }
        }
    }

    fun mostrarMenu() {
        println("\n--- Bienvenido a Pupusería El Comalito ---")
        println("Selecciona una opción:")
        println("1. Registrar una nueva orden")
        println("2. Ver órdenes pendientes")
        println("3. Despachar orden")
        println("4. Ver órdenes despachadas")
        println("5. Salir")
    }

    fun capturarOpcion(): Int {
        println("Ingresa una opción: ")
        return readLine()?.toIntOrNull() ?: 0 // Validando si la opcion es valor valido
    }

    fun leerOpcion(opcion: Int, sistema: sistemaPupuseria){
        when (opcion){
            1 -> sistema.nuevaOrden()
            2 -> sistema.historialOrdenesPendientes()
            3 -> sistema.despacharOrden()
            4 -> sistema.historialOrdenesDespachadas()
            5 -> {
                println("Saliendo del programa...")
                return
            }
            else -> println("Ingresa una opción valida.")
        }
    }

    fun correrMenu(sistema: sistemaPupuseria){
        while(true){
            mostrarMenu()
            val opcion = capturarOpcion()
            if (opcion == 5) break // Rompemos el bucle si la opción es 5
            leerOpcion(opcion, sistema)
        }
    }

    fun main() {
        val sistema = sistemaPupuseria()
        //Ejecutando el menu interactivo
        correrMenu(sistema)
    }
