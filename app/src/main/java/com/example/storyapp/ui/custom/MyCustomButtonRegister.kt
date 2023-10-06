package com.example.storyapp.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.storyapp.R

class MyCustomButtonRegister: AppCompatButton {

    private lateinit var enabledBackground: Drawable
    private lateinit var disabledBackground: Drawable
    private var txtColor: Int = 0
    constructor(context: Context) : super(context) {
        // Logika tambahan untuk konstruktor pertama
        // context untuk membuat instance dari MyButton
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // Logika tambahan untuk konstruktor kedua
        // attrs digunakan untuk menangani atribut XML yang mungkin didefinisikan untuk MyButton dalam file layout XML.
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        // Logika tambahan untuk konstruktor ketiga
        // defStyleAttr adalah sebuah atribut gaya default yang akan diterapkan pada instance MyButton Misalnya, inisialisasi custom, menambahkan click listener, dsb.
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if(isEnabled) enabledBackground else disabledBackground
        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = if(isEnabled) "Submit" else "Isi Dulu"
    }

    private fun init(){
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button) as Drawable
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disabble) as Drawable
    }
}