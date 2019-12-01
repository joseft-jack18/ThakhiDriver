package com.example.thakhidriver

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thakhidriver.Clases.admENTtEntrega
import com.example.thakhidriver.Clases.admENTtEntrega.Companion.CLIapellido
import com.example.thakhidriver.Clases.admENTtEntrega.Companion.ENTdescripciom
import com.example.thakhidriver.Clases.admENTtEntrega.Companion.ENTestado
import com.example.thakhidriver.Clases.admENTtEntrega.Companion.ENTid
import com.example.thakhidriver.Clases.admENTtEntrega.Companion.ENTprecio
import com.example.thakhidriver.Conexion.ClsConexion
import kotlinx.android.synthetic.main.lista_entregas.view.*

class DetalleAdapter (var con: Context, var list:ArrayList<admENTtEntrega>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v= LayoutInflater.from(con).inflate(R.layout.lista_entregas,
            parent,false)
        return EntregaHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EntregaHolder).show(list[position].ENTid,list[position].ENTdescripciom,
            list[position].ENTprecio,list[position].CLInombre,list[position].CLIapellido,list[position].ENTestado)

        holder.itemView.entrega_cv.setOnClickListener {
            var i= Intent(con,DetalleEntregaActivity::class.java)
            //i.putExtra("serviceid",list[position].id)

            ENTid=list[position].ENTid
            ENTdescripciom=list[position].ENTdescripciom
            ENTprecio=list[position].ENTprecio
            CLIapellido=list[position].CLInombre
            CLIapellido=list[position].CLIapellido
            ENTestado=list[position].ENTestado

            con.startActivity(i)
        }
    }

    class EntregaHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun show(a:Int,b:String,c:Double,d:String,e:String,f:String){
            itemView.producto.text=a.toString()
            itemView.tv_descripcion.text=b
            itemView.tv_costo.text= "Cobrar S/. " + c.toString()
            itemView.tv_cliente.text=d + " " + e
            if(f == "P"){
                itemView.tv_estado.setBackgroundColor(Color.parseColor("#0B4FBD"))
            }
            if(f == "N"){
                itemView.tv_estado.setBackgroundColor(Color.parseColor("#F01717"))
            }
            if(f == "E"){
                itemView.tv_estado.setBackgroundColor(Color.parseColor("#27F017"))
            }
            //Picasso.get().load(UserInfo.url + p).into(itemView.service_photo)
        }
    }
}