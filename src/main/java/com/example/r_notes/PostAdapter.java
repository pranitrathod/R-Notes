package com.example.r_notes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class PostAdapter extends FirebaseRecyclerAdapter<Post, PostAdapter.PostViewHolder> {

     private Context context;
    public PostAdapter(@NonNull FirebaseRecyclerOptions<Post> options, Context context) {
        super(options);
        this.context=context;


    }



    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, final int position, @NonNull final Post model) {
//to show on the screen(Recycleview list very important) we use this method
        holder.Details.setText(model.getDetails());
        holder.Title.setText(model.getTitle());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //From GOOGLE
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.content))

                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();


                //this method(DOWN METHOD) is from google so that
                // i can use child of dialog box aare vohi re edit text (Title,description)

                View holderView1 =(LinearLayout)dialog.getHolderView();
                final EditText title=holderView1.findViewById(R.id.Title);
                final EditText details=holderView1.findViewById(R.id.Details);
                Button update=holderView1.findViewById(R.id.update);

                //setting value to Dialog box

                title.setText(model.getTitle());
                details.setText(model.getDetails());


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //this Map<K,V> as Key is always STRING and Values can be anything isliye OBJECT
                        //After MAP set go and put it on updateChildren()

                        Map<String,Object> map =new HashMap<>();


                        map.put("Title",title.getText().toString());

                        map.put("Details",details.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("CHARANSING RATHOD").child(getRef(position).getKey())

                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override

                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context,"Updated Successfully",Toast.LENGTH_LONG).show();

                                dialog.dismiss();
                            }
                        });

                    }
                });



                dialog.show();

            }
        });

        //Deleting Items
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertBox=new AlertDialog.Builder(context);
                alertBox.setMessage("Are you sure to delete??");
                alertBox.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("CHARANSING RATHOD").child(getRef(position).getKey())
                                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });

                    }
                });
                alertBox.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertBox.create();
                alertBox.show();

            }
        });

    }


    @NonNull
    @Override

    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);

        return new PostViewHolder(view);
    }


    //This is Inner Class [IMPORTENT OK]
    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView Details;//Reference to display
        TextView Title;


        ImageView edit,delete;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.Title);
            Details=itemView.findViewById(R.id.Details);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}
