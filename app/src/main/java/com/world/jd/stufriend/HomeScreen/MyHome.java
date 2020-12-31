package com.world.jd.stufriend.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.world.jd.stufriend.Login;
import com.world.jd.stufriend.OnBoarding;
import com.world.jd.stufriend.R;
import com.world.jd.stufriend.SplashScreen;

public class MyHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
    }

    public void sendAdmin(View view) {
        startActivity(new Intent(getApplicationContext(),AdminHome.class));
    }
    public void sendUser(View view) {
        startActivity(new Intent(getApplicationContext(), SplashScreen.class));
    }
}
