package id.ac.ui.cs.mobileprogramming.bugs;

import android.content.pm.PackageManager;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.ui.cs.mobileprogramming.bugs.core.NetworkData;
import id.ac.ui.cs.mobileprogramming.bugs.fragment.AboutFragment;
import id.ac.ui.cs.mobileprogramming.bugs.fragment.LocationFragment;
import id.ac.ui.cs.mobileprogramming.bugs.fragment.MonitorFragment;

public class MainScreen extends AppCompatActivity {

    @BindView(R2.id.drawer_layout) DrawerLayout drawer;
    @BindView(R2.id.nav_view) NavigationView nav;
    @BindView(R2.id.toolbar) Toolbar toolbar;

    private FragmentManager fragmentManager;
    List<Fragment> fragments;
    MonitorFragment monitor;
    LocationFragment location;
    AboutFragment about;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragments = new ArrayList<>();

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_hamburger);
        }

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

        setMonitorScreen();
    }

    private void clearFragments() {
        for (Fragment fragment : fragments) {
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragments.remove(fragment);
        }
    }

    private void setHeader(int resId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(resId);
        }
    }

    private void setMonitorScreen() {
        clearFragments();
        monitor = new MonitorFragment();
        fragments.add(monitor);
        fragmentManager.beginTransaction().add(R.id.fragment_container, monitor).commit();
        setHeader(R.string.nav_menu_monitor);
    }

    private void setLocationScreen() {
        clearFragments();
        location = new LocationFragment();
        fragments.add(location);
        fragmentManager.beginTransaction().add(R.id.fragment_container, location).commit();
        setHeader(R.string.nav_menu_location);
    }

    private void setAboutScreen() {
        clearFragments();
        about = new AboutFragment();
        fragments.add(about);
        fragmentManager.beginTransaction().add(R.id.fragment_container, about).commit();
        setHeader(R.string.nav_menu_about);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MonitorFragment.PHONE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String phoneNumber = NetworkData.getPhoneNumber(getApplicationContext());
                    ((TextView) findViewById(R.id.phone_phone_number)).setText(phoneNumber);
                } else {
                    Toast.makeText(getApplicationContext(), "You need to grant permission to continue", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            default:
                Toast.makeText(getApplicationContext(), "You need to grant permission to continue", Toast.LENGTH_SHORT).show();
        }
    }


}
