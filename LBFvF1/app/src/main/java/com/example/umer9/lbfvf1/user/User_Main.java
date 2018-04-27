package com.example.umer9.lbfvf1.user;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umer9.lbfvf1.R;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.HashMap;

public class User_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        AsyncResponse, com.kosalgeek.genasync12.AsyncResponse {

    TextView searchView;
    String searchedText;
    public String username_string;
    public String password_string;
    Button btn_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        searchView = (TextView)findViewById(R.id.searchView);
        Button search=(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedText = searchView.getText().toString();

                    if (searchedText != null && !searchedText.isEmpty()) {

                        Intent intent = new Intent(User_Main.this, ListActivity.class);
                        //Intent intent = new Intent(User_Main.this, Home.class);
                        intent.putExtra("searchThis", searchedText);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(User_Main.this, "Please Enter a valid string", Toast.LENGTH_LONG).show();
            }
        });

        String value;
        Bundle bundle = getIntent().getExtras();
        username_string = /* "a@b.com"; // */ bundle.getString("username");
        password_string = /* "123"; // */ bundle.getString("password");

        //Toast.makeText(this, username_string+ " "+password_string ,Toast.LENGTH_LONG).show();

            HashMap postData = new HashMap();
            postData.put("mobile" , "android");
            postData.put("email" , username_string);
            postData.put("pass" , password_string);
            PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
            com.kosalgeek.genasync12.PostResponseAsyncTask taskRead = new com.kosalgeek.genasync12.PostResponseAsyncTask(User_Main.this, this);
            task.execute("http://192.168.2.64/lbf_fyp/api/profile_b.php");
        //}

        btn_profile=(Button)findViewById(R.id.btn_status);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Main.this,  Profile.class);
                intent.putExtra("username",username_string);
                //intent.putExtra("password", password_string);
                startActivity(intent);
            }
        });
    }

    @Override
    public void processFinish(String s) {

       // Toast.makeText(this, s,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.user__main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}