package com.example.black_pearl.im2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {



    Button bsignup,verifyotp;
    EditText etname, etemail, etphone,otpeditText;
    int x,y,z;

    boolean mVerificationInProgress = false;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        bsignup=(Button) findViewById(R.id.signup);
        etname=(EditText)findViewById(R.id.name);
        etphone=(EditText)findViewById(R.id.phonenumber);
        etemail=(EditText) findViewById(R.id.emailid);

        mAuth=FirebaseAuth.getInstance();


        verifyotp=(Button)findViewById(R.id.verifyotp);
        otpeditText=(EditText)findViewById(R.id.otpedittext);



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Toast.makeText(SignUpActivity.this,"Verification done",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SignUpActivity.this,"onVerificationFailed",Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(SignUpActivity.this,"Invalid mobile number",Toast.LENGTH_SHORT).show();

                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(SignUpActivity.this,"Quota over",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(SignUpActivity.this,"Verification Code Sent on Mobile ",Toast.LENGTH_SHORT).show();

                mVerificationId = verificationId;
                mResendToken = token;
                bsignup.setVisibility(View.GONE);
                etname.setVisibility(View.GONE);
                etphone.setVisibility(View.GONE);
                etemail.setVisibility(View.GONE);
                verifyotp.setVisibility(View.VISIBLE);
                otpeditText.setVisibility(View.VISIBLE);



            }
        };
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

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            etphone.getText().toString(),        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            SignUpActivity.this,               // Activity (for callback binding)
                           mCallbacks);        // OnVerificationStateChangedCallbacks

                }

            }
        });


        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpeditText.getText().toString());
                // [END verify_with_code]
                signInWithPhoneAuthCredential(credential);
            }
        });



    }


    private int isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return 1;

        else  return 0;
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "signInWithCredential:success");
                            startActivity(new Intent(SignUpActivity.this,LoggedinUser.class));
                            Toast.makeText(SignUpActivity.this,"Verification Done",Toast.LENGTH_SHORT).show();
                            // ...
                        } else {
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(SignUpActivity.this,"Invalid Verification",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}
