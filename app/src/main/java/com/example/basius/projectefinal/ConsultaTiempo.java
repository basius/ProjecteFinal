package com.example.basius.projectefinal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import static com.google.android.gms.internal.zzs.TAG;

public class ConsultaTiempo extends Fragment {

    //FIREBASE
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referencia;
    View view;
    TextView tempF;
    TextView humF;
    TextView presF;
    TextView infoLastUpdate;
    ImageView iconoTiempo;
    //INFO METEO
    public ConsultaTiempo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        FetchResults fR = new FetchResults();
        fR.execute("");
        view = inflater.inflate(R.layout.fragment_consulta_tiempo2, container, false);
        return view;
    }

    public void UltimosResultados(int _link){
        final int link = _link;
        referencia = database.getReference();
        Query lastQuery = referencia.orderByKey().limitToLast(1);
        lastQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String hour = "";
                double temp = 0.0;
                double humidity = 0.0;
                double press = 0.0;
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot dt = iterator.next();
                    for(DataSnapshot test : dt.getChildren()){
                        //Hora de la actualitzacion
                        hour = test.getKey();
                        if(!test.child("TEMPERATURAMEDIA").exists()) {
                            //Temperatura de la ultima actualitzacion
                            temp = test.child("TEMPERATURA").getValue(Double.class);
                            humidity = test.child("HUMIDITY").getValue(Double.class);
                            press = test.child("PRESSURE").getValue(Double.class);
                        }
                    }
                }
                tempF = (TextView) view.findViewById(R.id.tempField);
                tempF.setText((double)Math.round(temp*10d)/10d+" ºC");
                humF = (TextView) view.findViewById(R.id.humField);
                humF.setText((double)Math.round(humidity*10d)/10d+" %");
                presF = (TextView) view.findViewById(R.id.presField);
                presF.setText((double)Math.round(press*10d)/10d+" hPa");
                //infoLastUpdate = (TextView) view.findViewById(R.id.infoLastUpdate);
               // infoLastUpdate.setText("LAST UPDATE: "+hour);
                System.out.println(hour+" --> TEMPERATURA: "+temp);
                System.out.println(hour+" --> HUMIDITY: "+humidity);
                System.out.println(hour+" --> PRESSURE: "+press);

                iconoTiempo = (ImageView) view.findViewById(R.id.imageViewApi);
                Drawable myDrawable = getResources().getDrawable(link);
                iconoTiempo.setImageDrawable(myDrawable);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private class FetchResults extends AsyncTask<String, Void, Integer>{
        @Override
        protected Integer doInBackground(String... strings) {
            //Llamada  a la Api para obtener el codigo
            String codigo = ApiTiempo.getWeather();
            //Devolvemos el codigo de la imagen como entero
            return ApiTiempo.CompruebaImagen(codigo);
        }

        protected void onPostExecute(Integer link) {
            // Pasamos el entero de la respuesta obtenida en la API
            UltimosResultados(link);
        }
    }

}
