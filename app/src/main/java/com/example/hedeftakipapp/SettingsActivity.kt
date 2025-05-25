package com.example.hedeftakipapp

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var profileImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        profileImage = findViewById(R.id.profile_image)
        val uploadButton = findViewById<Button>(R.id.btn_upload)
        val themeRadioGroup = findViewById<RadioGroup>(R.id.theme_radio_group)
        val notificationSwitch = findViewById<Switch>(R.id.notification_switch)
        val feedbackButton = findViewById<Button>(R.id.btn_feedback)

        // Profil fotoğrafı yükleme
        uploadButton.setOnClickListener {
            openImageChooser()
        }

        // Tema seçimi
        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.radio_dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.radio_system -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        // Bildirim ayarı
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Bildirim ayarını kaydetme işlemi
        }

        // Geri bildirim gönderme
        feedbackButton.setOnClickListener {
            sendFeedback()
        }
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            startActivityForResult(this, PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            profileImage.setImageURI(selectedImage)
        }
    }

    private fun sendFeedback() {
        AlertDialog.Builder(this)
            .setTitle("Geri Bildirim Gönder")
            .setMessage("Uygulama hakkındaki görüşleriniz bizim için değerli!")
            .setPositiveButton("E-posta Gönder") { _, _ ->
                val emailIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("destek@example.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Uygulama Geri Bildirimi")
                }
                startActivity(Intent.createChooser(emailIntent, "E-posta ile gönder"))
            }
            .setNegativeButton("İptal", null)
            .show()
    }
}