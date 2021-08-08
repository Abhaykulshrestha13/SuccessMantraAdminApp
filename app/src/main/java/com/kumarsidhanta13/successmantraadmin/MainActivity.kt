package com.kumarsidhanta13.successmantraadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnMotVid:Button
    lateinit var btnBussQuotes:Button
    lateinit var btnGpQuotes: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMotVid = findViewById(R.id.btn_mot_vid)
        btnBussQuotes = findViewById(R.id.btn_buss_quotes)
        btnGpQuotes = findViewById(R.id.btn_gpq_quotes)
        btnMotVid.setOnClickListener {
            startActivity(Intent(this,VideoMotivational::class.java))
        }
        btnBussQuotes.setOnClickListener {
            startActivity(Intent(this,BusinessQuotes::class.java))
        }
        btnGpQuotes.setOnClickListener {
            startActivity(Intent(this,GreatPersonQuotes::class.java))
        }
    }
}