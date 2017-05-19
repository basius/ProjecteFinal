package com.example.basius.projectefinal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Graficas extends Fragment {
    public WebView webViewGrafica;
    //FIREBASE
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referencia;
    public Graficas() {
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
        View view = inflater.inflate(R.layout.fragment_graficas, container, false);
        webViewGrafica = (WebView) view.findViewById(R.id.webViewGrafica);
        webViewGrafica.loadUrl("https://estaciometeo-73e65.firebaseapp.com/stadistics22.html");
        return view;
    }

}
