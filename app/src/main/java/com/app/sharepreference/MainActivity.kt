package com.app.sharepreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ConditionVariable
import android.view.View
import com.app.sharepreference.databinding.ActivityLoginBinding
import com.app.sharepreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    protected var _binding: ActivityMainBinding? = null
    protected val binding get() = _binding as ActivityMainBinding

    protected lateinit var sharedPreferences: SharedPreferences

    val PREFS_NAME = "belajarsharepreference"
    val KEY_EMAIL = "key.email"
    val KEY_PASSWORD = "key.password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        getEmail()
    }

    protected fun getEmail() {
        val email = sharedPreferences.getString(KEY_EMAIL, null)
        binding.textViewEmail.text = email
    }

    fun onLogout(view: View) {
        clearData()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    protected fun clearData() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}