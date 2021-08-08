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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterGreatPerson
import com.kumarsidhanta13.successmantraadmin.model.ModelBuss
import com.kumarsidhanta13.successmantraadmin.model.ModelGreatPerson

class EditGreatPersonQuotes : AppCompatActivity() {
    lateinit var gpqArrayList: ArrayList<ModelGreatPerson>
    lateinit var adaptergpq: MyAdapterGreatPerson
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_great_person_quotes)
        recyclerView = findViewById(R.id.recyclerview_gpq)
        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }
    private fun loadVideoFromFirebase() {
        gpqArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageGreatPersonQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    gpqArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelBuss = ds.getValue(ModelGreatPerson::class.java)
                        gpqArrayList.add(modelBuss!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adaptergpq = MyAdapterGreatPerson(this@EditGreatPersonQuotes, gpqArrayList)
                    recyclerView.adapter = adaptergpq
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}