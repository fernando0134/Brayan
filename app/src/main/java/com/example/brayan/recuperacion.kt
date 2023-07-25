package com.example.brayan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

lateinit var TTextoContra: EditText
lateinit var BEnviarCodigo: Button
class recuperacion : AppCompatActivity() {
    private var connectSql = ConnectSql();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)
        TTextoContra = findViewById(R.id.txtEnviarCorreo)
        BEnviarCodigo = findViewById(R.id.btnEnviarCorreo)

        val PatronEmail: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        BEnviarCodigo.setOnClickListener {

            if(TTextoContra.text.toString().trim() == "")
            {
                Toast.makeText(this, "Ingrese una dirección de correo electrónico", Toast.LENGTH_SHORT).show()
            }
            else if(TTextoContra.text.toString().trim().matches(PatronEmail.toRegex()) == false)
            {
                Toast.makeText(this, "La dirección de correo electrónico no es válida", Toast.LENGTH_SHORT).show()
            }            else if(TTextoContra.text.toString().trim().matches(PatronEmail.toRegex()))
            {
                val randomNumber = (80000..99999).random()
                val Codigo: Intent = Intent(this, vistarecuperacion::class.java)
                Codigo.putExtra("0",randomNumber.toString())
                Codigo.putExtra("1", TTextoContra.text.toString())
                startActivity(Codigo)
            }
            else
            {
                Toast.makeText(this, "Ha habido un error al enviar el código de recuperación, intentelo de nuevo más tarde", Toast.LENGTH_LONG).show()
            }
        }
    }
}