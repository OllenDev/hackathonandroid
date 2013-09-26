package com.cardinal.cardinalhack;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {
	private FragmentManager mFM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			mFM = getFragmentManager();
			FragmentTransaction ft = mFM.beginTransaction();
			MainFragment fragment = new MainFragment();
			ft.add(R.id.content_frame, fragment);
			ft.commit();
		}
	}
}
