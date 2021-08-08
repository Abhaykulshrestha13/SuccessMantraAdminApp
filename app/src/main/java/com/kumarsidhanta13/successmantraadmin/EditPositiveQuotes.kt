package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterMoney
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterPositive
import com.kumarsidhanta13.successmantraadmin.model.ModelMoney
import com.kumarsidhanta13.successmantraadmin.model.ModelPositive

class EditPositiveQuotes : AppCompatActivity() {

    lateinit var positiveArrayList: ArrayList<ModelPositive>
    lateinit var adapterpositive: MyAdapterPositive
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_positive_quotes)
        recyclerView = findViewById(R.id.recyclerview_positive)
        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }
    private fun loadVideoFromFirebase() {
        positiveArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImagePositiveQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    positiveArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelBuss = ds.getValue(ModelPositive::class.java)
                        positiveArrayList.add(modelBuss!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adapterpositive = MyAdapterPositive(this@EditPositiveQuotes, positiveArrayList)
                    recyclerView.adapter = adapterpositive
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}