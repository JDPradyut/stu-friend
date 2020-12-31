package com.world.jd.stufriend.HomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.world.jd.stufriend.CP.CProbList;
import com.world.jd.stufriend.FJ.FJList;
import com.world.jd.stufriend.FeedBack;
import com.world.jd.stufriend.Fragment.ForumF;
import com.world.jd.stufriend.Fragment.HomeF;
import com.world.jd.stufriend.Fragment.InternF;
import com.world.jd.stufriend.Fragment.JobsF;
import com.world.jd.stufriend.IQ.ViewIQues;
import com.world.jd.stufriend.R;
import com.world.jd.stufriend.Settings;
import com.world.jd.stufriend.SharedPref;

import static com.world.jd.stufriend.Theme.sharedPref;


public class UserHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    CircleImageView circleImageView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        bottomNavigationView = findViewById(R.id.bnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fcont,new HomeF()).commit();



        /*---------------------Hooks------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        menu = navigationView.getMenu();

        circleImageView = findViewById(R.id.button2);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Uri photo = signInAccount.getPhotoUrl();

            Glide.with(this).load(String.valueOf(photo)).into(circleImageView);
        }



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);



    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_bus:
                Intent intent = new Intent(UserHome.this, CProbList.class);
                startActivity(intent);
                break;
            case R.id.nav_cycle:
                Intent intent1 = new Intent(UserHome.this, FJList.class);
                startActivity(intent1);
                break;

            case R.id.nav_plane:
                Intent intent2 = new Intent(UserHome.this, ViewIQues.class);
                startActivity(intent2);
                break;


            case R.id.nav_rate:
                Intent intent3 = new Intent(UserHome.this, FeedBack.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START); return true;
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment sFrag = null;

            switch (menuItem.getItemId()) {
                case R.id.bhome:
                    sFrag = new HomeF();
                    break;
                case R.id.bforum:
                    sFrag = new ForumF();
                    break;
                case R.id.bjobs:
                    sFrag = new JobsF();
                    break;
                case R.id.binterns:
                    sFrag = new InternF();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fcont,sFrag).commit();
            return true;
        }
    };

    public void sendSetting(View view){
        Intent intent = new Intent(getApplicationContext(),Settings.class);
        startActivity(intent);
    }
}
