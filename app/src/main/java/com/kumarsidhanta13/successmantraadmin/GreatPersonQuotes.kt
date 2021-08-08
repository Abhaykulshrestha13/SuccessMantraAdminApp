package com.kumarsidhanta13.successmantraadmin

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class GreatPersonQuotes : AppCompatActivity() {
    lateinit var actionBar: ActionBar
    lateinit var btnUploadGpq: Button
    lateinit var btnPickGpq: Button
    lateinit var imageViewGpq: ImageView
    lateinit var btnEditUploadGpq: Button
    var imageUri: Uri? = null
    lateinit var etGpqQuotes: EditText
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_great_person_quotes)
        btnUploadGpq= findViewById(R.id.btn_upload_gpq)
        btnPickGpq = findViewById(R.id.btn_select_gpq)
        imageViewGpq = findViewById(R.id.img_view_gpq)
        btnEditUploadGpq = findViewById(R.id.btn_edit_upload_gpq)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Uploading Image")
        progressDialog.setCanceledOnTouchOutside(false)
        actionBar = supportActionBar!!
        actionBar.title = "Add New Great Person Quote"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        btnPickGpq.setOnClickListener {
            Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
            gpqPickGallery()
        }
        btnUploadGpq.setOnClickListener {
            if (imageUri == null) {
                Toast.makeText(this, "Pick the Image first", Toast.LENGTH_SHORT).show()
            } else {
                uploadImageToFirebase()
            }
        }
        btnEditUploadGpq.setOnClickListener {
            startActivity(Intent(this,EditGreatPersonQuotes::class.java))
        }
    }
    //    private fun videoPickDialog() {
//        val option = arrayOf("Gallery")
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Pick Video From")
//            .setItems(option){
//                dialogInterface,i->
//                if(i==0){
//
//                }
//            }
//    }
    private fun gpqPickGallery(){
        var galleryIntent: Intent = Intent()
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent,1)
//        val intent = Intent()
//        intent.type = "video/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(
//            Intent.createChooser(intent,"Choose Video"),VIDIO_PICK_GALLERY
//        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            imageUri = data.data
            setImageToVideoView()
        }
//        if(resultCode == RESULT_OK){
//            if(requestCode == VIDIO_PICK_GALLERY){
//                videoUri = data!!.data
//                 settVideoToVideoView()
//            }
//        }
        else{
            Toast.makeText(this,"Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setImageToVideoView() {
        imageViewGpq.setImageURI(imageUri)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun uploadImageToFirebase() {
        progressDialog.show()
        val timestamp = ""+System.currentTimeMillis()
        val filePathAndName = "GreatPersonQuotes/image_$timestamp"
        val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)
        val likes = 0
        storageReference.putFile(imageUri!!)
            .addOnSuccessListener {
                val uriTask =it.storage.downloadUrl
                while(!uriTask.isSuccessful);
                val downloadUri = uriTask.result
                if(uriTask.isSuccessful){
                    val hashMap = HashMap<String,Any?>()
                    hashMap["id"] = "$timestamp"
                    hashMap["timestamp"] = "$timestamp"
                    hashMap["imageUri"] = "$downloadUri"
                    hashMap["likes"] = "$likes"

                    val dbReference = FirebaseDatabase.getInstance().getReference("ImageGreatPersonQuotes")
                    dbReference.child(timestamp)
                        .setValue(hashMap)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this,"Image Uploaded", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener{
                            progressDialog.dismiss()
                            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
    }
    }