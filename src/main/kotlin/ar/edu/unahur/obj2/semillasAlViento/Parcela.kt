package ar.edu.unahur.obj2.semillasAlViento

///////////////
// Simplicidad, la cantidad de plantas se podria calcular con la lista para no llevar un contador
///////////////
class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0

  fun superficie() = ancho * largo

///////////////
// redundancia, se podria usar la fun superficie()
///////////////
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

///////////////
// Desacoplamiento, seria mejor crear otra funcion que verifique si puede plantar y se podria reutilizar
///////////////
  fun plantar(planta: Planta) {
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

///////////////
// Simplicidad, esta funcionalidad no se pide en el enunciado
///////////////
  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

///////////////
// abstraccion, reusabilidad. aqui se podria reutilizar la funcion que verifica las condiciones de plantar
///////////////
  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
