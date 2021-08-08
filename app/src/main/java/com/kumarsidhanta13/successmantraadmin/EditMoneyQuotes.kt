package com.kumarsidhanta13.successmantraadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_money_quotes)
        recyclerView = findViewById(R.id.recyclerview_money)
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
//                    var option:FirebaseRecyclerOptions<ModelVideo> = FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(FirebaseDatabase.getInstance().reference.child("Videos"),ModelVideo::class.java).build()
                    adaptermoney = MyAdapterMoney(this@EditMoneyQuotes, moneyArrayList)
                    recyclerView.adapter = adaptermoney
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
    }
}