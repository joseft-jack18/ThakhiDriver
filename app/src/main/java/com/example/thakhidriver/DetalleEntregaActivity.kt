package com.example.thakhidriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.thakhidriver.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_detalle_entrega.*

class DetalleEntregaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_entrega)

        var url= ClsConexion.url + "VerDetalleEntrega.php?ENTid=" + ClsConexion.id_entrega
        var rq= Volley.newRequestQueue(this)

        val sr = JsonArrayRequest( Request.Method.GET, url, null,
            Response.Listener { response ->
                codigo.text = (response.getJSONObject(0).getInt("ENTid")).toString()
                descripcion.text = (response.getJSONObject(0).getInt("ENTdescripcion")).toString()
                costo.text = (response.getJSONObject(0).getInt("ENTprecio")).toString()
                cliente.text = (response.getJSONObject(0).getInt("CLInombre")).toString() + " " +
                        (response.getJSONObject(0).getInt("CLIapellido")).toString()
            },
            Response.ErrorListener {  }
        )
        rq.add(sr)
    }
}
