package com.world.jd.stufriend.HomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.world.jd.stufriend.AQ.AddQues;
import com.world.jd.stufriend.AddNews;
import com.world.jd.stufriend.CP.Add_Cprob;
import com.world.jd.stufriend.FI.AddIntern;
import com.world.jd.stufriend.FJ.AddJobs;
import com.world.jd.stufriend.IQ.Add_Iques;
import com.world.jd.stufriend.MainActivity;
import com.world.jd.stufriend.R;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void sendMain(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void sendQues(View view) {
        startActivity(new Intent(getApplicationContext(), AddQues.class));
    }
    public void sendAIQ(View view) {
        startActivity(new Intent(getApplicationContext(), Add_Iques.class));
    }
    public void sendCP(View view) {
        startActivity(new Intent(getApplicationContext(), Add_Cprob.class));
    }
    public void sendFJ(View view) {
        startActivity(new Intent(getApplicationContext(), AddJobs.class));
    }
    public void sendFI(View view) {
        startActivity(new Intent(getApplicationContext(), AddIntern.class));
    }
    public void sendNews(View view) {
        startActivity(new Intent(getApplicationContext(), AddNews.class));
    }
}
