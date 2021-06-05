package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot

class SemillasTest : DescribeSpec ({
    describe("Semillas test") {
        val sojaTransgenica1 = SojaTransgenica(2000, 2f) // Instancias alteradas para desacoplar,
        val soja1 = Soja(2009, 0.6f)                     // los dos tipos de sojas.
        val menta1 = Menta(2001, 0.5f)
        val parcela1 = Parcela(3, 3, 7)
        val parcela2 = Parcela(5, 2, 2)
        val parcela3 = Parcela(4, 4, 9)
        val parcela4 = Parcela(5, 3, 9)
        val agricultora1 = Agricultora(mutableListOf(parcela1, parcela2, parcela3))
        val agricultora2 = Agricultora(mutableListOf(parcela2, parcela4))

        describe("test de plantas") {
            describe("menta") {
                it("horas de sol que tolera") {
                    menta1.horasDeSolQueTolera().shouldBe(6)
                }
                it("es fuerte") {
                    menta1.esFuerte().shouldBeFalse()
                }
                it("da semilla") {
                    menta1.daSemillas().shouldBeTrue()
                }
            }
            describe("soja transgénica") {
                it("horas de sol que tolera") {
                    sojaTransgenica1.horasDeSolQueTolera().shouldBe(18)
                }
                it("es fuerte") {
                    sojaTransgenica1.esFuerte().shouldBeTrue()
                }
                it("da semilla") {
                    sojaTransgenica1.daSemillas().shouldBeFalse()
                }
            }
            describe("soja orgánica") {
                it("horas de sol que tolera") {
                    soja1.horasDeSolQueTolera().shouldBe(7)
                }
                it("es fuerte") {
                    soja1.esFuerte().shouldBeFalse()
                }
                it("da semilla") {
                    soja1.daSemillas().shouldBeFalse()
                }
            }
        }

        describe("test de parcela") {
            describe("una parcela") {
                it("superficie") {
                    parcela1.superficie().shouldBe(9)
                }
                it("cantidad maxima de plantas") {
                    parcela1.cantidadMaximaPlantas().shouldBe(6)
                }

            }
            describe("otra parcela") {
                it("superficie") {
                    parcela2.superficie().shouldBe(10)
                }
                it("cantidad maxima de plantas") {
                    parcela2.cantidadMaximaPlantas().shouldBe(2)
                }
            }
            describe("una parcela con sus plantas") {
                parcela1.plantar(sojaTransgenica1)
                parcela1.plantar(menta1)
                it("plantar") {
                    parcela1.plantas.shouldContain(sojaTransgenica1)
                    parcela1.plantas.shouldContain(menta1)
                }
                it("tiene Complicaciones") {
                    parcela1.tieneComplicaciones().shouldBeTrue()
                }
            }
            describe("otra parcela con sus plantas") {
                it("plantar") {
                    shouldThrowAny { parcela3.plantar(menta1) }
                    parcela3.plantas.shouldNotContain(menta1)
                }
                it("tiene Complicaciones") {
                    parcela3.tieneComplicaciones().shouldBeFalse()
                }
            }
        }

        describe("test de agricultora") {
            describe("una agricultora") {
                agricultora1.plantarEstrategicamente(sojaTransgenica1)
                it("plantarEstratégicamente") {
                    parcela3.plantas.shouldContain(sojaTransgenica1)
                }
                it("parcelasSemilleras") {
                    agricultora1.parcelasSemilleras().shouldBeEmpty()
                }
            }
            describe("Otra agricultora") {
                agricultora2.plantarEstrategicamente(menta1)
                agricultora2.plantarEstrategicamente(sojaTransgenica1)
                it("plantar Estrategicamente respetando las restricciones") {
                    parcela4.plantas.shouldNotContain(menta1)
                    parcela4.plantas.shouldContain(sojaTransgenica1)
                }
                it("parcelas semilleras") {
                    agricultora2.parcelasSemilleras().shouldBe(listOf(parcela2))
                }
            }
        }
    }
})