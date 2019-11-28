package com.example.thakhidriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.thakhidriver.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btningresar.setOnClickListener {

            var url= ClsConexion.url + "Login.php?CONemail=" + txtcorreo.text.toString() + "&CONclave=" + txtpassword.text.toString()
            var rq= Volley.newRequestQueue(this)

            val sr = JsonArrayRequest( Request.Method.GET, url, null,
                Response.Listener { response ->
                    Toast.makeText(this,"Bienvenido " + response.getJSONObject(0).getString("CONnombre"),
                        Toast.LENGTH_SHORT).show()
                    ClsConexion.dni = response.getJSONObject(0).getString("CONdni")
                    ClsConexion.nombres = response.getJSONObject(0).getString("CONnombre") + " " +
                            response.getJSONObject(0).getString("CONapellido")

                    var i= Intent(this,MainActivity::class.java)
                    startActivity(i)
                },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                    Toast.makeText(this,"Usuario y/o clave incorrecto...", Toast.LENGTH_SHORT).show()
                }
            )
            rq.add(sr)
        }
    }
}
