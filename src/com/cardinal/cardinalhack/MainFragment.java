package com.cardinal.cardinalhack;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Class implements a set of switches that represent binary numbers places.
 * "Turning on" the right number will unlock the a new area of the app.
 * 
 * @author ChrisOllenburg
 * 
 */
public class MainFragment extends Fragment implements OnClickListener {
	private static final String TAG = MainFragment.class.getSimpleName();
	private static final String KEY_CURRENT_VALUE = "KEY_CURRENT_VALUE";
	private Button switchFor8;
	private Button switchFor4;
	private Button switchFor2;
	private Button switchFor1;
	private Button lightFor8;
	private Button lightFor4;
	private Button lightFor2;
	private Button lightFor1;
	private Integer currentValue; // What the representation of the binary value is
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.main_fragment, container, false);
		switchFor8 = (Button) rootView.findViewById(R.id.place8Switch);
		switchFor8.setOnClickListener(this);
		switchFor4 = (Button) rootView.findViewById(R.id.place4Switch);
		switchFor4.setOnClickListener(this);
		switchFor2 = (Button) rootView.findViewById(R.id.place2Switch);
		switchFor2.setOnClickListener(this);
		switchFor1 = (Button) rootView.findViewById(R.id.place1Switch);
		switchFor1.setOnClickListener(this);
		lightFor8 = (Button) rootView.findViewById(R.id.place8Light);
		lightFor4 = (Button) rootView.findViewById(R.id.place4Light);
		lightFor2 = (Button) rootView.findViewById(R.id.place2Light);
		lightFor1 = (Button) rootView.findViewById(R.id.place1Light);
		
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			currentValue = Integer.valueOf(savedInstanceState.getInt(KEY_CURRENT_VALUE));
			Log.d(TAG, "Restored current value = " + currentValue.toString());
			restore();
		} else {
			currentValue = Integer.valueOf(0);
		}
		
		return rootView;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(KEY_CURRENT_VALUE, currentValue);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		Button button = (Button)v;
		button.setSelected(!button.isSelected());
		int id = v.getId();
		switch (id) {
		case R.id.place8Switch:
			if (button.isSelected()) {
				currentValue += 8;
				lightFor8.setBackgroundColor(Color.RED);
			} else {
				currentValue -= 8;
				lightFor8.setBackgroundColor(Color.WHITE);
			}
			break;
		case R.id.place4Switch:
			if (button.isSelected()) {
				currentValue += 4;
				lightFor4.setBackgroundColor(Color.RED);
			} else {
				currentValue -= 4;
				lightFor4.setBackgroundColor(Color.WHITE);
			}
			break;
		case R.id.place2Switch:
			if (button.isSelected()) {
				currentValue += 2;
				lightFor2.setBackgroundColor(Color.RED);
			} else {
				currentValue -= 2;
				lightFor2.setBackgroundColor(Color.WHITE);
			}
			break;
		case R.id.place1Switch:
			if (button.isSelected()) {
				currentValue += 1;
				lightFor1.setBackgroundColor(Color.RED);
			} else {
				currentValue -= 1;
				lightFor1.setBackgroundColor(Color.WHITE);
			}
			break;
		default:
			//Shouldn't get here
		}
		Log.d(TAG, "Current value of lights is " + currentValue.toString());
	}
	
	/**
	 * This Method will restore the light and switch positions after a restore
	 *  from a savedInstanceState bundle
	 */
	private void restore() {
		Log.d(TAG, "restore()");
		int value = currentValue.intValue();
		
		if ((value&8) > 0) {
			switchFor8.setSelected(true);
			lightFor8.setBackgroundColor(Color.RED);
		}
		
		if ((value&4) > 0) {
			switchFor4.setSelected(true);
			lightFor4.setBackgroundColor(Color.RED);
		}
		
		if ((value&2) > 0) {
			switchFor2.setSelected(true);
			lightFor2.setBackgroundColor(Color.RED);
		}
		
		if ((value&1) > 0) {
			switchFor1.setSelected(true);
			lightFor1.setBackgroundColor(Color.RED);
		}
	}
}
