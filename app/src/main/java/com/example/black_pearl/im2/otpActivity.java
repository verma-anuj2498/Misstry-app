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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpActivity extends AppCompatActivity {



    boolean mVerificationInProgress = false;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;

    Button doneotp;
    EditText otpdgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        setTitle("Indian Mistrry");

        doneotp=(Button)findViewById(R.id.done);
        otpdgt=(EditText)findViewById(R.id.enterotp);


        String phnnmbr=getIntent().getStringExtra("phonenumber");




        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                //  Log.d(TAG, "onVerificationCompleted:" + credential);
                Toast.makeText(otpActivity.this,"Verification done",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                //Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(otpActivity.this,"onVerificationFailed",Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    Toast.makeText(otpActivity.this,"Invalid mobile number",Toast.LENGTH_SHORT).show();

                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                    Toast.makeText(otpActivity.this,"Quota over",Toast.LENGTH_SHORT).show();

                }

                // Show a message and update the UI
                // ...
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                // Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(otpActivity.this,"Verification Code Sent on Mobile ",Toast.LENGTH_SHORT).show();

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // ...
            }
        };


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phnnmbr,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                otpActivity.this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks




        doneotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if(otpdgt.getText().toString().trim().length()==0){

                    Toast.makeText(otpActivity.this,"Enter Correct OTP",Toast.LENGTH_SHORT).show();

                }

                else{

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otpdgt.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                    Intent in=new Intent(otpActivity.this,LoggedinUser.class);
                    startActivity(in);

                }*/

                Intent in=new Intent(otpActivity.this,LoggedinUser.class);
                startActivity(in);

            }
        });


    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");

                            Toast.makeText(otpActivity.this,"Verfication Done!!",Toast.LENGTH_SHORT).show();


                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(otpActivity.this,"Verification failed invalid code",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }
}
