package com.world.jd.stufriend.AQ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.world.jd.stufriend.AQ.TCS.AddTCSQues;
import com.world.jd.stufriend.R;

public class AddQues extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ques);
    }

    public void sendTCSQues(View view) {
        startActivity(new Intent(getApplicationContext(), AddTCSQues.class));
    }
}
