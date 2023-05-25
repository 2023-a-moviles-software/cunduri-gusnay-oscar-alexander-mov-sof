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

abstract class Numeros (
    //Ejemplo
    //uno: Int (Parametro sin modificador de acceso)
    //private var uno: Int, //Ptopiedad publica clase numeros.uno
    //var uno: Int, propiedad de la clase (por defecto es public)
    //public var uno: Int,
    protected val numeroUno: Int,//Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int){//Propiedad de la clase protected numero.numeroDos


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



