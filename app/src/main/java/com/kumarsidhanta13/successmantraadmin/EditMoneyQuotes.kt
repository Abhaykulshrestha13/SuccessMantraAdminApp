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
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterLife
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterLove
import com.kumarsidhanta13.successmantraadmin.adapter.MyAdapterMoney
import com.kumarsidhanta13.successmantraadmin.model.ModelLife
import com.kumarsidhanta13.successmantraadmin.model.ModelLove
import com.kumarsidhanta13.successmantraadmin.model.ModelMoney

class EditMoneyQuotes : AppCompatActivity() {
    lateinit var moneyArrayList: ArrayList<ModelMoney>
    lateinit var adaptermoney: MyAdapterMoney
    lateinit var recyclerView: RecyclerView
    lateinit var refreshMoney: SwipeRefreshLayout
    lateinit var tvError: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_money_quotes)
        recyclerView = findViewById(R.id.recyclerview_money)
        refreshMoney  =findViewById(R.id.refresh_money)
        tvError = findViewById(R.id.tv_error_money)

        var linearLayoutManager = LinearLayoutManager(this)
//        linearLayoutManager.reverseLayout = true
//        linearLayoutManager.stackFromEnd = true
//        recyclerView.layoutManager = linearLayoutManager
        loadVideoFromFirebase()
    }
    private fun loadVideoFromFirebase() {
        moneyArrayList = ArrayList()
        val ref = FirebaseDatabase.getInstance().getReference("ImageMoneyQuotes")
        ref.addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    moneyArrayList.clear()
                    for (ds in snapshot.children) {
                        val modelBuss = ds.getValue(ModelMoney::class.java)
                        moneyArrayList.add(modelBuss!!)
                    }
                    if (moneyArrayList.isEmpty()) {
                        tvError.visibility = View.VISIBLE
                        refreshMoney.visibility = View.GONE
                    } else {
                        moneyArrayList.reverse()
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                        adaptermoney = MyAdapterMoney(this@EditMoneyQuotes, moneyArrayList)
                        recyclerView.adapter = adaptermoney
                        refreshMoney.setOnRefreshListener {
                            recyclerView.adapter = adaptermoney
                            refreshMoney.isRefreshing = false
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}