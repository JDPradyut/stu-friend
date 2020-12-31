package com.world.jd.stufriend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.world.jd.stufriend.Theme.sharedPref;

public class ViewNews extends AppCompatActivity {
    TextView textViewTitle,textViewDesc;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        textViewTitle=findViewById(R.id.list_title);
        textViewDesc=findViewById(R.id.list_desc);
        reference= FirebaseDatabase.getInstance().getReference().child("SFNews");


        String key=getIntent().getStringExtra("id");

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Title=dataSnapshot.child("title").getValue().toString();
                String Desc=dataSnapshot.child("desc").getValue().toString();

                textViewTitle.setText(Title);
                textViewDesc.setText(Desc);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
