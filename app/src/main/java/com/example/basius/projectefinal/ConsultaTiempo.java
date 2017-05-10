package com.example.basius.projectefinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        // Inflate the layout for this fragment
        referencia = database.getReference();
        Query lastQuery = referencia.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        //Hora de la actualitzacio
                        hour = test.getKey();
                        //Temperatura de la ultima actualitzacio
                        temp = test.child("TEMPERATURA").getValue(Double.class);
                        humidity = test.child("HUMIDITY").getValue(Double.class);
                        press = test.child("PRESSURE").getValue(Double.class);
                    }
                }
                System.out.println(hour+" --> TEMPERATURA: "+temp);
                System.out.println(hour+" --> HUMIDITY: "+humidity);
                System.out.println(hour+" --> PRESSURE: "+press);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return inflater.inflate(R.layout.fragment_consulta_tiempo, container, false);
    }
}
