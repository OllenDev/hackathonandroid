package com.cardinal.cardinalhack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener {
	private String[] mNavItems;
	//private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private FragmentManager mFM;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mNavItems = getResources().getStringArray(R.array.nav_items);
       // mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.nav_row, mNavItems));
        mDrawerList.setOnItemClickListener(this);
        
        mFM = getSupportFragmentManager(); 
        FragmentTransaction ft = mFM.beginTransaction();
        MainFragment fragment = new MainFragment();
        ft.add(R.id.content_frame, fragment);
        ft.commit();
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (position == 0) {
	        FragmentTransaction ft = mFM.beginTransaction();
	        MainFragment fragment = new MainFragment();
	        ft.add(R.id.content_frame, fragment);
	        ft.commit();
		} else if (position == 1) {
	        FragmentTransaction ft = mFM.beginTransaction();
	        MapFragment fragment = new MapFragment();
	        ft.add(R.id.content_frame, fragment);
	        ft.commit();
		}
	} 
}
