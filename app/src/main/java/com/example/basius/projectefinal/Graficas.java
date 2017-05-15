package com.example.basius.projectefinal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class Graficas extends Fragment {
    //http://www.android-graphview.org/
    //http://www.android-graphview.org/simple-graph/
    //https://github.com/appsthatmatter/GraphView
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
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 2),
                new DataPoint(1, 6),
                new DataPoint(2, 8),
                new DataPoint(3, 10),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
        graph.addSeries(series2);
        return view;
    }
}
