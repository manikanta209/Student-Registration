package com.example.studentregistration.Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentregistration.MainActivity;
import com.example.studentregistration.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText edUsername,edAge,edEmail,edPassword;
    Button buttonRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername=findViewById(R.id.username);
        edAge=findViewById(R.id.age);
        edEmail=findViewById(R.id.emailAdress);
        edPassword=findViewById(R.id.password);
        buttonRegister=findViewById(R.id.btnRegisterUser);
        buttonRegister.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegisterUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String username=edUsername.getText().toString();
        String age=edAge.getText().toString();
        String password=edPassword.getText().toString();
        String email=edEmail.getText().toString();


        if (username.isEmpty()) {
            edUsername.setError("Username is Required");
            edUsername.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            edAge.setError("Age is Required");
            edAge.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            edEmail.setError("Email is Required");
            edEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Email is Required");
            edEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edPassword.setError("Password is Required");
            edPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edPassword.setError("Min password length is 6 characters");
            edPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if (task.isSuccessful()){
               startActivity(new Intent(Register.this, MainActivity.class));
               finish();
           }
           else {
               Toast.makeText(Register.this,"Failed to register..!",Toast.LENGTH_LONG).show();
           }
            }
        });
    }
}