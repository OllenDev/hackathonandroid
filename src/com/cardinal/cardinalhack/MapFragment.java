package com.cardinal.cardinalhack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.map_fragment, container, false);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        GoogleMap map = mapFragment.getMap();
        
        LatLng packardPlace = new LatLng(35.227267, -80.846474); // GPS cords of Packard Place
        map.addMarker(new MarkerOptions().position(packardPlace)
                .title("Packard Place"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(packardPlace, 10));
        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
	}
	
}
