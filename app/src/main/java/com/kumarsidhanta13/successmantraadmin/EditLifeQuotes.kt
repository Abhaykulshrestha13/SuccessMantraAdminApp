package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterFriends
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterLife
import com.kumarsidhanta13.successmantraadmin.model.ModelFriends
import com.kumarsidhanta13.successmantraadmin.model.ModelLife

class EditLifeQuotes : AppCompatActivity() {
    lateinit var lifeArrayList: ArrayList<ModelLife>
    lateinit var adapterlife: MyAdapterLife
    lateinit var recyclerView: RecyclerView
    lateinit var refreshLq: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_life_quotes)

        recyclerView = findViewById(R.id.recyclerview_life)
        refreshLq  =findViewById(R.id.refresh_life)
        tvError = findViewById(R.id.tv_error_life)

        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }

    private fun loadVideoFromFirebase() {
        lifeArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageLifeQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    lifeArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelDmq = ds.getValue(ModelLife::class.java)
                        lifeArrayList.add(modelDmq!!)
                    }
                    if (lifeArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshLq.visibility = View.GONE
                    } else {
                        lifeArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adapterlife = MyAdapterLife(this@EditLifeQuotes, lifeArrayList)
                        recyclerView.adapter = adapterlife
                        refreshLq.setOnRefreshListener {
                            recyclerView.adapter = adapterlife
                            refreshLq.isRefreshing = false
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}