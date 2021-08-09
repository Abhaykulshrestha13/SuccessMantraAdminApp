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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterGreatPerson
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterLove
import com.kumarsidhanta13.successmantraadmin.model.ModelGreatPerson
import com.kumarsidhanta13.successmantraadmin.model.ModelLove

class EditLoveActivity : AppCompatActivity() {
    lateinit var lqArrayList: ArrayList<ModelLove>
    lateinit var adapterlq: MyAdapterLove
    lateinit var recyclerView: RecyclerView
    lateinit var refreshLq: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_love)
        recyclerView = findViewById(R.id.recyclerview_lq)
        var linearLayoutManager = LinearLayoutManager(this)
        refreshLq  =findViewById(R.id.refresh_love)
        tvError = findViewById(R.id.tv_error_love)

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
                    if (lqArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshLq.visibility = View.GONE
                    } else {
                        lqArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adapterlq = MyAdapterLove(this@EditLoveActivity, lqArrayList)
                        recyclerView.adapter = adapterlq
                        refreshLq.setOnRefreshListener {
                            recyclerView.adapter = adapterlq
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