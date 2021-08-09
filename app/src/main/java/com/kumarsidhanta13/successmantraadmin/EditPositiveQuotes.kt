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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterMoney
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterPositive
import com.kumarsidhanta13.successmantraadmin.model.ModelMoney
import com.kumarsidhanta13.successmantraadmin.model.ModelPositive

class EditPositiveQuotes : AppCompatActivity() {

    lateinit var positiveArrayList: ArrayList<ModelPositive>
    lateinit var adapterpositive: MyAdapterPositive
    lateinit var recyclerView: RecyclerView
    lateinit var refreshPositive: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_positive_quotes)
        recyclerView = findViewById(R.id.recyclerview_positive)
        refreshPositive  =findViewById(R.id.refresh_positive)
        tvError = findViewById(R.id.tv_error_positive)

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
                    if (positiveArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshPositive.visibility = View.GONE
                    } else {
                        positiveArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adapterpositive =
                            MyAdapterPositive(this@EditPositiveQuotes, positiveArrayList)
                        recyclerView.adapter = adapterpositive
                        refreshPositive.setOnRefreshListener {
                            recyclerView.adapter = adapterpositive
                            refreshPositive.isRefreshing = false
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}