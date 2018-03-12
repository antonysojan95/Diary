package com.voyager.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.voyager.diary.Helper.Common;
import com.voyager.diary.Helper.DBHandler;
import com.voyager.diary.Helper.UserDetails;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 08-Mar-18.
 */

public class SignInPage extends AppCompatActivity  {

    private EditText edtEmailPhno;
    private EditText edtPswd;
    private Button btnSubmit;

    private ProgressBar progressBar;
    String emailAdd;
    String pswd;
    DBHandler db;
    List<UserDetails> userDetailsList;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        //find view
        edtEmailPhno = (EditText) this.findViewById(R.id.edtEmailPhno);
        edtPswd = (EditText) this.findViewById(R.id.edtPswd);
        btnSubmit = (Button) this.findViewById(R.id.btnSubmit);
        progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        //db = new DBHandler(this);
        //userDetailsList = db.getAllUsers();
        sharedPrefs = getSharedPreferences(Common.UserDetailsList,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        userDetailsList = getUserGsonInSharedPrefrences();
    }

    public List<UserDetails> getUserGsonInSharedPrefrences(){
        Gson gson = new Gson();
        String json = sharedPrefs.getString("UserDetailsList", null);
        List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
        Type type = new TypeToken<List<UserDetails>>() {}.getType();
        if(userDetailsList!=null){
            userDetailsList = gson.fromJson(json, type);
            System.out.println("--------- SplashPresenter getUserGsonInSharedPrefrences"+userDetailsList);
        }
        return userDetailsList;
    }

    public void btnSubmit(View v){
        emailAdd = edtEmailPhno.getText().toString();
        pswd = edtPswd.getText().toString();
        if(userDetailsList!=null) {
            for (UserDetails userDetails : userDetailsList) {
                String log = "Id: " + userDetails.getUserID() + " ,Email: " + userDetails.getEmail() + " ,Passwd: " + userDetails.getPswd();
                // Writing shops to log
                Log.d("UserName: : ", log);
                if(userDetails.getEmail().equals(emailAdd) && userDetails.getPswd().equals(pswd)) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "Please Register to login", Toast.LENGTH_SHORT).show();
                }

            }
        }else {
            Toast.makeText(this, "Please Register to login", Toast.LENGTH_SHORT).show();
        }
    }

    public void SignUp(View v){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
        finish();
    }

}