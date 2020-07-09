package com.synavos.api;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.google.maps.android.clustering.ClusterItem;
import com.synavos.cluster_pointer.ClusteringPointerActivity;

import java.util.List;

import static com.synavos.utils.constant.cluster_items;

public class MapsHelper {

    public MapsHelper(Context context , List<ClusterItem> items){
        Intent intent = new Intent(context, ClusteringPointerActivity.class);
        intent.putExtra(cluster_items, (Parcelable) items);
        context.startActivity(intent);
    }
}
