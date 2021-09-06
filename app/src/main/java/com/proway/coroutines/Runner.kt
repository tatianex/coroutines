package com.proway.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        runFor()
    }
    println("STARTING")
}

suspend fun runFor() {
    for (banana in 0..10) {
        delay(1000)
        println("Position $banana")
    }
}