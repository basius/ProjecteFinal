package com.example.basius.projectefinal;

import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by basius on 15/05/17.
 */

public class ApiTiempo {

    private static String url = "http://api.openweathermap.org/data/2.5/weather?APPID=89e1966d38650a9ab9a6bc73dd8e6c84&lat=41.396620&lon=2.200817&units=metric";
    private static InfoMeteo temp = new InfoMeteo();
    static String JsonResponse = null;


    public static String getWeather() {
        String code = "";
        try {
            JsonResponse = HttpUtils.get(url);
            JSONObject data = null;
            data = new JSONObject(JsonResponse);
            JSONArray jsonweather = data.getJSONArray("weather");
            JSONObject object;


            //Parte weather
            for (int i = 0; i < jsonweather.length(); i++) {
                object = jsonweather.getJSONObject(i);
                code = object.getString("icon");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static int CompruebaImagen(String imagen) {
        int link = 0;
        if (imagen.equals("03d") || imagen.equals("03n")) {
            link = R.drawable.nubes;
        }
        else if (imagen.equals("01d")) {
            link = R.drawable.sol;
        }
        else if (imagen.equals("01n")) {
            link = R.drawable.luna;
        }
        else if (imagen.equals("04n") || imagen.equals("02n")) {
            link = R.drawable.lunanublada;
        }
        else if (imagen.equals("04d") || imagen.equals("02d")) {
            link = R.drawable.parsol;
        }
        else if (imagen.equals("13d") || imagen.equals("13n")) {
            link = R.drawable.nieve;
        }
        else if (imagen.equals("10d") || imagen.equals("09d") || imagen.equals("09n") || imagen.equals("10n")) {
            link = R.drawable.lluvia;
        }
        else if (imagen.equals("11d") || imagen.equals("11n")) {
            link = R.drawable.rayos;
        }
        return link;
    }
}