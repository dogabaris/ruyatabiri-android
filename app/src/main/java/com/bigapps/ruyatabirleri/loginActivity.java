package com.bigapps.ruyatabirleri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class loginActivity extends AppCompatActivity {

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

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        if (btnLogin != null) {
            btnLogin.setOnClickListener(Login);
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
            final EditText etEmail = (EditText) findViewById(R.id.etEmailLogin);
            final EditText etPassword = (EditText) findViewById(R.id.etPasswordLogin);

            final pojoUser user = new pojoUser();
            user.setEmail(etEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());

            if (etEmail.getText().toString().matches("") || etPassword.getText().toString().matches("")) {

                Toast.makeText(loginActivity.this, "Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show();

            }else if(Global.isValidEmail(etEmail.getText().toString())) {

                Global.getService().Login(user, new Callback<pojoLogin>() {

                    @Override
                    public void success(pojoLogin pojoLogin, Response response) {
                        Intent intent = new Intent(getBaseContext(), timelineActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(loginActivity.this, "Kayıt olunamadı. Tekrar deneyin!", Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                Toast.makeText(loginActivity.this, "Mail düzgün girilmedi!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
