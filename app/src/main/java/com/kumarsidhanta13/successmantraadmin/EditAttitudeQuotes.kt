package com.kumarsidhanta13.successmantraadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterAttitude
import com.kumarsidhanta13.successmantraadmin.model.ModelAttitude


class EditAttitudeQuotes : AppCompatActivity() {
    lateinit var attiArrayList: ArrayList<ModelAttitude>
    lateinit var adapteratti: MyAdapterAttitude
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_attitude_quotes)
        recyclerView = findViewById(R.id.recyclerview_att)

//        val LayoutManager = LinearLayoutManager(applicationContext)
//        LayoutManager.reverseLayout = true
//        LayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = LayoutManager
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }

    private fun loadVideoFromFirebase() {
        attiArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageAttitudeQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    attiArrayList.clear()
                    for(ds in snapshot.children){
                        val modelAttitude = ds.getValue(ModelAttitude::class.java)
                        attiArrayList.add(modelAttitude!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adapteratti = MyAdapterAttitude(this@EditAttitudeQuotes,attiArrayList)
                    recyclerView.adapter = adapteratti
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}