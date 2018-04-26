package com.example.umer9.lbfvf1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.umer9.lbfvf1.user.User_Main;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import io.fabric.sdk.android.Fabric;

import java.io.Serializable;
import java.util.HashMap;

public class Login_Two extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;
    public String forUserName, forPassword;
    public static Bundle mMyAppsBundle1 = new Bundle();
    public static Bundle mMyAppsBundle2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login__two);
        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login);
        btnLogin.setOnClickListener(this);

    }
    @Override
    public void processFinish(String result) {

        String word = result.substring(0, 7);
        if ("success".equals(word)){
            //Toast.makeText(this, "Success test text" ,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, User_Main.class);
            Bundle extras = new Bundle();
            intent.putExtra("profile_json", result );
            intent.putExtra("username", etUsername.getText().toString());
            intent.putExtra("password", etPassword.getText().toString());
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Fail test text" ,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        HashMap postData = new HashMap();

        postData.put("mobile" , "android");
        postData.put("email" , etUsername.getText().toString());
        postData.put("pass" , etPassword.getText().toString());
        PostResponseAsyncTask task = new PostResponseAsyncTask(this, postData);
        task.execute("http://192.168.2.64/lbf_fyp/api/login.php");

    }
}