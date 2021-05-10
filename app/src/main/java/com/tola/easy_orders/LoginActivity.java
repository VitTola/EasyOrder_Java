package com.tola.easy_orders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_etEmail);
        password = findViewById(R.id.login_etPassword);
        login = findViewById(R.id.login_btnLogin);
        register = findViewById(R.id.login_btnRegister);
        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email = email.getText().toString();
                String text_password = password.getText().toString();
                if(text_email.isEmpty()){
                    email.setError("Please enter email address!");
                    return;
                }
                else if(text_password.isEmpty()){
                    password.setError("Please enter password!");
                    return;
                }

                logInUser(text_email,text_password);
            }

            private void logInUser(String text_email, String text_password) {
                auth.signInWithEmailAndPassword(text_email,text_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Toast.makeText(LoginActivity.this,"Login successful!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }
                });
            }
        });


    }
}