package com.bigapps.ruyatabirleri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shadyfade on 13.09.2016.
 */
public class registerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        Button btnSubmit = (Button) findViewById(R.id.btnRegister);
        if (btnSubmit != null) {
            btnSubmit.setOnClickListener(Submit);
        }


    }

    View.OnClickListener Submit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final EditText etEmail = (EditText) findViewById(R.id.etEmailRegister);
            final EditText etPassword = (EditText) findViewById(R.id.etPasswordRegister);

            final pojoUser user = new pojoUser();
            user.setEmail(etEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());

            if (etEmail.getText().toString().matches("") || etPassword.getText().toString().matches("")) {

                Toast.makeText(registerActivity.this, "Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show();

            }else if(Global.isValidEmail(etEmail.getText().toString())) {

                Global.getService().Register(user, new Callback<pojoRegister>() {
                    @Override
                    public void success(pojoRegister beanRegister, Response response) {
                        Intent intent = new Intent(getBaseContext(), loginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (error.getResponse() != null) {
                            RestError body = (RestError) error.getBodyAs(RestError.class);
                            switch (error.getResponse().getStatus()) {
                                case 400://yanlış girilen bilgiler
                                    Toast.makeText(registerActivity.this, body.errorDetails, Toast.LENGTH_SHORT).show();
                                case 404://kayıtlı olmayan kullanıcı
                                    Toast.makeText(registerActivity.this,  "Böyle bir kayıtlı kullanıcı yok!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }else{
                Toast.makeText(registerActivity.this, "Mail düzgün girilmedi!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
