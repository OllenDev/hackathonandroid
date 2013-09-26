package com.cardinal.cardinalhack;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
	private static final int NAV_MAP = 4;
	private static final int NAV_COMMAND_CONSOLE = 9;
	private static final int NAV_INFO = 11;
	private Typeface digiFont;
	private View rootView;
	private TextView hintBox1;
	private TextView hintBox2;
	private TextView hintBox3;
	private TextView binaryValue;
	private Button switchFor8;
	private Button switchFor4;
	private Button switchFor2;
	private Button switchFor1;
	private Button lightFor8;
	private Button lightFor4;
	private Button lightFor2;
	private Button lightFor1;
	private Button navButton;
	private Integer currentValue; // What the representation of the binary value
									// is

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView()");
		if (rootView == null) {
			Log.d(TAG, "root view was null");
			rootView = inflater.inflate(R.layout.main_fragment, container,
					false);
			hintBox1 = (TextView) rootView.findViewById(R.id.hintBox1);
			hintBox2 = (TextView) rootView.findViewById(R.id.hintBox2);
			hintBox3 = (TextView) rootView.findViewById(R.id.hintBox3);  
			binaryValue = (TextView) rootView.findViewById(R.id.binaryValue);
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
			navButton = (Button) rootView.findViewById(R.id.navButton);
			navButton.setOnClickListener(this);
		}

		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			currentValue = Integer.valueOf(savedInstanceState
					.getInt(KEY_CURRENT_VALUE));
			Log.d(TAG, "Restored current value = " + currentValue.toString());
			restore();
		} else if (currentValue != null && currentValue > 0) {
			restore();
		} else {
			currentValue = Integer.valueOf(0);
		}

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "onActivityCreated()");
		super.onActivityCreated(savedInstanceState);
		if (digiFont == null) {
			digiFont = Typeface.createFromAsset(this.getActivity().getAssets(), "ds_digi.ttf");  
			binaryValue.setTypeface(digiFont);
		}
	}

	@Override
	public void onResume() {
		Log.d(TAG, "onResume()");
		super.onResume();
		hintBox1.setSelected(true);
		hintBox2.setSelected(true);
		hintBox3.setSelected(true);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, "onSaveInstanceState()");
		outState.putInt(KEY_CURRENT_VALUE, currentValue);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick()");
		Button button = (Button) v;
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
		case R.id.navButton:
			handleNavClick();
		default:
			// Shouldn't get here
		}
		binaryValue.setText(currentValue.toString());
		shouldEnableNav();
	}

	private void handleNavClick() {
		Log.d(TAG, "handleNavClick()");

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		switch (currentValue) {
		case NAV_MAP:
			MapControlFragment mapFragment = new MapControlFragment();
			ft.replace(R.id.content_frame, mapFragment);
			ft.addToBackStack(MapControlFragment.class.getName());
			ft.commit();
			break;
		case NAV_COMMAND_CONSOLE:
			InfoFragment infoFragment = new InfoFragment();
			ft.replace(R.id.content_frame, infoFragment);
			ft.addToBackStack(InfoFragment.class.getName());
			ft.commit();
			break;
		case NAV_INFO:
			CommandConsoleFragment consoleFragment = new CommandConsoleFragment();
			ft.replace(R.id.content_frame, consoleFragment);
			ft.addToBackStack(CommandConsoleFragment.class.getName());
			ft.commit();
			break;
		default:
			// shouldn't get here
		}
	}

	private void shouldEnableNav() {
		Log.d(TAG, "shouldEnableNav()");
		switch (currentValue) {
		case NAV_MAP:
		case NAV_COMMAND_CONSOLE:
		case NAV_INFO:
			navButton.setEnabled(true);
			break;
		default:
			navButton.setEnabled(false);
		}
	}

	/**
	 * This Method will restore the light and switch positions after a restore
	 * from a savedInstanceState bundle
	 */
	private void restore() {
		Log.d(TAG, "restore()");
		binaryValue.setText(currentValue.toString());
		int value = currentValue.intValue();
		if ((value & 8) > 0) {
			switchFor8.setSelected(true);
			lightFor8.setBackgroundColor(Color.RED);
		}

		if ((value & 4) > 0) {
			switchFor4.setSelected(true);
			lightFor4.setBackgroundColor(Color.RED);
		}

		if ((value & 2) > 0) {
			switchFor2.setSelected(true);
			lightFor2.setBackgroundColor(Color.RED);
		}

		if ((value & 1) > 0) {
			switchFor1.setSelected(true);
			lightFor1.setBackgroundColor(Color.RED);
		}
		shouldEnableNav();
	}
}
