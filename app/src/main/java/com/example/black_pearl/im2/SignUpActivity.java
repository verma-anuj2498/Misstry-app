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

public class SignUpActivity extends AppCompatActivity {

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;

    Button bsignup,verify;
    EditText etname, etemail, etphone,otpedittext;
    int x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bsignup=(Button) findViewById(R.id.signup);
        etname=(EditText)findViewById(R.id.name);
        etphone=(EditText)findViewById(R.id.phonenumber);
        etemail=(EditText) findViewById(R.id.emailid);


        otpedittext=(EditText)findViewById(R.id.otp_edittxt);
        verify=(Button)findViewById(R.id.verify_button);



        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = false;
                Toast.makeText(SignUpActivity.this,"Verification Complete",Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(SignUpActivity.this,"Verification Failed",Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Toast.makeText(SignUpActivity.this,"InValid Phone Number",Toast.LENGTH_SHORT).show();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(SignUpActivity.this,"Verification code has been send on your number",Toast.LENGTH_SHORT).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                bsignup.setVisibility(View.GONE);
                etname.setVisibility(View.GONE);
                etphone.setVisibility(View.GONE);
                etemail.setVisibility(View.GONE);
                otpedittext.setVisibility(View.VISIBLE);
                verify.setVisibility(View.VISIBLE);

                // ...
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
                            etphone.getText().toString(),
                            60,
                            java.util.concurrent.TimeUnit.SECONDS,
                            SignUpActivity.this,
                            mCallbacks);
                }



            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpedittext.getText().toString());
                // [END verify_with_code]
                signInWithPhoneAuthCredential(credential);
            }
        });

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

    private int isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return 1;

        else  return 0;
    }

}
