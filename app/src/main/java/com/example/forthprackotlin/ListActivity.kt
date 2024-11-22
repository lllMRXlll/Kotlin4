package com.example.forthprackotlin

import android.os.Bundle
import android.os.Environment
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Получаем список файлов и передаём его в адаптер
        val files = getFilesFromDirectory()
        recyclerView.adapter = ListAdapter(files)
    }

    // Метод для получения списка файлов из папки "photos"
    private fun getFilesFromDirectory(): List<File> {
        val photosDir = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos")
        if (!photosDir.exists()) {
            photosDir.mkdirs()
        }

        // Получаем список всех файлов и сортируем их по дате последней модификации (по возрастанию)
        val files = photosDir.listFiles()?.sortedBy { it.lastModified() } ?: emptyList()
        return files
    }
}