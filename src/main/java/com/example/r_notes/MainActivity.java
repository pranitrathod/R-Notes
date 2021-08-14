package com.example.r_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
Button Rbutn;
EditText Rtxt1,Rtxt2;
DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rtxt1=(EditText)findViewById(R.id.Title);
        Rtxt2=(EditText)findViewById(R.id.e1);
        Rbutn=(Button)findViewById(R.id.butn);

        //To Hide the Action bar from the activity
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        ref=FirebaseDatabase.getInstance().getReference().child("CHARANSING RATHOD");
        Rbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1=Rtxt1.getText().toString();
                String t2=Rtxt2.getText().toString();
                HashMap<String,Object> saveCloud=new HashMap<>();
                if(t1.isEmpty())
                {
                    Rtxt1.setError("Please enter title");

                }
                if (t2.isEmpty())
                {
                    Rtxt2.setError("Please enter details");


                }
                if(!t1.isEmpty()&&!t2.isEmpty()){
                saveCloud.put("Title",Rtxt1.getText().toString());
                saveCloud.put("Details",Rtxt2.getText().toString());
                ref.push().setValue(saveCloud);
                Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            Rtxt1.setText("");
            Rtxt2.setText("");}
            }
        });

    }
}
