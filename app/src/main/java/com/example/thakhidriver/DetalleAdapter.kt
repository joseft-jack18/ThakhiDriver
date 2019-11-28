package com.example.thakhidriver

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thakhidriver.Clases.admENTtEntrega

class DetalleAdapter (var con: Context, var list:ArrayList<admENTtEntrega>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v= LayoutInflater.from(con).inflate(R.layout.lista_entregas,
            parent,false)
        return ServiceHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ServiceHolder).show(list[position].name,
            list[position].photo)

        holder.itemView.service_photo.setOnClickListener {
            var i= Intent(con,OrderActivity::class.java)
            //i.putExtra("serviceid",list[position].id)
            UserInfo.service_id=list[position].id.toString()
            con.startActivity(i)
        }
    }

    class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun show(n:String,p:String){
            itemView.service_name.text=n
            Picasso.get().load(UserInfo.url + p).into(itemView.service_photo)
        }
    }
}