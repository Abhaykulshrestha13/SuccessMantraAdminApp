package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterBuss
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterVideo
import com.kumarsidhanta13.successmantraadmin.model.ModelBuss
import com.kumarsidhanta13.successmantraadmin.model.ModelVideo

class EditBussinessActivity : AppCompatActivity() {
    lateinit var bussArrayList: ArrayList<ModelBuss>
    lateinit var adapterbuss: MyAdapterBuss
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bussiness)
        recyclerView = findViewById(R.id.recyclerview_buss)
        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }

    private fun loadVideoFromFirebase() {
        bussArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageBuss")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    bussArrayList.clear()
                    for(ds in snapshot.children){
                        val modelBuss = ds.getValue(ModelBuss::class.java)
                        bussArrayList.add(modelBuss!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adapterbuss = MyAdapterBuss(this@EditBussinessActivity,bussArrayList)
                    recyclerView.adapter = adapterbuss
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}