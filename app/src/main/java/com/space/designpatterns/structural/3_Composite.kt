package com.space.designpatterns.structural

/** We create class [Hardware] for each component of [Composite] */
open class Hardware(val name: String, open val price: Float)

/** We create class [Composite], that includes its components and counts its sum price */
open class Composite(name: String) : Hardware(name, 0f) {
    private val hardwareList = mutableListOf<Hardware>()

    override val price: Float
        get() = sumPrice()

    private fun sumPrice(): Float {
        var priceSum = 0f
        hardwareList.forEach {
            priceSum += it.price
        }
        return priceSum
    }

    fun addHardware(item: Hardware) = apply { hardwareList.add(item) }
}

/** We create each component. Some of them includes other components, so they are [Composite]s*/
class Computer(computerName: String) : Composite(computerName)
class Processor : Hardware("Processor", 1500f)
class HardDrive : Hardware("Hard Drive", 500f)
class GPU : Hardware("GPU", 1250f)
class Memory : Composite("Memory")
class RAM : Hardware("Random Access Memory", 300f)
class ROM : Hardware("Read Only Memory", 200f)


fun main() {
    /** We create [Memory] composite, that includes components: [RAM] and [ROM] */
    val memory = Memory()
        .addHardware(RAM())
        .addHardware(ROM())

    /**  We create [Computer] composite, that includes components:
     * * [Memory] (which also includes other components),
     * * [Processor],
     * * [HardDrive],
     * * [GPU]  */
    val computer = Computer("Bachi's Macbook Pro")
        .addHardware(memory)
        .addHardware(Processor())
        .addHardware(HardDrive())
        .addHardware(GPU())

    /** Thanks to our [Composite]'s function [Composite.sumPrice],
    we can count computer's price by accessing its property [Computer.price] */
    println("Price of ${computer.name} is $${computer.price}")

}
