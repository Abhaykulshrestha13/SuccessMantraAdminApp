package com.kumarsidhanta13.successmantraadmin

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class VideoMotivational : AppCompatActivity() {
    lateinit var actionBar:ActionBar
    val VIDIO_PICK_GALLERY = 100
    lateinit var btnUploadVid:Button
    lateinit var btnPickVid:Button
    lateinit var videoViewMot:VideoView
    lateinit var etVideo:EditText
    lateinit var btnEditUploadVideo:Button
    var videoUri: Uri? = null
    var title:String = ""
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_motivational)

        btnUploadVid = findViewById(R.id.btn_upload_vid)
        btnPickVid = findViewById(R.id.btn_select_video)
        videoViewMot = findViewById(R.id.vid_view_video)
        etVideo = findViewById(R.id.et_video)
        btnEditUploadVideo = findViewById(R.id.btn_edit_upload_vid)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Uploading Video")
        progressDialog.setCanceledOnTouchOutside(false)
        actionBar = supportActionBar!!
        actionBar.title = "Add New Video"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        btnPickVid.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_SHORT).show()
            videoPickGallery()
        }
        btnUploadVid.setOnClickListener {
            title = etVideo.text.toString().trim()
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show()
            } else if (videoUri == null) {
                Toast.makeText(this, "Pick the video first", Toast.LENGTH_SHORT).show()
            } else {
                uploadVideoToFirebase()
            }
        }
        btnEditUploadVideo.setOnClickListener {
            startActivity(Intent(this,EditVideoActivity::class.java))
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
    private fun videoPickGallery(){
        var galleryIntent: Intent = Intent()
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        galleryIntent.type = "video/*"
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
            videoUri = data.data
            settVideoToVideoView()
        }
//        if(resultCode == RESULT_OK){
//            if(requestCode == VIDIO_PICK_GALLERY){
//                videoUri = data!!.data
//                 settVideoToVideoView()
//            }
//        }
        else{
            Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
        }
    }

    private fun settVideoToVideoView() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoViewMot)
        videoViewMot.setMediaController(mediaController)
        videoViewMot.setVideoURI(videoUri)
        videoViewMot.requestFocus()
        videoViewMot.setOnPreparedListener {
            videoViewMot.start()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
        private fun uploadVideoToFirebase() {
            progressDialog.show()
            val timestamp = ""+System.currentTimeMillis()
            val filePathAndName = "Video/video_$timestamp"
            val storageReference = FirebaseStorage.getInstance().getReference(filePathAndName)
            val likes = 0
            storageReference.putFile(videoUri!!)
                .addOnSuccessListener {
                    val uriTask =it.storage.downloadUrl
                    while(!uriTask.isSuccessful);
                    val downloadUri = uriTask.result
                    if(uriTask.isSuccessful){
                        val hashMap = HashMap<String,Any?>()
                        hashMap["id"] = "$timestamp"
                        hashMap["title"] = "$title"
                        hashMap["timestamp"] = "$timestamp"
                        hashMap["videoUri"] = "$downloadUri"
                        hashMap["likes"] = "$likes"

                        val dbReference = FirebaseDatabase.getInstance().getReference("Videos")
                        dbReference.child(timestamp)
                            .setValue(hashMap)
                            .addOnSuccessListener {
                                progressDialog.dismiss()
                                Toast.makeText(this,"Video Uploaded",Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener{
                                progressDialog.dismiss()
                                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener{

            }
        }
}


