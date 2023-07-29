package com.example.a02_twitch_deberrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a02_twitch_deberrecyclerview.adapter.CategoriaAdapter
import com.example.a02_twitch_deberrecyclerview.adapter.VideoAdapter
import com.example.a02_twitch_deberrecyclerview.providers.VideoProvider

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val massageIconURL = "https://cdn.icon-icons.com/icons2/3390/PNG/512/twitch_icon_213275.png"
        val notificationIconURL = "https://img.icons8.com/?size=512&id=H1UeFcv5ogFU&format=png"

        val iconoNotificacion = findViewById<ImageView>(R.id.iv_icono_notificacion)
        val iconoMensaje = findViewById<ImageView>(R.id.iv_icono_mensajes)


        Glide.with(iconoNotificacion.context).load(notificationIconURL).into(iconoNotificacion)
        Glide.with(iconoMensaje.context).load(massageIconURL).into(iconoMensaje)

        initRecyclerView()
        initRecyclerViewCategorias()
    }

    private fun initRecyclerView(){
        val rv = findViewById<RecyclerView>(R.id.rv_canales_siguiendo)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = VideoAdapter(VideoProvider.listaVideosSiguiendo)
    }

    private fun initRecyclerViewCategorias(){
        val rvc = findViewById<RecyclerView>(R.id.rv_categorias)
        rvc.layoutManager = GridLayoutManager(this, 4)
        rvc.adapter = CategoriaAdapter(VideoProvider.listaCategoriasSiguiendo)
    }
}