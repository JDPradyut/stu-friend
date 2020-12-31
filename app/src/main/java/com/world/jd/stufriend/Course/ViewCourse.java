package com.world.jd.stufriend.Course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.world.jd.stufriend.R;
import com.world.jd.stufriend.SharedPref;

import static com.world.jd.stufriend.Theme.sharedPref;

public class ViewCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
    }
}
