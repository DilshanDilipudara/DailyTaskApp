package com.example.dailytaskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

   private  Button btn_senddata;
   private EditText text,idtext;
   private ListView list;
   private final ArrayList<String> showdata = new ArrayList<>();

   private Firebase reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        reference = new Firebase("https://daily-task-app-2a8f7.firebaseio.com/");

        btn_senddata = (Button) findViewById(R.id.btnsend);
        text = (EditText) findViewById(R.id.edittext);
        list = (ListView) findViewById(R.id.list_item);
        idtext  = (EditText) findViewById(R.id.valuetext);



        btn_senddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String task = text.getText().toString().trim();
               String id =  idtext.getText().toString().trim();

                if (!TextUtils.isEmpty(task)) {

                    Firebase childref = reference.child(id);
                    childref.setValue(task);

                    Toast.makeText(MainActivity.this, "Sucussfully", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "pleas enter the task", Toast.LENGTH_SHORT).show();
                }


                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,showdata);
                list.setAdapter(arrayAdapter);

                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String value = dataSnapshot.getValue(String.class);
                        showdata.add(value);
                        arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });



            }
        });





    }
}
