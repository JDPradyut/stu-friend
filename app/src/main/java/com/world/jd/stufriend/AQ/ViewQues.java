package com.world.jd.stufriend.AQ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.world.jd.stufriend.AQ.TCS.TCSQuesList;
import com.world.jd.stufriend.R;
import com.world.jd.stufriend.SharedPref;

import static com.world.jd.stufriend.Theme.sharedPref;

public class ViewQues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ques);

        Button button4 = (Button) findViewById(R.id.ViewTCS);
        button4.setBackgroundResource(R.drawable.tata);
    }
    public void sendTCS(View view){
        startActivity(new Intent(getApplicationContext(), TCSQuesList.class));
    }
}
