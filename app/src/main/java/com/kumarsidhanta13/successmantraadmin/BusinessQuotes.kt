package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class BusinessQuotes : AppCompatActivity() {
    lateinit var etBussQuotes:EditText
    lateinit var btnBussUpload:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_quotes)
        btnBussUpload.setOnClickListener {

        }
    }

}