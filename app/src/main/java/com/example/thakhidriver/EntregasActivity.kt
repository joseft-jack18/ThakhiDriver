package com.example.thakhidriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.thakhidriver.Clases.admENTtEntrega
import com.example.thakhidriver.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_entregas.*

class EntregasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entregas)

        var url:String= ClsConexion.url + "ListarEntregas.php"
        var list=ArrayList<admENTtEntrega>()

        var rq= Volley.newRequestQueue(this)
        var jar= JsonArrayRequest(
            Request.Method.GET,url,
            null, Response.Listener { response ->
                for(x in 0..response.length()-1){
                    list.add(
                        admENTtEntrega(
                            response.getJSONObject(x).getInt("id"),
                            response.getJSONObject(x).getString("name"),
                            response.getJSONObject(x).getString("photo")
                        )
                    )
                }
                var adp=DetalleAdapter(this,list)
                entregas_rv.adapter=adp
                entregas_rv.layoutManager= GridLayoutManager(this,2)
            },
            Response.ErrorListener {  })
        rq.add(jar)
    }
}
