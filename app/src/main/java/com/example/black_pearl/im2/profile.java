package com.example.black_pearl.im2;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity {


    TextView username,mail,phonnmbr;
    Button logout;
    FloatingActionButton editfab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");


        username  = (TextView) findViewById(R.id.userprofilename);
        mail      = (TextView) findViewById(R.id.emailprofile);
        phonnmbr  = (TextView) findViewById(R.id.phonenumberprofile);
        logout    = (Button) findViewById(R.id.logout);
        editfab   = (FloatingActionButton) findViewById(R.id.editfab);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // logout id
            }
        });


        editfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // edit profile
            }
        });
    }
}
