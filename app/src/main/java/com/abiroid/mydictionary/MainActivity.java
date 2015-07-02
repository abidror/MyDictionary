package com.abiroid.mydictionary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu_three);
        ab.setDisplayHomeAsUpEnabled(true);

        nvDrawer = (NavigationView)findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
    }


    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);

                        return true;

                    }

                });

    }

    public void selectDrawerItem(MenuItem menuItem) {

        // Create a new fragment and specify the planet to show based on

        // position

        Fragment fragment = null;

        Class fragmentClass;

        switch(menuItem.getItemId()) {

            case R.id.nav_first_fragment:

                fragmentClass = FirstFragment.class;

                break;

            case R.id.nav_second_fragment:

                fragmentClass = SecondFragment.class;

                break;

            case R.id.nav_third_fragment:

                fragmentClass = ThirdFragment.class;

                break;

            default:

                fragmentClass = FirstFragment.class;

        }



        try {

            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {

            e.printStackTrace();

        }




        //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();



        // Highlight the selected item, update the title, and close the drawer

        menuItem.setChecked(true);

        setTitle(menuItem.getTitle());

        mDrawer.closeDrawers();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        // Uncomment to inflate menu items to Action Bar

        // inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:

                mDrawer.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if( mDrawer.isDrawerOpen(Gravity.LEFT))
        {
            mDrawer.closeDrawer(Gravity.LEFT);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Close App");
            builder.setMessage("Are you sure to Exit ? ");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //super.onBackPressed();
                    finish();
                }
            });

            builder.setNegativeButton("No", null);
            builder.show();

        }
    }
}
