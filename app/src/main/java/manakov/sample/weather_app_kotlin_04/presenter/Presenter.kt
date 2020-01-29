package manakov.sample.weather_app_kotlin_04.presenter

import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import manakov.sample.weather_app_kotlin_04.R
import manakov.sample.weather_app_kotlin_04.model.WeatherApi
import manakov.sample.weather_app_kotlin_04.view.CustomView
import java.lang.Exception

class Presenter(val view : CustomView) {

    val api: WeatherApi = WeatherApi.Instance().getWeatherApi()
    init{
        bind()
    }

    fun bind() {
        view.refreshButton.setOnClickListener(
            View.OnClickListener { refresh() }
        )
    }

    fun refresh(){
        api.getWeatherData(
            "Novosibirsk"
            ,"4cf7f6610d941a1ca7583f50e7e41ba3"
            ,"metric"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer {
                    view.tempTextView   .setText(  it.main?.temp     + " " + "°C" )
                    view.humidTextView  .setText(  it.main?.humidity + " " + "%" )
                    view.windTextView   .setText(  it.wind?.speed    + " " + "mps" )

                    try {
                        val src = this.view.applicationContext.resources.getIdentifier(
                            "i" + it.weather?.get(0)?.icon + "2x",
                            "drawable",
                            view.applicationContext.packageName
                        ) as Int
                        view.weatherIconImageView.setImageResource(src)
                    } catch (e : Exception){
                        Log.d("error", "fck")
                    }
                }
            )
    }
}