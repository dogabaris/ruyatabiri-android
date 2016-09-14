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
public class kayitEkraniActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kayit_ekrani);
        getSupportActionBar().hide();

        Button btnSubmit = (Button) findViewById(R.id.btnRegister);
        if (btnSubmit != null) {
            btnSubmit.setOnClickListener(Submit);
        }


    }

    View.OnClickListener Submit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final EditText etEmail = (EditText) findViewById(R.id.etEmail);
            final EditText etPassword = (EditText) findViewById(R.id.etPassword);

            final User user = new User();
            user.setEmail(etEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());

            if (etEmail.getText().toString().matches("") || etPassword.getText().toString().matches("")) {

                Toast.makeText(kayitEkraniActivity.this, "Alanlar Boş Bırakılamaz!", Toast.LENGTH_SHORT).show();

            }else if(Global.isValidEmail(etEmail.getText().toString())) {

                Global.getService().Register(user, new Callback<PojoRegister>() {
                    @Override
                    public void success(PojoRegister beanRegister, Response response) {
                        Intent intent = new Intent(getBaseContext(), girisEkraniActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(kayitEkraniActivity.this, "Kayıt olunamadı. Tekrar deneyin!", Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                Toast.makeText(kayitEkraniActivity.this, "Mail düzgün girilmedi!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
