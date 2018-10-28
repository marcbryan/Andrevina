package com.andrevina.et.andrevina;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fame);
        LinearLayout linearLayout = new LinearLayout(this);
        Intent intent = getIntent();
        String txt = "";
        TextView textView = findViewById(R.id.textView);
        //txt = "Nombre      -     Intentos\n";

        for (int i=0; i < MainActivity.getJugadors().size(); i++){
            TextView tv = new TextView(this);
            tv.setText(MainActivity.getJugadors().get(i).getNombre()+"   -    "+MainActivity.getJugadors().get(i).getIntentos());
            linearLayout.addView(tv);
            // txt = txt+"\n"+MainActivity.getJugadors().get(i).getNombre()+" - "+MainActivity.getJugadors().get(i).getIntentos();
        }
        //textView.setText(txt);
    }
}
