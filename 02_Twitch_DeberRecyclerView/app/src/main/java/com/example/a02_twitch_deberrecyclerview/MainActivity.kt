package com.example.a02_twitch_deberrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore.Video
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a02_twitch_deberrecyclerview.adapter.CategoriaAdapter
import com.example.a02_twitch_deberrecyclerview.adapter.VideoAdapter
import com.example.a02_twitch_deberrecyclerview.providers.VideoProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivIcono = findViewById<ImageView>(R.id.iv_icono_twitch)
        val twitchIconURL = "https://i0.wp.com/senpai.com.mx/wp-content/uploads/2020/03/Cuarentena-por-coranivurus-aumenta-el-consumo-de-Twitch-en-el-mundo.jpg?fit=1280%2C720&ssl=1"
        Glide.with(ivIcono.context).load(twitchIconURL).into(ivIcono)

        Handler().postDelayed({
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}