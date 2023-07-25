package com.example.brayan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
lateinit var BRestablecer: Button
lateinit var TnContra: EditText
lateinit var TcContra: EditText
class vistarecuperacion : AppCompatActivity() {
    private var connectSql = ConnectSql();

    private var mCounter: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vistarecuperacion)

        BRestablecer = findViewById(R.id.btnRestablecer)
        TnContra = findViewById(R.id.txtConfirmarNuevaContra)
        TcContra = findViewById(R.id.txtConfirmarNuevaContra)

        BRestablecer.setOnClickListener {
            if(TnContra.text.toString().trim() == "" || TcContra.text.toString().trim() == "")
            {
                Toast.makeText(this, "Asegúrese de ingresar una contraseña", Toast.LENGTH_SHORT).show()
            }
            else if(TnContra.text.toString().trim() != TcContra.text.toString().trim())
            {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                TnContra.requestFocus()
            }
            else if(TnContra.text.toString().trim().count() < 8)
            {
                Toast.makeText(this, "La contraseña es demasiado corta", Toast.LENGTH_SHORT).show()
            }
            else if(TnContra.text.toString().trim().count() > 20)
            {
                Toast.makeText(this, "La contraseña es demasiado extensa", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val Correo: String = intent.getStringExtra("0")!!
                RestablecerContra(Correo, TnContra.text.toString().trim())
                Toast.makeText(this, "Se ha restablecido su contraseña exitosamente", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }public fun RestablecerContra(correo: String, contra: String): Int
    {
        try
        {
            val AgregarUsuario =connectSql.dbConn()?.prepareStatement("update usuarios set contra_usuario = '$contra' where correo_usuario = '$correo';")!!
            AgregarUsuario.executeUpdate()
        }
        catch (e: java.lang.Exception)
        {
            Toast.makeText(this, "Ha habido un error en la aplicación, intentélo más tarde o reinicie la aplicación", Toast.LENGTH_LONG).show()
            return 0
        }
        return 1
    }
}