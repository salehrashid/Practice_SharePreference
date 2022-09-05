package com.app.sharepreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import com.app.sharepreference.databinding.ActivityLoginBinding
import java.util.prefs.Preferences

class LoginActivity : AppCompatActivity() {

    protected var _binding: ActivityLoginBinding? = null
    protected val binding get() = _binding as ActivityLoginBinding

    //kita perlu mendapatkan class SharedPreference
    protected lateinit var sharedPreferences: SharedPreferences

    val PREFS_NAME = "belajarsharepreference"
    val KEY_EMAIL = "key.email"
    val KEY_PASSWORD = "key.password"
    val KEY_SESSION = "key.session"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    protected fun saveEmail(email: String) {
        //agar data yang ada bisa di edit
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        //setiap data yang disimpan berdasarkan key dan value
        editor.putString(KEY_EMAIL, email)
        //berfungsi untuk menyimpan data
        editor.apply()
    }

    protected fun savePassword(password: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    //untuk menyimpan data saat keluar aplikasi dan masuk lagi
    protected fun saveSession(session: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_SESSION, session)
        editor.apply()
    }

    protected fun getSession(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    protected fun msg() {
        val email: String = binding.inputEmail.text.toString()
        val password: String = binding.inputPassword.text.toString()
        when {
            email == "" -> {
                val msg = Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.TOP, 0, 100)
                msg.show()
            }
            password == "" -> {
                val msg = Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.TOP, 0, 100)
                msg.show()
            }
            password == "" && email == "" -> {
                val msg = Toast.makeText(this, "Enter password & email", Toast.LENGTH_SHORT)
                msg.setGravity(Gravity.TOP, 0, 100)
                msg.show()
            }
            else -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (getSession(KEY_SESSION)){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    fun onLogin(view: View) {
        val email = binding.inputEmail.text.toString()
        saveEmail(email)
        val password = binding.inputPassword.text.toString()
        savePassword(password)
        saveSession(true)
        msg()
    }
}