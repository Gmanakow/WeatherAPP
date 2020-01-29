package manakov.sample.weather_app_kotlin_04.model

import io.reactivex.Observable
import io.reactivex.Single
import manakov.sample.weather_app_kotlin_04.model.data.WeatherData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    class Instance {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(
                    "https://api.openweathermap.org/"
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .build()
        }

        fun getWeatherApi() : WeatherApi{
            return getRetrofit().create(WeatherApi::class.java)
        }
    }

    @GET("data/2.5/weather")
    fun getWeatherData(
        @Query("q")     location : String,
        @Query("appid") appid    : String,
        @Query("units") units    : String
    ) : Single<WeatherData>

}