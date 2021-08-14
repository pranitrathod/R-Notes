package com.example.r_notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;


import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.FirebaseDatabase;

public class PostListActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private  PostAdapter adapter;
private ImageView Rfbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        recyclerView=(RecyclerView)findViewById((R.id.recycler));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Rfbtn=(ImageView)findViewById(R.id.MainActivity);



        //To Hide the Action bar from the activity
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CHARANSING RATHOD"), Post.class)
                        //above method will query to FIREBASE and Build the query samja???
                        .build();

       adapter = new PostAdapter(options,this);
     //set the adapter by using reference of recyclerView

        recyclerView.setAdapter(adapter);



       //TO go to another Activity we use StartActivty method
        Rfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (PostListActivity.this,MainActivity.class));
            }
        });


    }

    //this OnStart() will start listening
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    //this onStop() will stop listening
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
