package com.space.designpatterns.behavioral

import java.lang.Thread.sleep
import kotlin.random.Random

/** [Command] interface is needed for its inherited classes to be one type
It will be used in [CommandProcessor] */
interface Command {
    fun execute()
}

/** Processor to add new commands to its queue and execute one after another. */
class CommandProcessor {
    private val commandsQueue = mutableListOf<Command>()

    fun add(command: Command) = apply { commandsQueue.add(command) }
    fun process() = apply { commandsQueue.forEach { it.execute() }; commandsQueue.clear() }
}

/** Example commands for order */
class RegisterNewOrderCommand(private val orderId: Int) : Command {
    override fun execute() {
        println("Registering order $orderId...")
        sleep(1500)
        println("Registering order $orderId finished!\n")

    }
}

class ApproveNewOrderCommand(private val orderId: Int) : Command {
    override fun execute() {
        println("Waiting for approval...")
        sleep(1700)
        println("Order $orderId approved!\n")
    }
}

class PrepareOrderCommand(private val orderId: Int) : Command {
    override fun execute() {
        println("Waiting for preparation...")
        sleep(3000)
        println("Order $orderId preparation process finished!\n")
    }
}

class ShipOrderCommand(private val orderId: Int) : Command {
    override fun execute() {
        println("Starting shipping process...")
        sleep(5000)
        println("Order $orderId shipped to destination.\n")
    }
}

class CloseOrderCommand(private val orderId: Int) : Command {
    override fun execute() {
        println("Order $orderId closed!")
    }
}

fun main() {
    val processor = CommandProcessor()
    val orderId = Random.nextInt(1000, 9999)
    processor
        .add(RegisterNewOrderCommand(orderId))
        .add(ApproveNewOrderCommand(orderId))
        .add(PrepareOrderCommand(orderId))
        .add(ShipOrderCommand(orderId))
        .add(CloseOrderCommand(orderId))
    processor.process()
}
