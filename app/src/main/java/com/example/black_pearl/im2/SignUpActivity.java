package com.example.black_pearl.im2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {



    Button bsignup;
    EditText etname, etemail, etphone;
    int x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bsignup=(Button) findViewById(R.id.signup);
        etname=(EditText)findViewById(R.id.name);
        etphone=(EditText)findViewById(R.id.phonenumber);
        etemail=(EditText) findViewById(R.id.emailid);


        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                x = isEmpty(etname);
                y = isEmpty(etphone);
                z = isEmpty(etemail);

                if(x==0 || y==0 || z==0){
                    Toast.makeText(SignUpActivity.this,"Error!! \nEnter correct details.",Toast.LENGTH_SHORT).show();

                }

                else{




                    Intent in= new Intent(SignUpActivity.this,otpActivity.class);

                    in.putExtra("phonenumber","+91"+etphone.getText().toString());
                    startActivity(in);


                }



            }
        });

    }

    private int isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return 1;

        else  return 0;
    }

}
