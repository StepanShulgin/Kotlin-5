package ru.otus.cars

object Taz: Car {
    private val fuelSystem = FuelSystem.explosivePetrol()

    /**
     * Номерной знак
     */
    override val plates: Car.Plates
        get() = throw NotImplementedError("Номера сняты")

    /**
     * Цвет машины
     */
    override val color: String = "Ржавый"

    /**
     * Следит за машиной
     */
    override val carOutput: CarOutput = object : CarOutput {
        override fun getCurrentSpeed(): Int = 0

        override fun getFuelContents(): Int = fuelSystem.contents
    }

    override val tankMouth: FuelSystem.TankMouth
        get() = fuelSystem.mouth

    /**
     * Получить оборудование
     */
    override fun getEquipment(): String = "Крыса"

    /**
     * Руль вправо на [degrees] градусов
     */
    override fun wheelToRight(degrees: Int) {
        throw NotImplementedError("Руля нет")
    }

    /**
     * Руль влево на [degrees] градусов
     */
    override fun wheelToLeft(degrees: Int) {
        throw NotImplementedError("Руля нет")
    }

    override fun toString(): String = "Taz(color=$color, fuel=${carOutput.getFuelContents()})"
}
