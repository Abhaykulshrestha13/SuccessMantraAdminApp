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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterVideo
import com.kumarsidhanta13.successmantraadmin.model.ModelBuss
import com.kumarsidhanta13.successmantraadmin.model.ModelVideo

class EditBussinessActivity : AppCompatActivity() {
    lateinit var bussArrayList: ArrayList<ModelBuss>
    lateinit var adapterbuss: MyAdapterBuss
    lateinit var recyclerView: RecyclerView
    lateinit var refreshBuss: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bussiness)
        recyclerView = findViewById(R.id.recyclerview_buss)
        refreshBuss  =findViewById(R.id.refresh_buss)
        tvError = findViewById(R.id.tv_error_buss)
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
                    for (ds in snapshot.children) {
                        val modelBuss = ds.getValue(ModelBuss::class.java)
                        bussArrayList.add(modelBuss!!)
                    }
                    if (bussArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshBuss.visibility = View.GONE
                    } else {
                        bussArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adapterbuss = MyAdapterBuss(this@EditBussinessActivity, bussArrayList)
                        recyclerView.adapter = adapterbuss
                        refreshBuss.setOnRefreshListener {
                            recyclerView.adapter = adapterbuss
                            refreshBuss.isRefreshing = false
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}