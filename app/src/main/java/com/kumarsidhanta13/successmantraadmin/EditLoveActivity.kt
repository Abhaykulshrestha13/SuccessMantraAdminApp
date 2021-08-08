package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterGreatPerson
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterLove
import com.kumarsidhanta13.successmantraadmin.model.ModelGreatPerson
import com.kumarsidhanta13.successmantraadmin.model.ModelLove

class EditLoveActivity : AppCompatActivity() {
    lateinit var lqArrayList: ArrayList<ModelLove>
    lateinit var adapterlq: MyAdapterLove
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_love)
        recyclerView = findViewById(R.id.recyclerview_lq)
        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }
    private fun loadVideoFromFirebase() {
        lqArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageLoveQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    lqArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelBuss = ds.getValue(ModelLove::class.java)
                        lqArrayList.add(modelBuss!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adapterlq = MyAdapterLove(this@EditLoveActivity, lqArrayList)
                    recyclerView.adapter = adapterlq
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}