package com.example.studentregistration.Signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentregistration.AddStudentData;
import com.example.studentregistration.MainActivity;
import com.example.studentregistration.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    private FirebaseAuth mAuth;
    private Button buttonLoign, buttonreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailAdress);
        password = findViewById(R.id.password);
        buttonLoign = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        buttonreg = findViewById(R.id.btnRegref);


        buttonreg.setOnClickListener(this);
        buttonLoign.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                startLogin();
                break;
            case R.id.btnRegref:
                startActivity(new Intent(login.this, Register.class));
                break;
        }
    }

    private void startLogin() {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        if (userEmail.isEmpty()) {
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }

        if (userPassword.isEmpty()) {
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }

        if (userPassword.length() < 6) {
            password.setError("Min password length is 6 characters");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(login.this, AddStudentData.class));
                    finish();
//                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                    if (firebaseUser.isEmailVerified()) {
//                        startActivity(new Intent(login.this, MainActivity.class));
//                        finish();
//                    } else {
//                        firebaseUser.sendEmailVerification();
//                        Toast.makeText(login.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    Toast.makeText(login.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}