package com.cardinal.cardinalhack;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommandConsoleFragment extends Fragment {
	private static final String TAG = CommandConsoleFragment.class.getSimpleName();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView()");
		View rootView = inflater.inflate(R.layout.command_console_fragment, container, false);
		return rootView;
	}
}
