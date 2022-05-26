package com.safaashatha.allin1

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.list_products.view.*
import java.io.File

class useradapter(private val context: Activity, private val arrayList: ArrayList<user>):ArrayAdapter<user>(context,
R.layout.list_products,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.list_products,parent,false);

        val nameview:TextView=view.findViewById(R.id.username)
        val phoneview:TextView=view.findViewById(R.id.userphone)
        val adderview:TextView=view.findViewById(R.id.useraddress)
        //val filename = FirebaseAuth.getInstance().currentUser!!.uid.toString()


        nameview.text=arrayList[position].Firstname
        phoneview.text=arrayList[position].phone
        adderview.text=arrayList[position].Address


        val product=arrayList[position]
            val inflator=context!!.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE)as LayoutInflater
            var productview=inflator.inflate(R.layout.activity_customersdetails,null)

        view.productimage.setOnClickListener(){
                var intent= Intent(context,customersdetails::class.java)
                //intent.putExtra("image",product.image!!)

                intent.putExtra("name",arrayList[position].Firstname.toString())
                intent.putExtra("phone",arrayList[position].phone.toString()!!)
                intent.putExtra("address",arrayList[position].Address.toString()!!)


            context!!.startActivity(intent)

            }

        return view
    }
}