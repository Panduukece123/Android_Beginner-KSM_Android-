package com.example.apk_pandu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apk_pandu.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showToast("Sedang di fase onCreate()")
        Log.d("onCreate()", "sedang onCreate")

        binding.btnExplicit.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, ExplicitIntentActivity::class.java)
            startActivity(moveIntent)
        }
        binding.btnExplicit.setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.editTextUsername).text.toString()
            val intent = Intent(this, ExplicitIntentActivity::class.java).apply {
                putExtra("USERNAME", username)
            }
            startActivity(intent)
        }


        binding.btnImplicit.setOnClickListener {
            val phoneNumber = "082218496939"
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(callIntent)
        }

        binding.btnImplicit.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        showToast("Sedang di fase onStart()")
    }

    override fun onResume() {
        super.onResume()
        showToast("Sedang di fase onResume()")
    }

    override fun onPause() {
        super.onPause()
        showToast("Sedang di fase onPause()")
    }

    override fun onStop() {
        super.onStop()
        showToast("Sedang di fase onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("Sedang di fase onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        showToast("Sedang di fase onRestart()")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_implicit -> {
                val phoneNumber = "082218496939"
                val implicitIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(implicitIntent)
            }
        }
    }
}