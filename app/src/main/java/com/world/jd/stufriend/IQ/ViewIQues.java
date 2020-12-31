package com.world.jd.stufriend.IQ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.world.jd.stufriend.Course.ViewCourse;
import com.world.jd.stufriend.IQ.DS.DSList;
import com.world.jd.stufriend.R;
import com.world.jd.stufriend.SharedPref;

import static com.world.jd.stufriend.Theme.sharedPref;

public class ViewIQues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_i_ques);

        Button button4 = (Button) findViewById(R.id.ViewDS);
        button4.setBackgroundResource(R.drawable.data);
    }

    public void sendDS(View view){
        startActivity(new Intent(getApplicationContext(), DSList.class));
    }
}
