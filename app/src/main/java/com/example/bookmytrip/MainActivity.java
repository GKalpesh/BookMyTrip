package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmytrip.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Btn_Login;
    private int counter = 5;
    private TextView userregistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    private String name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Info = (TextView)findViewById(R.id.count);
        Btn_Login =(Button)findViewById(R.id.btn_Login);
        userregistration = (TextView)findViewById(R.id.register);
        forgotPassword = (TextView)findViewById(R.id.forgot_password);

        Info.setText("No of attempt Remaining: 5  ");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            finish();
            startActivity(new Intent(MainActivity.this, HomeScreen.class));
        }

        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(),Password.getText().toString());

            }
        });

        userregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Registration_Activity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PasswordActivity.class));
            }
        });
    }

    private void validate(String userName,String userPassword){

        name = Username.getText().toString();
        pass = Password.getText().toString();



        if (name.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,"please enter all the details",Toast.LENGTH_SHORT).show();

        }else {

            progressDialog.setMessage("Loading....");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(userName , userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();

                        //Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        checkEmailVerification();
                        Log.i("button Clicked" ," Registration success");
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        counter --;
                        Info.setText("No of attempt Remaining:" +counter);
                        if (counter == 0){
                            Btn_Login.setEnabled(false);
                        }
                    }
                }
            });
        }
    }

    public void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();

        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this, HomeScreen.class));
        }
        else {
            Toast.makeText(MainActivity.this,"verify Your Email",Toast.LENGTH_SHORT);
            firebaseAuth.signOut();
        }
    }
}
