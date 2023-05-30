import java.util.ArrayList
import java.util.Date

fun main(args: Array<String>) {

    //Variable inmultable, no se reasigna
    val inmutable: String = "Oscar"

    //Mutables, si se puede reasignar
    var mutable: String = "Oscar1"
    mutable = "pepe"

    val numeroInm: Int = 152
    var numeroMut: Int = 123

    //DUCK TYPING
    var ejemploVariable = "Oscar Cunduri"
    val edadEjemplo: Int = 23
    ejemploVariable.trim()

    //VARIABLES PRIMITIVAS
    val nombreProfesor: String = "Vicente Eguez"
    val sueldo: Int = 12
    val estado: Boolean = true
    val num: Double = 1.2
    val fecha: Date = Date()


    //CONDICIONALES

    val estadoCivilWhen = "C"

    when (estadoCivilWhen) {
        ("C") -> {
            print("CASADO")
        }

        "S" -> {
            print("SOLTERIN")
        }

        else -> {
            print("NPI")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "si" else "no"


    calcSalary(10.00)
    calcSalary(bonoEspecial = 12.00, sueldo = 52.00)

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)


    //ARREGLOS

    //1. Arreglo estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)

    //2. Arreglo dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    //OPERADORES
    //Sirve para iterar

    val respuestaForEach: Unit = arregloDinamico.forEach { valor: Int -> println("Valor: ${valor}") }

    arregloDinamico.forEach { println("Valor actual ${it}") } //It es el iterador, o elemento iterado

    arregloEstatico.forEachIndexed { indice: Int, valor: Int -> println("Valor actual: ${valor} en el indice ${indice}") }

    //MAP MUTA el arreglo, cambia el arreglo. Crea un nuevo arreglo con valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map { valor: Int -> return@map valor.toDouble()+ 100.00 }
    print(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }


    //FILTER, filtrar un arreglo
    //Devuelve una expresion true o false o un nuevo arreglo filtrado

    val respuestFilter: List<Int> = arregloDinamico.filter { valor: Int ->
        val mayoresACinco: Boolean = valor > 5
        return@filter mayoresACinco
    }

    val respuestFilterDos = arregloDinamico.filter { it <= 5 }

    print(respuestFilter)
    print(respuestFilterDos)

    //OR AND
    //OR -> ANY(Alguno cumple?)
    //AND -> ALL(Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico.any{ valor: Int -> return@any (valor > 5)}

    print(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico.all { valor: Int -> return@all (valor > 5) }

    print(respuestaAll)

    
    //REDUCE, VALOR ACUMULADO
    val respuestaReduce: Int = arregloDinamico.reduce{ acumulado: Int, valor: Int -> return@reduce (acumulado + valor)}

    print(respuestaReduce)

}

//FUNCIONES
//Sueldo requerido, tasa opcional ya que tiene valor por defecto y bonoEspecial evita el null pointer exception
fun calcSalary(sueldo: Double, tasa: Double = 12.00, bonoEspecial: Double? = null): Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

//void -> unit
fun printName(name: String): Unit {
    print(name)

    //Template como JS
    print("El nombre es ${name}")
}


abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(uno: Int, dos: Int) {
        this.numeroUno = uno
        this.numeroDos = dos
        print("Inicializando valores")
    }
}

abstract class Numeros(
    //Ejemplo
    //uno: Int (Parametro sin modificador de acceso)
    //private var uno: Int, //Ptopiedad publica clase numeros.uno
    //var uno: Int, propiedad de la clase (por defecto es public)
    //public var uno: Int,
    protected val numeroUno: Int,//Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int
) {//Propiedad de la clase protected numero.numeroDos


    //var cedula: String = "" (public es por defecto)
    //private valorCalculado: Int = //private
    init {
        this.numeroUno //this es opcional
        this.numeroDos
        numeroUno //sin this funciona igual
        numeroDos
        print("Inicializando valores")
    }
}

class Suma(unoParametro: Int, dosParametro: Int) : Numeros(unoParametro, dosParametro) {//extender y mandar parametros
init {
    this.numeroUno
    this.numeroDos
}

    //segundo constructor
    constructor(uno: Int?, dos: Int)
            : this(if (uno == null) 0 else uno, dos)

    constructor(uno: Int, dos: Int?)
            : this(uno, if (dos == null) 0 else dos)

    constructor(uno: Int?, dos: Int?)
            : this((if (uno == null) 0 else uno), (if (dos == null) 0 else dos))

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistoria(total)
        return total
    }

    companion object {

        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistoria(valorNuevaSuma: Int) {
            historialSumas.add(valorNuevaSuma)
        }
    }

}



