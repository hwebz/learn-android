package com.hado.kotlinfundamentals

import java.io.IOException
import java.lang.Exception

fun main() {
    println("Hello, world!")
    // Hello, world!

    // Functions
    calculateCatAge(age = 15)
    println("Add: 3 + 5 = ${add(3, 5)}")
    name("James")

    enhancedMessage("Test Message") {
        print(it)
        add(5, 10)
    }

    handlingCollections()

    // OOP
    val car = Car("Black", "Spade")
    car.color = "Blue"
    car.model = "mDF"

    val secondCar = Car(color = "Purple", model = "XXLOIMM")
    secondCar.speed(minSpeed = 100, maxSpeed = 199)
    secondCar.drive()

    println("Car color: ${car.color} model: ${car.model}")
    println("Car color: ${secondCar.color} model: ${secondCar.model}")

    val truck = Truck(color = "Magenta", model = "F16")
    truck.speed(20, 50)
    truck.drive()

    // Interface
    val button = Button(label = "Demo Label")
    button.onClick(message = "This is a button")

    val superMario = Character(name = "Super Mario")
    superMario.onClick(message = "This is an actor")

    // Extension Functions
    println("Hello Jenny ".append("Friend"))
    println("\"Test\" that has first and last chars removed: ${"Test".removeFirstAndLastChars()}")

    // Data class
    val person = Person(
        name = "Joe",
        lastName = "Ball",
        age = 23
    )
    println(person)
    val ruti = Person(
        name = "Ruti",
        lastName = "Machava",
        age = 54
    )
    val john = Person(
        name = "John",
        lastName = "Doe",
        age = 34
    )
    val listOfPeople = listOf<Person>(
        person,
        ruti,
        john
    )
    listOfPeople.forEach { person ->
        println(person)
    }

    // Generics
    val listOfItems = listOf("Rafael", "Gina", "George", "James")
    val listOfNumbers = listOf(23, 45, 45)
    val finder = Finder<String>(listOfItems)
    val finder2 = Finder<Int>(listOfNumbers)
    val finder3 = Finder(listOfPeople)
    finder.findItem("George") {
        println("Found item: $it")
    }
    finder2.findItem(23) {
        println("Found item: $it")
    }
    finder3.findItem(ruti) {
        println("Found item: $it")
    }

    // Enums & State
    val input = Result.ERROR
    getResult(result = input)
    getResult(result = Result.SUCCESS)

    Repository.startFetch()
    getResult(result = Repository.getCurrentState())
    Repository.finishedFetch()
    getResult(result = Repository.getCurrentState())
    Repository.error()
    getResult(result = Repository.getCurrentState())

    SecondRepository.startFetch()
    getSecondResult(result = SecondRepository.getCurrentState())
    SecondRepository.finishedFetch()
    getSecondResult(result = SecondRepository.getCurrentState())
    SecondRepository.error()
    getSecondResult(result = SecondRepository.getCurrentState())
    SecondRepository.customFailure()
    getSecondResult(result = SecondRepository.getCurrentState())
    SecondRepository.anotherCustomFailure()
    getSecondResult(result = SecondRepository.getCurrentState())
}

fun calculateCatAge(age: Int) {
    val catAge = age * 7
    print("This cat is $catAge years old")
}

val add: (Int, Int) -> Int = { a, b -> a + b}
val name: (String) -> Unit = { name -> print(name)}

// Trailing Lambda
fun enhancedMessage(message: String, funAsParameter: (String) -> Int) {
    println("$message ${funAsParameter("Hey")}")
}

fun handlingCollections() {
    // List
    val myListOfNames = listOf("James", "Paul", "Refael", "Gina")
    val myMutableList = mutableListOf(12, 34, 45 ,123)

    myMutableList.add(214)
    myMutableList.removeAt(0)
    println(myMutableList)
    println("Size: ${myMutableList.size}")

    myListOfNames.forEach {
        println("Names: $it")
    }
    for (item in myListOfNames) {
        println("Names: $item")
    }

    val myNewList = mutableListOf<String>()
    myNewList.add("hey")
    myNewList.add("There")
    for (i in 1..10) {
        myNewList.add(i, "Hey $i")
    }
    println(myNewList)

    // Empty list
    val empty = emptyList<String>()
    val emptySet = emptySet<Int>()
    val emptyMap = emptyMap<String, Int>()

    // List Filtering
    val found = myListOfNames.filter {
        it.startsWith("r", ignoreCase = true) && it.endsWith('L')
    }
    println(found)

    // Set
    val mySet = setOf("US", "MZ", "AU")
    println(mySet)
    val mutableSet = mutableSetOf(1, 2, 3, 4, 5)
    mutableSet.add(2)
    println(mutableSet)

    // Map
    val secretMap = mapOf(
        "Up" to 1,
        "Down" to 2,
        "Left" to 3,
        "Right" to 4
    )
    println(secretMap)
    println(secretMap.keys)
    println(secretMap.values)

    if ("Up" in secretMap) println("Yes up is in!")
    if (4 in secretMap.values) println("Yes 4 is in!")

    val mutableSecretMap = mutableMapOf(
        "One" to 1,
        "Two" to 2,
        "Three" to 3
    )

    mutableSecretMap["Four"] = 4
    println(mutableSecretMap)
}

