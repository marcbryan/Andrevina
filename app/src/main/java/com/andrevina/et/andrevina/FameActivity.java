package com.andrevina.et.andrevina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fame);
        LinearLayout vertic = findViewById(R.id.vertical);
        Intent intent = getIntent();
        String str_intentos = intent.getStringExtra("EXTRA_INTENTOS");
        String player_Name = intent.getStringExtra("EXTRA_NAME");

        if (str_intentos != null && player_Name != null) {
            addRecordToFile(player_Name, str_intentos);
        }

        if (Jugador.jugadors.size() != 0) {
            //Ordena los jugadores por el numero de intentos mas bajo
            Collections.sort(Jugador.jugadors, new Comparator<Jugador>() {
                @Override
                public int compare(Jugador j1, Jugador j2) {
                    return new Integer(j1.getIntentos()).compareTo(new Integer(j2.getIntentos()));
                }
            });

            //Coloca los jugadores que existan en la tabla de records
            for (int i=0; i < Jugador.jugadors.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(0, 100);
                lparams.weight = 1;

                TextView nom_jug = new TextView(this);
                nom_jug.setTextSize(20);
                nom_jug.setText(Jugador.jugadors.get(i).getNombre());
                nom_jug.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                nom_jug.setLayoutParams(lparams);

                TextView intents_jug = new TextView(this);
                intents_jug.setTextSize(20);
                intents_jug.setText(Jugador.jugadors.get(i).getIntentos() + "");
                intents_jug.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                intents_jug.setLayoutParams(lparams);

                linearLayout.addView(nom_jug);
                linearLayout.addView(intents_jug);
                vertic.addView(linearLayout);
            }
        }
    }

    @Override
    public void onBackPressed(){
        Intent intVR = getIntent();
        if (intVR.getBooleanExtra("VER_RECORDS", false) == true){
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("EXTRA_DATOS", true);
            startActivity(intent);
        }
    }

    private void addRecordToFile(String nombre, String intentos){
        File f = new File(getFilesDir().getPath()+"/recordsAndrevina.txt");
        try {
            if (!f.exists()){
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.append(nombre+":"+intentos+System.lineSeparator());
            writer.close();
            fos.flush();
            fos.close();

            Jugador jugador = new Jugador(nombre, Integer.parseInt(intentos));
            Jugador.jugadors.add(jugador);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
