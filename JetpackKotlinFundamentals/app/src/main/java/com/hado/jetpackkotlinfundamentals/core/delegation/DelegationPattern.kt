package com.hado.jetpackkotlinfundamentals.core.delegation

interface SoundBehavior {
    fun makeSound()
}

class ScreamBehavior(val n: String): SoundBehavior {
    override fun makeSound() {
        println("${n.uppercase()} !!!")
    }
}

class RockAndRollBehavior(val n: String): SoundBehavior {
    override fun makeSound() {
        println("I'm the King of Rock 'N' Roll: $n")
    }
}

// Tom Araya is the "singer" of Slayer
class TomAraya(n: String): SoundBehavior by ScreamBehavior(n)
class ElvisPresley(n: String): SoundBehavior by RockAndRollBehavior(n)

fun main() {
    val tomAraya = TomAraya("Thrash Metal")
    tomAraya.makeSound()
    val elvisPresley = ElvisPresley("Dancin' to the Jailhouse Rock.")
    elvisPresley.makeSound()
}