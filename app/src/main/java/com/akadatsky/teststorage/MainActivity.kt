package com.akadatsky.teststorage

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showBitmapFomBase64String()

        // storage path by default
        // /data/data/com.akadatsky.teststorage/shared_prefs/main.xml
        val sharedPref = getSharedPreferences("main", Context.MODE_PRIVATE)

        // private to this activity
        // val sharedPref = getPreferences(Context.MODE_PRIVATE)

        findViewById<Button>(R.id.save).setOnClickListener {
            sharedPref.edit().putInt("some_key", 123).apply()
            sharedPref.edit().putString("some_other_key", "test_value").apply()

        /*
            <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
            <map>
                <string name="some_other_key">test_value</string>
                <int name="some_key" value="123" />
            </map>
         */
        }

        findViewById<Button>(R.id.load).setOnClickListener {
            val test = sharedPref.getInt("some_key", 0) // 123
            val s = sharedPref.getString("some_other_key", "") // test_value
            Toast.makeText(this, "Value: $test", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.clear).setOnClickListener {
            sharedPref.edit().clear().apply()
        }
    }

    private fun showBitmapFomBase64String() {
        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageBytes: ByteArray = Base64.decode(myImageSample, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        imageView.setImageBitmap(decodedImage)
    }

}