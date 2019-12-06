package com.example.thakhidriver

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thakhidriver.Clases.DetalleEnt
import com.example.thakhidriver.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_detalle_entrega.*

class DetalleEntregaActivity : AppCompatActivity() {

    var est:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_entrega)

        codigo.text = DetalleEnt.ENTid.toString()
        descripcion.text = DetalleEnt.ENTdescripcion.toString()
        costo.text = DetalleEnt.ENTprecio.toString()
        cliente.text = DetalleEnt.CLInombre + " " + DetalleEnt.CLIapellido
        celular.text = DetalleEnt.CLIcelular
        if(DetalleEnt.ENTestado=="P"){
            pendiente.isChecked = true
        }
        if(DetalleEnt.ENTestado=="E"){
            entregado.isChecked = true
        }
        if(DetalleEnt.ENTestado=="N"){
            no_entregado.isChecked = true
        }

        llamada.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + DetalleEnt.CLIcelular)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        ubicacion_cliente.setOnClickListener{

        }

        /*foto.setOnClickListener{

        }*/

        guardar.setOnClickListener{
            if(pendiente.isChecked == true){
                est = "P"
            }
            if(entregado.isChecked == true){
                est = "E"
            }
            if(no_entregado.isChecked == true){
                est = "N"
            }

            var url = ClsConexion.url + "ModificarEntrega.php?ENTestado=" + est +
                    "&ENTid=" + DetalleEnt.ENTid
            var rq= Volley.newRequestQueue(this)
            var sr= StringRequest(Request.Method.GET,url,
                Response.Listener { response ->
                    Toast.makeText(this,"Datos modificados correctamente...!",
                        Toast.LENGTH_LONG).show()
                    var i= Intent(this,EntregasActivity::class.java)
                    startActivity(i)
                },
                Response.ErrorListener {  })
            rq.add(sr)
        }
    }
}
