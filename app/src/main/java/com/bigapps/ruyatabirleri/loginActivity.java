package com.bigapps.ruyatabirleri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class loginActivity extends AppCompatActivity {
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private EditText etEmail,etPassword;
    private Boolean saveLogin;
    private CheckBox rememberCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        TextView regTv = (TextView) findViewById(R.id.tvRegister);
        TextView restoreTv = (TextView) findViewById(R.id.tvRestore);
        etEmail = (EditText) findViewById(R.id.etEmailLogin);
        etPassword = (EditText) findViewById(R.id.etPasswordLogin);
        rememberCb = (CheckBox) findViewById(R.id.checkboxRemember);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        saveLogin = loginPreferences.getBoolean("saveLogin",false);


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        if (btnLogin != null) {
            btnLogin.setOnClickListener(Login);
        }

        if (saveLogin)
        {
            rememberCb.setChecked(true);
            etEmail.setText (loginPreferences.getString("email", ""));
            etPassword.setText (loginPreferences.getString("password", ""));
        }else{
            rememberCb.setChecked(false);
            SharedPreferences.Editor loginPrefsEditor= getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("email", "");
            loginPrefsEditor.putString("password", "");
            loginPrefsEditor.apply();
        }



        regTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), registerActivity.class);
                startActivity(intent);
            }
        });

        restoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), restoreActivity.class);
                startActivity(intent);
            }
        });

    }

    View.OnClickListener Login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            etEmail = (EditText) findViewById(R.id.etEmailLogin);
            etPassword = (EditText) findViewById(R.id.etPasswordLogin);

            final pojoUser user = new pojoUser();
            user.setEmail(etEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());

            if (etEmail.getText().toString().matches("") || etPassword.getText().toString().matches("")) {

                Toast.makeText(loginActivity.this, "Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show();

            }else if(Global.isValidEmail(etEmail.getText().toString())) {

                Global.getService().Login(user, new Callback<pojoLogin>() {

                    @Override
                    public void success(pojoLogin pojoLogin, Response response) {
                        if(rememberCb.isChecked()){
                            loginPrefsEditor= getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("email", etEmail.getText().toString());
                            loginPrefsEditor.putString("password", etPassword.getText().toString());
                            loginPrefsEditor.apply();
                        }else{
                            loginPrefsEditor= getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                            loginPrefsEditor.putBoolean("saveLogin", false);
                            loginPrefsEditor.apply();
                        }
                        finish();
                        Intent intent = new Intent(getBaseContext(), timelineActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (error.getResponse() != null) {
                            RestError body = (RestError) error.getBodyAs(RestError.class);
                            switch (error.getResponse().getStatus()) {
                                case 400://yanlış girilen bilgiler
                                    Toast.makeText(loginActivity.this, body.errorDetails, Toast.LENGTH_SHORT).show();
                                case 404://kayıtlı olmayan kullanıcı
                                    Toast.makeText(loginActivity.this,  "Böyle bir kayıtlı kullanıcı yok!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            }else{
                Toast.makeText(loginActivity.this, "Mail düzgün girilmedi!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
