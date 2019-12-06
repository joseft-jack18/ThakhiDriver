package com.example.thakhidriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.thakhidriver.Clases.DetalleEnt
import com.example.thakhidriver.Conexion.ClsConexion

class Splash2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        CargarDatos()
        //Toast.makeText(this,"Bienvenido " + ClsConexion.id_entrega,
            //Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetalleEntregaActivity::class.java)
        val timer = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(intent)
                    finish()
                }
            }
        }
        timer.start()
    }

    fun CargarDatos(){
        var url= ClsConexion.url + "VerDetalleEntrega.php?ENTid=" + ClsConexion.id_entrega
        var rq= Volley.newRequestQueue(this)

        val sr = JsonArrayRequest( Request.Method.GET, url, null,
            Response.Listener { response ->
                DetalleEnt.ENTid = response.getJSONObject(0).getInt("ENTid")
                DetalleEnt.ENTdescripcion = response.getJSONObject(0).getString("ENTdescripcion")
                DetalleEnt.ENTprecio = response.getJSONObject(0).getDouble("ENTprecio")
                DetalleEnt.CLInombre= response.getJSONObject(0).getString("CLInombre")
                DetalleEnt.CLIapellido=response.getJSONObject(0).getString("CLIapellido")
                DetalleEnt.CLIcelular=response.getJSONObject(0).getString("CLIcelular")
                DetalleEnt.ENTestado=response.getJSONObject(0).getString("ENTestado")
            },
            Response.ErrorListener {  }
        )
        rq.add(sr)
    }
}
