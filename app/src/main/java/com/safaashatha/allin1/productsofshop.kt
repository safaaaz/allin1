package com.safaashatha.allin1

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.productsdetails.*

class productsofshop:  AppCompatActivity() {
    private lateinit var productsarrylist: ArrayList<product>
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productsofshop)
        val b = intent.extras!!
        val prodname = b.getString("prodId")
        //print("\n uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu\n")
        //print("\n"+prodname+"\n")
        //productsname.text = prodname
        showprod(prodname.toString())


    }

    private fun showprod(newtext:String) {
        //print("\n xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n")
        //print("\n"+newtext+"\n")
        //prodcattext.text=newtext
        //var newtext = productsname.text.toString()
        var flag = 0

        productsarrylist = ArrayList()
        //noprod.text=""
        database =
            FirebaseDatabase.getInstance("https://allin1-23085-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("shops")
        database.get().addOnSuccessListener {
            for (x in it.children) {

                if (x.exists()) {
                    if(x.key.equals(newtext))
                        for (prod in x.child("products").children) {

                            val pr = product(
                                prod.child("name").value.toString(),
                                prod.child("owner").value.toString(),
                                prod.child("price").value.toString(),
                                prod.child("category").value.toString(),
                                //R.drawable.img
                            )
                            productsarrylist.add(pr)


                        }
                }
            }

            val listView: GridView = findViewById(R.id.products)
            listView.setAdapter(productadapter(this, productsarrylist))
        }
    }
}