package train.android.kotlin.module.webview

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import android.widget.Toast
import train.android.kotlin.R

class webActivity : Activity() {

    private var web: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        val WebViw = findViewById(R.id.webView) as WebView
        WebViw.settings.javaScriptEnabled = true
        Toast.makeText(applicationContext, "URL  :  "+intent.getStringExtra("Url"), Toast.LENGTH_SHORT).show()
        var aa = intent.getStringExtra("Url")
        WebViw.loadUrl(intent.getStringExtra("Url"))

    }
}
