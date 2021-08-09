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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterPositive
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterSuccessQuotes
import com.kumarsidhanta13.successmantraadmin.model.ModelPositive
import com.kumarsidhanta13.successmantraadmin.model.ModelSuccess

class EditSuccessQuotes : AppCompatActivity() {
    lateinit var successArrayList: ArrayList<ModelSuccess>
    lateinit var adaptersuccess: MyAdapterSuccessQuotes
    lateinit var recyclerView: RecyclerView
    lateinit var refreshSuccess: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_success_quotes)
        recyclerView = findViewById(R.id.recyclerview_succ)
        refreshSuccess  =findViewById(R.id.refresh_success)
        tvError = findViewById(R.id.tv_error_success)

        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }
    private fun loadVideoFromFirebase() {
        successArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageSuccessQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    successArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelBuss = ds.getValue(ModelSuccess::class.java)
                        successArrayList.add(modelBuss!!)
                    }
                    if (successArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshSuccess.visibility = View.GONE
                    } else {
                        successArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adaptersuccess =
                            MyAdapterSuccessQuotes(this@EditSuccessQuotes, successArrayList)
                        recyclerView.adapter = adaptersuccess
                        refreshSuccess.setOnRefreshListener {
                            recyclerView.adapter = adaptersuccess
                            refreshSuccess.isRefreshing = false
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}