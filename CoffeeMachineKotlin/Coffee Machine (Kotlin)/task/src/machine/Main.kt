package machine

fun main() {
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.startCM()
}

class CoffeeMachine {
    // CM represents Coffee Machine
    private var moneyInCM = 550
    private var waterInCM = 400
    private var milkInCM = 540
    private var beansInCM = 120
    private var usableCupsInCM = 9


    fun startCM() {

        while (true) {

            println("\nWrite action (buy, fill, take, remaining, exit): ")
            val actionChoice = readln()

            when (actionChoice) {
                "buy" -> buy()
                "fill" -> fillMachine()
                "take" -> takeMoney()
                "remaining" -> showCMContents()
                "exit" -> break
            }
        }
    }

    private fun showCMContents() {
        println(
            "\nThe coffee machine has:\n" +
            "$waterInCM ml of water\n" +
            "$milkInCM ml of milk\n" +
            "$beansInCM g of coffee beans\n" +
            "$usableCupsInCM disposable cups\n" +
            "\$$moneyInCM of money"
        )
    }

    private fun buy() {

        println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        val coffeeChoice = readln()

        when (coffeeChoice) {
            "1" -> buyEspresso()
            "2" -> buyLatte()
            "3" -> buyCappuccino()
            "back" -> return
        }

    }

    private fun buyEspresso() {

        checkIngredients(250, 0, 16)
        moneyInCM += 4

    }

    private fun buyLatte() {

        checkIngredients(350, 75, 20)
        moneyInCM += 7

    }

    private fun buyCappuccino() {

        checkIngredients(200, 100, 12)
        moneyInCM += 6

    }

    private fun fillMachine() {

        println("Write how many ml of water you want to add: ")
        waterInCM += readln().toInt()
        println("Write how many ml of milk you want to add: ")
        milkInCM += readln().toInt()
        println("Write how many grams of coffee beans you want to add: ")
        beansInCM += readln().toInt()
        println("Write how many disposable cups you want to add: ")
        usableCupsInCM += readln().toInt()

    }

    private fun takeMoney() {

        println("I gave you \$$moneyInCM\n")
        moneyInCM = 0

    }

    private fun checkIngredients(water: Int, milk: Int, beans: Int) {
        when {
            waterInCM >= water  &&
            milkInCM >= milk &&
            beansInCM >= beans &&
            usableCupsInCM >= 1  -> {
                println("I have enough resources, making you a coffee!")
                waterInCM -= water
                milkInCM -= milk
                beansInCM -= beans
                usableCupsInCM -= 1
            }

            waterInCM < water -> println("Sorry, not enough water!")
            milkInCM < milk -> println("Sorry, not enough milk!")
            beansInCM < beans -> println("Sorry, not enough coffee beans!")
            usableCupsInCM == 0 -> println("Sorry, not enough disposable cups!")

        }
    }

}