package com.example.brayan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.sql.PreparedStatement
import java.sql.SQLException

lateinit var nombreusuario: EditText
lateinit var contraseñausuario : EditText
lateinit var boton : Button
lateinit var botoncito : Button

class MainActivity : AppCompatActivity() {

    private var connectSql = ConnectSql()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombreusuario = findViewById(R.id.txtusuario)
        contraseñausuario = findViewById(R.id.txtcontraseña)
        boton = findViewById(R.id.btnEnviar)
        botoncito = findViewById(R.id.btnContraseña)

        botoncito.setOnClickListener {
            val intentpantalla3: Intent = Intent(this, recuperacion::class.java)
            startActivity(intentpantalla3)
        }
        boton.setOnClickListener {

            val username = nombreusuario.text.toString().trim()
            val password = contraseñausuario.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Snackbar.make(it, "Completa todos los campos", Snackbar.LENGTH_SHORT).show()

            } else {
                val intentpantalla2: Intent = Intent(this, pantallacarga::class.java)
                startActivity(intentpantalla2)
            }
            try {
                val addUsuarios: PreparedStatement =
                    connectSql.dbConn()?.prepareStatement("insert into usuario values(?,?)")!!
                addUsuarios.setString(1, nombreusuario.text.toString())
                addUsuarios.setString(2, contraseñausuario.text.toString())
                addUsuarios.executeUpdate()

                Toast.makeText(
                    this,
                    "Usuario y contraseña ingresados correctamente",
                    Toast.LENGTH_LONG
                ).show()
                Toast.makeText(this, "Error al ingresar", Toast.LENGTH_SHORT).show()
            } catch (ex: SQLException) {
                Toast.makeText(this, "Error al ingresar", Toast.LENGTH_SHORT).show()
            }
        }

    }
}