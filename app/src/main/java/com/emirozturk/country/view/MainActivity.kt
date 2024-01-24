package com.emirozturk.country.view
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.emirozturk.country.R

class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*fun getToolbar(): Toolbar? {
        if (toolbar == null) {
            toolbar = findViewById(R.id.toolbar)
            if (toolbar != null) {
                setSupportActionBar(toolbar)
            }
        }
        return toolbar
    }*/
}