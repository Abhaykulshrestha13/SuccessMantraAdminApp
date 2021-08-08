package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterDaily
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterFriends
import com.kumarsidhanta13.successmantraadmin.model.ModelDaily
import com.kumarsidhanta13.successmantraadmin.model.ModelFriends

class EditFriendshipQuotes : AppCompatActivity() {
    lateinit var friendArrayList: ArrayList<ModelFriends>
    lateinit var adapterfriend: MyAdapterFriends
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_friendship_quotes)

        recyclerView = findViewById(R.id.recyclerview_fq)
        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }

    private fun loadVideoFromFirebase() {
        friendArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageFriendQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    friendArrayList.clear()
                    for(ds in snapshot.children){
                        val modelDmq = ds.getValue(ModelFriends::class.java)
                        friendArrayList.add(modelDmq!!)
                    }
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adapterfriend = MyAdapterFriends(this@EditFriendshipQuotes,friendArrayList)
                    recyclerView.adapter = adapterfriend
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}