package com.cardinal.cardinalhack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements OnClickListener {
	private Button toggle1;
	private Button toggle2;
	private Button toggle3;
	private Button toggle4;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.main_fragment, container, false);
		toggle1 = (Button) rootView.findViewById(R.id.toggleButton1);
		toggle1.setOnClickListener(this);
		toggle2 = (Button) rootView.findViewById(R.id.toggleButton2);
		toggle2.setOnClickListener(this);
		toggle3 = (Button) rootView.findViewById(R.id.toggleButton3);
		toggle3.setOnClickListener(this);
		toggle4 = (Button) rootView.findViewById(R.id.toggleButton4);
		toggle4.setOnClickListener(this);
		return rootView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		Button button = (Button)v;
		button.setSelected(!button.isSelected());
	}
	
}
