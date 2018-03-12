package com.voyager.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterPage extends AppCompatActivity {

    private EditText edtFullName;
    private EditText edtPassword;
    private EditText edtEmailAddress;
    private EditText edtRetypePassword;
    private EditText txtViewPhoneNo;
    private EditText edtCity;
    private Button btnRegister;
    DBHandler db;
    List<UserDetails> userDetailsList;
    UserDetails userDetails;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        edtFullName = (EditText) this.findViewById(R.id.edtFullName);
        edtPassword = (EditText) this.findViewById(R.id.edtPassword);
        edtRetypePassword = (EditText) this.findViewById(R.id.edtRetypePassword);
        edtEmailAddress = (EditText) this.findViewById(R.id.edtEmailAddress);
        txtViewPhoneNo = (EditText) this.findViewById(R.id.txtViewPhoneNo);
        edtCity = (EditText) this.findViewById(R.id.edtCity);
        btnRegister = (Button) this.findViewById(R.id.btnRegister);
        userDetails = new UserDetails();
        //db = new DBHandler(this);
        sharedPrefs = getSharedPreferences(Common.UserDetailsList,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        userDetailsList = getUserGsonInSharedPrefrences();
    }

    private void addUserGsonInSharedPrefrences(List<UserDetails> userDetailsList){
        Gson gson = new Gson();
        String jsonString = gson.toJson(userDetailsList);
        //UserModel user1 = gson.fromJson(jsonString,UserModel.class);
        if(jsonString!=null) {
            editor.putString("UserDetailsList", jsonString);
            editor.commit();
            System.out.println("-----------sendRegisteredDataAndValidateResponse  userDetailsList"+jsonString);

        }

    }



    public List<UserDetails> getUserGsonInSharedPrefrences(){
        Gson gson = new Gson();
        String json = sharedPrefs.getString("UserDetailsList", null);
        List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
        Type type = new TypeToken<List<UserDetails>>() {}.getType();
        if(userDetailsList==null){
            userDetailsList = new ArrayList<UserDetails>();
        }else{
            userDetailsList = gson.fromJson(json, type);
            System.out.println("--------- SplashPresenter getUserGsonInSharedPrefrences"+userDetailsList);
        }
        return userDetailsList;
    }



    public void btnRegister(View v) {
        if(edtFullName.getText().toString()!=null&&
                edtRetypePassword.getText().toString()!=null&&
                edtPassword.getText().toString()!=null&&
                edtRetypePassword.getText().toString()!=null&&
                edtEmailAddress.getText().toString()!=null&&
                txtViewPhoneNo.getText().toString()!=null&&
                edtCity.getText().toString()!=null) {
            if(userDetailsList==null){
                userDetailsList = new ArrayList<>();
            }
            userDetails.setFName(edtFullName.getText().toString());
            userDetails.setPswd(edtPassword.getText().toString());
            userDetails.setEmail(edtEmailAddress.getText().toString());
            userDetails.setPhno(txtViewPhoneNo.getText().toString());
            userDetails.setCity(edtCity.getText().toString());
            userDetails.setCountry("India");
            userDetailsList.add(userDetails);
            if (edtRetypePassword.getText().toString().equals(edtPassword.getText().toString())) {
               // db.addUser(userDetailsList);
                addUserGsonInSharedPrefrences(userDetailsList);
                Toast.makeText(this, "Register Successful ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SignInPage.class);
                startActivity(intent);
                finish();
            } else {
                edtFullName.setText("");
                edtPassword.setText("");
                edtRetypePassword.setText("");
                edtEmailAddress.setText("");
                txtViewPhoneNo.setText("");
                edtCity.setText("");
                Toast.makeText(this, "Pls Type the Same Password", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Pls fill all the fields ", Toast.LENGTH_SHORT).show();
        }

    }

}