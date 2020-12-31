package com.world.jd.stufriend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewItems extends AppCompatActivity {

    TextView textViewTitle,textViewDesc;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        textViewTitle=findViewById(R.id.list_title);
        textViewDesc=findViewById(R.id.list_desc);

        final Button button = findViewById(R.id.ViewLinkBtn);




        reference= FirebaseDatabase.getInstance().getReference().child("posts");


        String key=getIntent().getStringExtra("id");

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Title=dataSnapshot.child("title").getValue().toString();
                String Desc=dataSnapshot.child("desc").getValue().toString();

                final String TLink=dataSnapshot.child("link").getValue().toString();

                textViewTitle.setText(Title);
                textViewDesc.setText(Desc);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),WebBrowser.class);
                        intent.putExtra("TLink",TLink);
                        startActivity(intent);
                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
