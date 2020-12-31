package com.world.jd.stufriend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.world.jd.stufriend.Fragment.ForumF;

import java.util.HashMap;
import java.util.Map;

import static com.world.jd.stufriend.Theme.sharedPref;

public class ViewForum extends AppCompatActivity {
    TextView textViewTitle,textViewDesc;
    public String key;
    DatabaseReference reference;
    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_forum);
        textViewTitle=findViewById(R.id.list_title);
        textViewDesc=findViewById(R.id.list_desc);
        reference= FirebaseDatabase.getInstance().getReference().child("Forum");


        key=getIntent().getStringExtra("id");
        editText = findViewById(R.id.getPost);
        button = findViewById(R.id.postF);
        recyclerView = findViewById(R.id.forumlist);
        final GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = editText.getText().toString();
                if (desc.matches(""))
                {
                    Toast.makeText(getBaseContext(), "Please Write Something",Toast.LENGTH_SHORT).show();
                    return;

                }
                else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(key).push();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", databaseReference.getKey());
                    map.put("title",signInAccount.getDisplayName());
                    map.put("desc", desc);

                    databaseReference.setValue(map);

                    Toast.makeText(getApplicationContext(),"Post Uploaded",Toast.LENGTH_SHORT).show();
                    editText.getText().clear();
                    return;

                }

            }

        });
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();




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
    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(key);

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(query, new SnapshotParser<Model>() {
                            @NonNull
                            @Override
                            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Model(snapshot.child("id").getValue().toString(),
                                        snapshot.child("title").getValue().toString(),
                                        snapshot.child("desc").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.comment_list_item, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Model model) {

                holder.setTxtTitle(model.getmTitle());
                holder.setTxtDesc(model.getmDesc());

            }

        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;
        public TextView txtDesc;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            txtTitle = itemView.findViewById(R.id.list_title);
            txtDesc = itemView.findViewById(R.id.list_desc);
            view=itemView;
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }


        public void setTxtDesc(String string) {
            txtDesc.setText(string);
        }
    }


}


