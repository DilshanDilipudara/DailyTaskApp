package com.example.dailytaskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

   private  Button btn_senddata;
   private EditText text;

   private Firebase reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        reference = new Firebase("https://daily-task-app-2a8f7.firebaseio.com/");

        btn_senddata = (Button) findViewById(R.id.btnsend);
        text = (EditText) findViewById(R.id.edittext);

        btn_senddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String task = text.getText().toString().trim();
               String id =  reference.push().getKey();

                if (!TextUtils.isEmpty(task)) {
                    reference.child(id).setValue(task);
                    Toast.makeText(MainActivity.this, "Sucussfully", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "pleas enter the task", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
