package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterVideo
import com.kumarsidhanta13.successmantraadmin.model.ModelVideo

class EditVideoActivity : AppCompatActivity() {
    lateinit var videoArrayList: ArrayList<ModelVideo>
    lateinit var adapterVideo:MyAdapterVideo
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_video)
        recyclerView = findViewById(R.id.recyclerview_vid)
        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }

    private fun loadVideoFromFirebase() {
        videoArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("Videos")
        ref.addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    videoArrayList.clear()
                    for(ds in snapshot.children){
                        val modelVideo = ds.getValue(ModelVideo::class.java)
                        videoArrayList.add(modelVideo!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adapterVideo = MyAdapterVideo(this@EditVideoActivity,videoArrayList)
                    recyclerView.adapter = adapterVideo
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}