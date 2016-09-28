package com.bigapps.ruyatabirleri;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Created by shadyfade on 23.09.2016.
 */
public class sendDreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_senddream);

        Button btnSend = (Button) findViewById(R.id.btnSend);
        final EditText etDream = (EditText) findViewById(R.id.etDreamDescription);

        final pojoCreateDream dream = new pojoCreateDream();



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDream.getText().toString()!=null){
                    dream.setDescription(etDream.getText().toString());

                    Global.getService().createDream(dream, new Callback<pojoDream>() {
                        @Override
                        public void success(pojoDream dream, Response response) {
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
                                        Toast.makeText(sendDreamActivity.this, body.errorDetails, Toast.LENGTH_SHORT).show();
                                    //case 404://kayıtlı olmayan kullanıcı
                                     //   Toast.makeText(sendDreamActivity.this,  "404", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                }else{
                    Toast.makeText(sendDreamActivity.this,"Dream cannot be blank!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
