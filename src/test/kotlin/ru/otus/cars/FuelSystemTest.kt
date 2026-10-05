package ru.otus.cars

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FuelSystemTest {
    @Test
    fun `vaz 2107 is refueled with lpg`() {
        val car = Vaz2107.build(Car.Plates("А001АА", 77))

        assertTrue(GasStation.refuel(car, 20))

        assertEquals(20, car.carOutput.getFuelContents())
    }

    @Test
    fun `vaz 2108 is refueled with petrol`() {
        val car = Vaz2108.build(Car.Plates("А002АА", 77))

        assertTrue(GasStation.refuel(car, 20))

        assertEquals(20, car.carOutput.getFuelContents())
    }

    @Test
    fun `gas station safely handles taz tank explosion`() {
        assertFalse(GasStation.refuel(Taz, 20))
        assertEquals(0, Taz.carOutput.getFuelContents())
    }
}
