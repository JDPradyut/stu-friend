package com.world.jd.stufriend;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.world.jd.stufriend.HomeScreen.UserHome;

import static com.world.jd.stufriend.Theme.sharedPref;

public class Settings extends AppCompatActivity {

    TextView name;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = findViewById(R.id.name);
        circleImageView = findViewById(R.id.button2);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName());
            Uri photo = signInAccount.getPhotoUrl();

            Glide.with(this).load(String.valueOf(photo)).into(circleImageView);
        }
    }

    public void sendProfile(View view){
        Intent intent = new Intent(getApplicationContext(),Profile.class);
        startActivity(intent);
    }

    public void sendAbout(View view){
        Intent intent = new Intent(getApplicationContext(),About.class);
        startActivity(intent);
    }

    public void sendTheme(View view){
        Intent intent = new Intent(getApplicationContext(),Theme.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), UserHome.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
