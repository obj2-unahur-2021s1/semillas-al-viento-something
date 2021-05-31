package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    describe("Semillas test") {
        val sojaTransgenica1 = SojaTransgenica(2000, 2f) // Instancias alteradas para desacoplar,
        val soja1 = Soja(2009, 0.6f)                     // los dos tipos de sojas.
        val menta1 = Menta(2001, 0.5f)
        val parcela1 = Parcela(3, 3, 7)
        val parcela2 = Parcela(5, 2, 2)
        val parcela3 = Parcela(4, 4, 9)
        val agricultora = Agricultora(mutableListOf(parcela1, parcela2, parcela3))

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
                it("plantar") {
                    parcela1.plantar(sojaTransgenica1)
                    parcela1.plantar(menta1)
                    parcela1.plantas.shouldContain(sojaTransgenica1)
                    parcela1.plantas.shouldContain(menta1)
                }
                it("tieneComplicaciones") {
                    parcela1.tieneComplicaciones().shouldBeFalse()
                }
            }
            describe("otra parcela con sus plantas") {
                it("plantar") {
                    parcela3.plantar(menta1)
                    parcela3.plantas.shouldNotContain(menta1)
                }
                it("tieneComplicaciones") {
                    parcela3.tieneComplicaciones().shouldBeFalse() // Nunca debería haber sido true porque
                }                                                  // la menta nunca fue plantada en primer lugar.
            }
        }

        describe("test de agricultora") {
            describe("una agricultura") {
                it("parcelasSemilleras") {
                    agricultora.parcelasSemilleras().shouldContain(parcela1)
                }
                it("plantarEstratégicamente") {
                    agricultora.plantarEstrategicamente(sojaTransgenica1)
                    parcela3.plantas.shouldContain(sojaTransgenica1)
                }
            }
        }
    }
})