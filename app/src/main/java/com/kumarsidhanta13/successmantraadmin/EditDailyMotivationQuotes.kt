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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterBuss
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterDaily
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterGreatPerson
import com.kumarsidhanta13.successmantraadmin.model.ModelBuss
import com.kumarsidhanta13.successmantraadmin.model.ModelDaily
import com.kumarsidhanta13.successmantraadmin.model.ModelGreatPerson

class EditDailyMotivationQuotes : AppCompatActivity() {
    lateinit var dmqArrayList: ArrayList<ModelDaily>
    lateinit var adapterdmq: MyAdapterDaily
    lateinit var recyclerView: RecyclerView
    lateinit var refreshDmq: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_daily_motivation_quotes)

        recyclerView = findViewById(R.id.recyclerview_dmq)
        refreshDmq  =findViewById(R.id.refresh_dmq)
        tvError = findViewById(R.id.tv_error_dmq)

        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }

    private fun loadVideoFromFirebase() {
        dmqArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageDailyQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    dmqArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelDmq = ds.getValue(ModelDaily::class.java)
                        dmqArrayList.add(modelDmq!!)
                    }
                    if (dmqArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshDmq.visibility = View.GONE
                    } else {
                        dmqArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adapterdmq = MyAdapterDaily(this@EditDailyMotivationQuotes, dmqArrayList)
                        recyclerView.adapter = adapterdmq

                        refreshDmq.setOnRefreshListener {
                            recyclerView.adapter = adapterdmq
                            refreshDmq.isRefreshing = false
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}