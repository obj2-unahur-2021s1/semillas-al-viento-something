package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() = if (ancho > largo) superficie() / 5 else superficie() / 3 + largo

  fun tieneComplicaciones() = plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }

  fun puedePlantar(planta: Planta) =
    plantas.size < this.cantidadMaximaPlantas()
    && horasSolPorDia <= planta.horasDeSolQueTolera() + 2

  fun plantar(planta: Planta) {
    if (this.puedePlantar(planta)) plantas.add(planta)
    else throw Exception("Ya no hay lugar en esta parcela o la planta se va a quemar")
  }

  fun esSemillera() = plantas.isNotEmpty() && plantas.all { it.daSemillas() }

  fun espacioLibre() = this.cantidadMaximaPlantas() - this.plantas.size
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  fun parcelasSemilleras() = parcelas.filter { it.esSemillera() }

  fun plantarEstrategicamente(planta: Planta) =
    parcelaConMasLugarPara(planta).plantar(planta)

  fun parcelaConMasLugarPara(planta : Planta) =
    this.parcelasQuePuedePlantar(planta).maxBy { it.espacioLibre() }!!

  fun parcelasQuePuedePlantar(planta: Planta) =
    parcelas.filter { it.puedePlantar(planta) }
}

