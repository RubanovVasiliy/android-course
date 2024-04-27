    package com.example.intentmultitool

    import android.content.Context
    import android.content.Intent
    import android.net.ConnectivityManager
    import android.net.NetworkCapabilities
    import android.net.Uri
    import android.os.Bundle
    import android.view.View
    import android.widget.Button
    import android.widget.EditText
    import android.widget.ImageView
    import android.widget.TextView
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat


    class MainActivity : AppCompatActivity() {

        private lateinit var toast: Toast

        private lateinit var editTextTo: EditText
        private lateinit var editTextTheme: EditText
        private lateinit var editTextMessage: EditText

        private lateinit var imageView: ImageView

        private fun checkInternetOnClick(v: View) {
            val connectivityManager : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                toast = Toast.makeText(applicationContext, "Подключение есть \nДобро пожаловать в приложение!", Toast.LENGTH_LONG)
                toast.show()
                imageView.setImageResource(R.drawable.internet)
            }
            else{
                toast = Toast.makeText(applicationContext, "Нет подключения \nРазрешите доступ и повторите попытку", Toast.LENGTH_LONG)
                toast.show()
                imageView.setImageResource(R.drawable.no_internet)
            }
        }

        private fun openLinkOnClick(v: View) {
            val intent = Intent("com.example.Browser")
            intent.setData(Uri.parse("http://nstu.ru"))
            startActivity(intent)
        }

        private fun callOnClick(v: View) {
            if (v !is TextView) return

            val tv: TextView = v
            val myIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv.text.toString()))
            startActivity(myIntent)
        }

        private fun submitFormOnClick(v: View) {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.setType("plain/text");
            myIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(editTextTo.text.toString()))
            myIntent.putExtra(Intent.EXTRA_SUBJECT, editTextTheme.text.toString())
            myIntent.putExtra(Intent.EXTRA_TEXT, editTextMessage.text.toString())
            startActivity(Intent.createChooser(myIntent, "Написать разработчику"))
        }

        private fun clearFormOnClick(v: View) {
            editTextTo.setText("")
            editTextTheme.setText("")
            editTextMessage.setText("")
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)

            imageView = findViewById(R.id.imageView3)

            editTextTo = findViewById(R.id.editTextTo)
            editTextTheme = findViewById(R.id.editTextTheme)
            editTextMessage = findViewById(R.id.editTextMessage)

            val button = findViewById<Button>(R.id.button)
            button.setOnClickListener(::checkInternetOnClick)

            val buttonLink = findViewById<Button>(R.id.buttonLink)
            buttonLink.setOnClickListener(::openLinkOnClick)

            val buttonSubmit = findViewById<Button>(R.id.buttonSubmitForm)
            buttonSubmit.setOnClickListener(::submitFormOnClick)

            val buttonClearForm = findViewById<Button>(R.id.buttonClearForm)
            buttonClearForm.setOnClickListener(::clearFormOnClick)

            val phone1 = findViewById<TextView>(R.id.phoneText1)
            phone1.setOnClickListener(::callOnClick)

            val phone2 = findViewById<TextView>(R.id.phoneText2)
            phone2.setOnClickListener(::callOnClick)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }