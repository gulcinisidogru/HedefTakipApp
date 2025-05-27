package com.example.hedeftakipapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info) // activity_info.xml'e bağlandı

        val infoTextView = findViewById<TextView>(R.id.infoTextView)
        infoTextView.text = """
            Uygulama Bilgisi

            Bu uygulama, 21 gün kuralına göre alışkanlıklarınızı takip etmenize yardımcı olmak için tasarlanmıştır.

            Özellikler:
            - Alışkanlıklarınızı ekleyin ve yönetin.
            - Tamamlanan günleri işaretleyin ve ilerlemenizi görsel olarak takip edin.
            - Önerilen alışkanlıklar arasından seçim yapın.
            - Profilinizde istatistiklerinizi ve başarımlarınızı görün.
            - Tema ayarlarıyla uygulamayı kişiselleştirin.

            Geliştirici: [Adınız/Şirketiniz]
            Versiyon: 1.0.0
            Son Güncelleme: Mayıs 2025
        """.trimIndent()
    }
}