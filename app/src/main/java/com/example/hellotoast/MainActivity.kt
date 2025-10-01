package com.example.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonDial = findViewById<Button>(R.id.button_dial)
        val buttonMap = findViewById<Button>(R.id.button_map)
        val buttonEmail = findViewById<Button>(R.id.button_email)

        // Tombol Counter
        buttonCountUp.setOnClickListener {
            mCount++
            Log.d("mCount", mCount.toString())
            mShowCount.text = mCount.toString()
        }

        // Tombol Toast
        buttonToast.setOnClickListener {
            val tulisan: String = mShowCount.text.toString()
            val toast = Toast.makeText(this, "Angka yang dimunculkan $tulisan", Toast.LENGTH_LONG)
            toast.show()
        }

        // Explicit Intent: Pindah ke Activity2
        buttonSwitchPage.setOnClickListener {
            try {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                Toast.makeText(this, "Berhasil pindah ke halaman 2", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("SwitchPage", "Error pindah halaman", e)
            }
        }

        // Implicit Intent: Buka Browser
        buttonBrowser.setOnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.data = Uri.parse("https://www.google.com/")
            startActivity(intentbrowse)
        }

        // Implicit Intent: Buka Dial Phone
        buttonDial.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:08123456789") // ganti dengan nomor lain
            startActivity(dialIntent)
        }

        // Implicit Intent: Buka Lokasi Maps
        buttonMap.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.data = Uri.parse("geo:-6.175392,106.827153?q=Monas Jakarta")
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        // Implicit Intent: Kirim Email
        buttonEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:example@email.com")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Judul Email dari Aplikasi")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Halo, ini isi email otomatis dari aplikasi HelloToast.")
            startActivity(emailIntent)
        }
    }
}
