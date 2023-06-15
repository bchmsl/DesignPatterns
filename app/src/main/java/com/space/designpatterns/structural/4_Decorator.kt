package com.space.designpatterns.structural

/** We create an interface with common methods for our classes */
interface CoffeeShop {
    fun orderSmallCoffee()
    fun orderLargeCoffee()
}
/** We create classes of [SmallCoffeeShop], [BigCoffeeShop], [Starbucks] */
class SmallCoffeeShop : CoffeeShop {
    override fun orderSmallCoffee() {
        println("Received an order of Small coffee!")
    }

    override fun orderLargeCoffee() {
        println("Received an order of Large coffee!")
    }
}

/** We receive [CoffeeShop] as a parameter to extend its functionality a.k.a decorate it */
class BigCoffeeShop(coffeeShop: CoffeeShop) : CoffeeShop by coffeeShop {

    /** We use [CoffeeShop] old functionality to [orderSmallCoffee] and
        decorate it by adding extra functions (adding milk in this case) */
    fun orderSmallCoffeeWithMilk(){
        orderSmallCoffee()
        println("Adding milk to Small coffee!")
    }
}

/** Same happens with [Starbucks]. We use old [CoffeeShop] as a parameter and extend its functionality
    by adding ice to their Large coffee. */
class Starbucks(coffeeShop: CoffeeShop): CoffeeShop by coffeeShop{
    fun orderBigFrappuccino(){
        orderLargeCoffee()
        println("Added ice to Large coffee")
    }
}

fun main() {
    val smallCoffeeShop = SmallCoffeeShop()
    val bigCoffeeShop = BigCoffeeShop(smallCoffeeShop)
    val starbucks = Starbucks(smallCoffeeShop)

    starbucks.orderLargeCoffee()
    starbucks.orderBigFrappuccino()
    println("---------\n")
    bigCoffeeShop.orderSmallCoffeeWithMilk()
}
