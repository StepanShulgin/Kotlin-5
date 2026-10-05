package ru.otus.cars

object GasStation {
    fun refuel(car: Car, liters: Int): Boolean {
        val mouth = car.tankMouth
        return try {
            mouth.open()
            when (mouth) {
                is FuelSystem.PetrolMouth -> mouth.fuelPetrol(liters)
                is FuelSystem.LpgMouth -> mouth.fuelLpg(liters)
            }
            true
        } catch (_: TankExplosionException) {
            false
        } finally {
            mouth.close()
        }
    }
}
