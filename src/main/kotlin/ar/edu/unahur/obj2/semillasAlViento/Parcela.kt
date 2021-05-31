package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() = if (ancho > largo) superficie() / 5 else superficie() / 3 + largo

  fun tieneComplicaciones() = plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }

///////////////
// Desacoplamiento, seria mejor crear otra funcion que verifique si puede plantar y se podria reutilizar
///////////////

  fun plantar(planta: Planta) {
    if (plantas.size == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
    }
  }

  fun esSemillera() = plantas.all { it.daSemillas() }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  fun parcelasSemilleras() = parcelas.filter { it.esSemillera() }

///////////////
// abstraccion, reusabilidad. aqui se podria reutilizar la funcion que verifica las condiciones de plantar
///////////////

  fun plantarEstrategicamente(planta: Planta) { parcelaConMasLugar().plantar(planta) }

  fun parcelaConMasLugar() = parcelas.maxBy { it.cantidadMaximaPlantas() - it.plantas.size }!!
}