// OOP
//class Car(var color: String = "Blue", var model: String = "xmdle") {
open class Car(var color: String = "Yellow", var model: String = "KLMM") {
//    val color: String = "Red"
//    val model: String = "XMD"

//    var color: String = color
//    var model: String = model

    init {
//        color = "Yellow"
//        model = "KLMM"

        if (color == "Green") {
            println("Yay, Green")
        } else {
            println("Hmm, $color not Green?")
        }
    }

    open fun speed(minSpeed: Int, maxSpeed: Int) {
        println("Min speed is: $minSpeed, max speed is: $maxSpeed")
    }

    open fun drive() {
        println("Driving...")
    }
}

class Truck(color: String, model: String) : Car(color, model) {
    override fun speed(minSpeed: Int, maxSpeed: Int) {
        println("Truck min speed is: $minSpeed, max speed is $maxSpeed")
    }

    override fun drive() {
        println("Driving...truck!")
    }
}

// Interface
class Button (val label: String): ClickEvent {
    override fun onClick(message: String) {
        println("Button $label clicked: message = $message")
    }
}

class Character(val name: String): ClickEvent {
    override fun onClick(message: String) {
        println("Character $name clicked: message = $message")
    }

}
interface ClickEvent {
    fun onClick(message: String)
}

// Extension Functions
fun String.append(toAppend: String): String = this.plus(toAppend)

fun String.removeFirstAndLastChars(): String {
    return this.substring(1, this.length - 1)
}

// Data class
data class Person(val name: String, val lastName: String, val age: Int)

// Generics
class Finder<T>(private val list: List<T>) {
    fun findItem(element: T, foundItem: (element: T?) -> Unit) {
        val itemFoundList = list.filter {
            it == element
        }

        if (itemFoundList.isNullOrEmpty()) foundItem(null) else foundItem(itemFoundList.first())
    }
}

// Enums & State
enum class Result {
    LOADING,
    SUCCESS,
    FAILURE,
    ERROR,
    IDLE
}
fun getResult(result: Result) {
    return when(result) {
        Result.LOADING -> println("Loading!")
        Result.SUCCESS -> println("Success!")
        Result.FAILURE -> println("Failure!")
        Result.ERROR -> println("Error!")
        Result.IDLE -> println("Idle!")
    }
}

object Repository {
    private var loadState: Result = Result.FAILURE
    private var dataFetched: String? = null
    fun startFetch() {
        loadState = Result.LOADING
        dataFetched = "data"
    }

    fun finishedFetch() {
        loadState = Result.SUCCESS
        dataFetched = null
    }

    fun error() {
        loadState = Result.ERROR
    }

    fun getCurrentState(): Result {
        return loadState
    }
}

// Another version of Enums & States
abstract class SecondResult
data class Error(val exception: Exception?): SecondResult()
object Loading: SecondResult()
object NotLoading: SecondResult()
data class Success(val dataFetched: String?): SecondResult()

fun getSecondResult(result: SecondResult) {
    return when(result) {
        is Error -> {
            println(result.exception.toString())
        }
        is Success -> {
            println(result.dataFetched?: "Ensure you start the fetch function first")
        }
        is Loading -> {
            println("Loading...")
        }
        is NotLoading -> {
            println("Idle")
        }
        is Failure.CustomFailed -> {
            println(result.customFailure.toString())
        }
        is Failure.AnotherCustomFailed -> {
            println(result.anotherCustomFailure.toString())
        }
        else -> {
            println("N/A")
        }
    }
}

object SecondRepository {
    private var loadState: SecondResult = NotLoading
    private var dataFetched: String? = null
    fun startFetch() {
        loadState = Loading
        dataFetched = "data"
    }

    fun finishedFetch() {
        loadState = Success(dataFetched)
        dataFetched = null
    }

    fun error() {
        loadState = Error(exception = Exception("Exception"))
    }

    fun getCurrentState(): SecondResult {
        return loadState
    }

    fun customFailure() {
        loadState = Failure.CustomFailed(customFailure = IOException("Some exception"))
    }

    fun anotherCustomFailure() {
        loadState = Failure.AnotherCustomFailed(anotherCustomFailure = java.lang.NullPointerException("Some null pointer exception"))
    }
}

// Sealed Classes
sealed class Failure: SecondResult() {
    data class CustomFailed(val customFailure: IOException): Failure()
    data class AnotherCustomFailed(val anotherCustomFailure: NullPointerException): Failure()
}