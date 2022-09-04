package com.example.renthouses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home_layout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    SharedPrefManager sharedPrefManager;
    private Menu activityMenu;
    private MenuItem curMenuItem;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        sharedPrefManager = SharedPrefManager.getInstance(this);
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new Home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Menu nav_Menu = navigationView.getMenu();
        if ( sharedPrefManager.readString("userFlag","noValue").equals("true")) {
            nav_Menu.findItem(R.id.nav_post).setVisible(false);
            nav_Menu.findItem(R.id.nav_edit_list).setVisible(false);
            nav_Menu.findItem(R.id.nav_rental_history).setVisible(false);
            nav_Menu.findItem(R.id.nav_request).setVisible(false);
        }
        else {
            nav_Menu.findItem(R.id.nav_search).setVisible(false);
            nav_Menu.findItem(R.id.nav_rentalTenant_history).setVisible(false);
            nav_Menu.findItem(R.id.nav_approveRequest).setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new Home()).commit();

                break;
            case R.id.nav_search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new Search()).commit();
                    break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new profile()).commit();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(Home_layout.this,SignIn.class);
                Home_layout.this.startActivity(intent);
                finish();
                break;
            case R.id.nav_post:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new PostFragment()).commit();
                break;
            case R.id.nav_rental_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new RentalHistoryAgFragment()).commit();
                break;
            case R.id.nav_edit_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new EditProperityFragment()).commit();
                break;
            case R.id.nav_request:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new RequestFragment()).commit();
                break;
            case R.id.nav_approveRequest:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new ApproveRequestFragment()).commit();
                break;
            case R.id.nav_rentalTenant_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,new TenantHistoryFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}