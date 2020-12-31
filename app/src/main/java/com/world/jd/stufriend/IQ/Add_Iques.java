package com.world.jd.stufriend.IQ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.world.jd.stufriend.IQ.DS.AddDS;
import com.world.jd.stufriend.R;

public class Add_Iques extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__iques);
    }

    public void sendAddDS(View view) {
        startActivity(new Intent(getApplicationContext(), AddDS.class));
    }
}
