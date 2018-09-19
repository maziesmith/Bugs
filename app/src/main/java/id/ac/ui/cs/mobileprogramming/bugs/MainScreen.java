package id.ac.ui.cs.mobileprogramming.bugs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.bugs.fragment.AboutFragment;
import id.ac.ui.cs.mobileprogramming.bugs.fragment.LocationFragment;
import id.ac.ui.cs.mobileprogramming.bugs.fragment.MonitorFragment;

public class MainScreen extends AppCompatActivity {

    private DrawerLayout drawer;
    private FragmentManager fragmentManager;
    List<Fragment> fragments;
    MonitorFragment monitor;
    LocationFragment location;
    AboutFragment about;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView nav = findViewById(R.id.nav_view);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawer.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_monitor:
                        setMonitorScreen();
                        break;
                    case R.id.nav_location:
                        setLocationScreen();
                        break;
                    case R.id.nav_about:
                        setAboutScreen();
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_hamburger);
        }

        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        setMonitorScreen();
    }

    private void clearFragments() {
        for (Fragment fragment : fragments) {
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragments.remove(fragment);
        }
    }

    private void setMonitorScreen() {
        clearFragments();
        monitor = new MonitorFragment();
        fragments.add(monitor);
        fragmentManager.beginTransaction().add(R.id.fragment_container, monitor).commit();
    }

    private void setLocationScreen() {
        clearFragments();
        location = new LocationFragment();
        fragments.add(location);
        fragmentManager.beginTransaction().add(R.id.fragment_container, location).commit();
    }

    private void setAboutScreen() {
        clearFragments();
        about = new AboutFragment();
        fragments.add(about);
        fragmentManager.beginTransaction().add(R.id.fragment_container, about).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
