package manakov.sample.weather_app_kotlin_04.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import manakov.sample.weather_app_kotlin_04.presenter.Presenter
import manakov.sample.weather_app_kotlin_04.R

class MainActivity : CustomView() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val refreshButton   : Button = findViewById(  R.id.refreshButton  )

        val tempTextView    : TextView = findViewById(  R.id.tempTextView   )
        val windTextView    : TextView = findViewById(  R.id.windTextView   )
        val humidTextView   : TextView = findViewById(  R.id.humidTextView  )

        val iconImageView   : ImageView = findViewById( R.id.weatherIconImageView )

        val presenter : Presenter = Presenter(this);
    }
}
