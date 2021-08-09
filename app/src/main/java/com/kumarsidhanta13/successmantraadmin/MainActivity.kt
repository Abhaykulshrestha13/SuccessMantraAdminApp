package com.kumarsidhanta13.successmantraadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnMotVid:Button
    lateinit var btnBussQuotes:Button
    lateinit var btnGpQuotes: Button
    lateinit var btnLQuotes: Button
    lateinit var btnSq:Button
    lateinit var btnAtti:Button
    lateinit var btnDQuotes:Button
    lateinit var btnFriend:Button
    lateinit var btnLifeQuotes: Button
    lateinit var btnMoneyQuotes: Button
    lateinit var btnPositiveQuotes: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMotVid = findViewById(R.id.btn_mot_vid)
        btnBussQuotes = findViewById(R.id.btn_buss_quotes)
        btnGpQuotes = findViewById(R.id.btn_gpq_quotes)
        btnLQuotes = findViewById(R.id.btn_lq_quotes)
        btnAtti = findViewById(R.id.btn_atti_quotes)
        btnDQuotes = findViewById(R.id.btn_dm_quotes)
        btnFriend = findViewById(R.id.btn_friend_quotes)
        btnLifeQuotes = findViewById(R.id.btn_life_quotes)
        btnMoneyQuotes = findViewById(R.id.btn_money_quotes)
        btnPositiveQuotes = findViewById(R.id.btn_positive_quotes)
        btnSq = findViewById(R.id.btn_sq_quotes)
        btnMotVid.setOnClickListener {
            startActivity(Intent(this,VideoMotivational::class.java))
        }
        btnBussQuotes.setOnClickListener {
            startActivity(Intent(this,BusinessQuotes::class.java))
        }
        btnGpQuotes.setOnClickListener {
            startActivity(Intent(this,GreatPersonQuotes::class.java))
        }
        btnLQuotes.setOnClickListener {
            startActivity(Intent(this,LoveQuotes::class.java))
        }
        btnAtti.setOnClickListener {
            startActivity(Intent(this,AttitudeQuotes::class.java))
        }
        btnDQuotes.setOnClickListener {
            startActivity(Intent(this,DialyMotivationQuotes::class.java))
        }
        btnFriend.setOnClickListener {
            startActivity(Intent(this,FriendshipQuotes::class.java))
        }
        btnLifeQuotes.setOnClickListener {
            startActivity(Intent(this,LifeQuotes::class.java))
        }
        btnMoneyQuotes.setOnClickListener {
            startActivity(Intent(this,MoneyQuotes::class.java))
        }
        btnPositiveQuotes.setOnClickListener {
            startActivity(Intent(this,PositiveQuotes::class.java))
        }
        btnSq.setOnClickListener {
            startActivity(Intent(this,SuccessQuotes::class.java))
        }
    }
}