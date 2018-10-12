package com.andrevina.et.andrevina;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int intentos;
    public static final String EXTRA_MESSAGE = "com.andrevina.et.andrevina";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int numero = (int) (Math.random()*100)+1;
        final Button button = findViewById(R.id.button);
        final EditText eText = new EditText(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv;
                tv = (TextView) findViewById(R.id.numIntro);
                String tvValue = tv.getText().toString();
                //System.out.println("Pista: "+numero);
                Context context = getApplicationContext();
                CharSequence text = "";

                int duration = Toast.LENGTH_LONG;
                if (!tvValue.equals("")) {
                    int num1 = Integer.parseInt(tvValue);
                    if (num1 < numero){
                        text = "El numero es mes gran";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        intentos++;
                    }
                    else if (num1 > numero){
                        text = "El numero es mes petit";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        intentos++;
                    }
                    else if (num1 == numero) {
                        text = "Has encertat!";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        sendMessage(v, numero);
                    }
                }
            }
        });

    }
    public void sendMessage(View view, int num) {
        Intent intent = new Intent(this, FameActivity.class);
        String message = num+"";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
