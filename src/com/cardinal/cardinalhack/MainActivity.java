package com.cardinal.cardinalhack;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements ListView.OnItemClickListener {
	private String[] mNavItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private FragmentManager mFM;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // TODO replace menu icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_closed)
        {
        	public void onDrawerClosed(View view) {
        		
        	}
        	
        	public void onDrawerOpened(View drawerView) {
        		
        	}
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        
        mNavItems = getResources().getStringArray(R.array.nav_items);
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        mDrawerLayout.closeDrawers();
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
}
