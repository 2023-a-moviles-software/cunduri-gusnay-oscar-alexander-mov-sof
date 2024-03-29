package com.example.movilessoftware2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringArrayExtra("nombre")
        val apellido = intent.getStringArrayExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador: Bentrenador? = intent.getParcelableExtra("entrenador")

        val boton = findViewById<Button>(R.id.btn_devolver_resupuesta)

        boton.setOnClickListener{ devolverRespuesta() }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Oscar")
        intentDevolverParametros.putExtra("edadModificada", 35)

        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}