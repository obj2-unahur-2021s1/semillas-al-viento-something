package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillasTest : DescribeSpec ({
    describe("Semillas test") {
        val soja1 = Soja(2000, 2f, true)
        val soja2 = Soja(2009, 0.6f, false)
        val menta1 = Menta(2001, 0.5f)
        val parcela1 = Parcela(3, 3, 7)
        val parcela2 = Parcela(5, 2, 2)
        val agricultora = Agricultora(mutableListOf(parcela1, parcela2))

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
            describe("soja transgenica") {
                it("horas de sol que tolera") {
                    soja1.horasDeSolQueTolera().shouldBe(18)
                }
                it("es fuerte") {
                    soja1.esFuerte().shouldBeTrue()
                }
                it("da semilla") {
                    soja1.daSemillas().shouldBeFalse()
                }
            }
            describe("soja organica") {
                it("horas de sol que tolera") {
                    soja2.horasDeSolQueTolera().shouldBe(7)
                }
                it("es fuerte") {
                    soja2.esFuerte().shouldBeFalse()
                }
                it("da semilla") {
                    soja2.daSemillas().shouldBeFalse()
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
        }

    }
})