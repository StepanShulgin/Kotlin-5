package ru.otus.cars

class TankExplosionException : IllegalStateException("The tank exploded")

class FuelSystem private constructor(
    private val tank: Tank,
    val mouth: TankMouth
) {
    val contents: Int
        get() = tank.contents

    sealed interface TankMouth {
        fun open()
        fun close()
    }

    class PetrolMouth internal constructor(private val tank: Tank) : TankMouth {
        private var isOpen = false

        override fun open() {
            isOpen = true
        }

        override fun close() {
            isOpen = false
        }

        fun fuelPetrol(liters: Int) {
            check(isOpen) { "Tank mouth must be open" }
            tank.receiveFuel(liters)
        }
    }

    class LpgMouth internal constructor(private val tank: Tank) : TankMouth {
        private var isOpen = false

        override fun open() {
            isOpen = true
        }

        override fun close() {
            isOpen = false
        }

        fun fuelLpg(liters: Int) {
            check(isOpen) { "Tank mouth must be open" }
            tank.receiveFuel(liters)
        }
    }

    internal interface Tank {
        val contents: Int
        fun receiveFuel(liters: Int)
    }

    private class StandardTank : Tank {
        override var contents: Int = 0
            private set

        override fun receiveFuel(liters: Int) {
            require(liters > 0) { "Fuel amount must be positive" }
            contents += liters
        }
    }

    private class ExplosiveTank : Tank {
        override val contents: Int = 0

        override fun receiveFuel(liters: Int): Nothing = throw TankExplosionException()
    }

    companion object {
        fun petrol(): FuelSystem {
            val tank = StandardTank()
            return FuelSystem(tank, PetrolMouth(tank))
        }

        fun lpg(): FuelSystem {
            val tank = StandardTank()
            return FuelSystem(tank, LpgMouth(tank))
        }

        fun explosivePetrol(): FuelSystem {
            val tank = ExplosiveTank()
            return FuelSystem(tank, PetrolMouth(tank))
        }
    }
}
