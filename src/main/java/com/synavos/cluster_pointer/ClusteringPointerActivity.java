package com.synavos.cluster_pointer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.synavos.mapsv1.BaseMapsActivity;
import com.synavos.mapsv1.R;

import org.json.JSONException;

import java.util.List;

import static com.synavos.utils.constant.cluster_items;

public class ClusteringPointerActivity extends BaseMapsActivity {

    private ClusterManager<ClusterItem> mClusterManager;

    @Override
    protected void startDemo(boolean isRestore) {


        mClusterManager = new ClusterManager<>(this, getMap());
        getMap().setOnCameraIdleListener(mClusterManager);

        mClusterManager.getMarkerCollection().setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                final LayoutInflater inflater = LayoutInflater.from(ClusteringPointerActivity.this);
                final View view = inflater.inflate(R.layout.custom_info_window, null);
                final TextView textView = view.findViewById(R.id.textViewTitle);
                String text = (marker.getTitle() != null) ? marker.getTitle() : "Cluster Item";
                textView.setText(text);
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(ClusteringPointerActivity.this,
                        "Info window clicked.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        loadIntentData();
    }

    private void loadIntentData(){
        Intent intent = getIntent();

        if(intent != null){
            intent.hasExtra(cluster_items);
            List<ClusterItem> items =  intent.getParcelableExtra(cluster_items);
            readItems(items);
        }
    }

    private void readItems(List<ClusterItem> items)  {
        mClusterManager.addItems(items);
        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(items.get(0).getPosition(), 9.0f));

    }
}
