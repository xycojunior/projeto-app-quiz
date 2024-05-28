package com.stackmobile.projetotde4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
