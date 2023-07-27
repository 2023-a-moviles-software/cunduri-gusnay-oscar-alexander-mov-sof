package com.example.movilessoftware2023a

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class GGoogleMaps : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    private var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        solicitarPermisos()
        iniciarMapa()


        val btnIrCarolina = findViewById<Button>(R.id.btn_ir_carolina)

        btnIrCarolina.setOnClickListener{
            irCarolina()
        }
    }


    private fun irCarolina() {
        val carolina = LatLng(
            -0.1825684318486696,
            -78.48447277600916
        )
        val zoom = 17f
        moverCamaraZoom(carolina, zoom)
    }

    private fun iniciarMapa() {
        val framentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        framentoMapa.getMapAsync { googleMap ->
            with(googleMap) {
                mapa = googleMap
                establecerConfiguracionMapa()
                marcadorQuicentro()
                escucharListeners()
                anadirPoliLinea()
                anadirPoligono()
            }
        }

    }

    private fun añadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    private fun moverCamaraZoom(latLng: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )
    }

    private fun anadirPoliLinea() {
        with(mapa) {
            val poliLineaUno = mapa
                .addPolyline(
                    PolylineOptions().clickable(true).add(
                        LatLng(
                            -0.1759187040647396,
                            -78.48506472421384
                        ),
                        LatLng(
                            -0.17632468492901104,
                            -78.48265589308046
                        ),
                        LatLng(
                            -0.17746143130181483,
                            -78.4770533307815
                        )
                    )
                )
            poliLineaUno.tag = "PoliLinea-1"
        }
    }

    private fun anadirPoligono() {
        with(mapa) {
            val poligonoUno = mapa
                .addPolygon(
                    PolygonOptions().clickable(true).add(
                        LatLng(
                            -0.171546902239471,
                            -78.48344981495214
                        ),
                        LatLng(
                            -0.17968981486125768,
                            -78.48269198043828
                        ),
                        LatLng(
                            -0.1771095812414777,
                            -78.48142892291516
                        )

                    )
                )
            poligonoUno.fillColor = -0xc771c4
            poligonoUno.tag = "Poligono-2"
        }
    }

    private fun escucharListeners() {
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener $it")
            it.tag
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener $it")
            it.tag
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener $it")
            it.tag
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener $it")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }

    private fun marcadorQuicentro() {
        val zoom = 17f
        val quicentro = LatLng(
            -0.17556708490271092, -78.48014901143776
        )
        val titulo = "Quicentro"
        val markQuicentro = añadirMarcador(quicentro, titulo)
        moverCamaraZoom(quicentro)
    }

    private fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    private fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCores = android.Manifest.permission.ACCESS_COARSE_LOCATION

        val permisosFineLocation = ContextCompat.checkSelfPermission(
            contexto,
            nombrePermisoFine
        )

        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermisos) permisos = true
        else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    nombrePermisoFine, nombrePermisoCores
                ),
                1
            )
        }
    }
}
