package com.raissa.gatos

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var catImageView: ImageView
    private lateinit var loadCatButton: Button
    private lateinit var catImageView2: ImageView
    private lateinit var loadCatButton2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        catImageView = findViewById(R.id.catImageView)
        loadCatButton = findViewById(R.id.loadCatButton)
        catImageView2 = findViewById(R.id.catImageView2)
        loadCatButton2 = findViewById(R.id.loadCatButton2)

        loadCatButton.setOnClickListener {
            loadRandomCat()
        }

        loadCatButton2.setOnClickListener {
            loadBengalCats()
        }
    }

    private fun loadRandomCat() {
        lifecycleScope.launch {
            try {
                val catsList = ApiClient.instance.getRandomCat()
                if (catsList.isNotEmpty()) {
                    val imageUrl = catsList[0].url
                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_placeholder_pic)
                        .error(R.drawable.error_image_pic)
                        .fit()
                        .centerCrop()
                        .into(catImageView)
                } else {
                    Toast.makeText(this@MainActivity, "Nenhuma imagem encontrada", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadBengalCats() {
        lifecycleScope.launch {
            try {
                val catsList = ApiClient.instance.getBengalCats()
                if (catsList.isNotEmpty()) {
                    val imageUrl = catsList[0].url
                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_placeholder_pic)
                        .error(R.drawable.error_image_pic)
                        .fit()
                        .centerCrop()
                        .into(catImageView2)
                } else {
                    Toast.makeText(this@MainActivity, "Nenhuma imagem encontrada", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
