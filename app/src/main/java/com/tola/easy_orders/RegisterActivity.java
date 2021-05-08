package com.tola.easy_orders;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

  private  EditText etEmail;
  private  EditText etPassword;
  private Button btnRegister;
  private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegisterTo);
        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email = etEmail.getText().toString();
                String text_password = etPassword.getText().toString();

                if(!ValidateControls.ValidateEamil(etEmail) | !ValidateControls.ValidatePassword(etPassword)){
                    return;
                }
                    registerUser(text_email, text_password);
            }

            private void registerUser(String text_email, String text_password) {
                auth.createUserWithEmailAndPassword(text_email,text_password).addOnCompleteListener(RegisterActivity.this
                        , new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Registering user successful!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Registration failed!",Toast.LENGTH_SHORT).show();
                                }



                            }
                        });
            }
        });

    }
}