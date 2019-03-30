package com.andrevina.et.andrevina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fame);
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("VER_RECORDS", false)) {
            String str_intentos = intent.getStringExtra("EXTRA_INTENTOS");
            String player_Name = intent.getStringExtra("EXTRA_NAME");
            String player_photo = intent.getStringExtra("EXTRA_PATH");

            if (str_intentos != null && player_Name != null) {
                addRecordToFile(player_Name, str_intentos, player_photo);
            }
        }
        if (Jugador.jugadors.size() != 0) {
            //Ordena los jugadores por el numero de intentos mas bajo
            Collections.sort(Jugador.jugadors, new Comparator<Jugador>() {
                @Override
                public int compare(Jugador j1, Jugador j2) {
                    return new Integer(j1.getIntentos()).compareTo(new Integer(j2.getIntentos()));
                }
            });


            // Crea el array para el adapter
            ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
            // Añade todos los jugadores al array
            arrayJugadores.addAll(Jugador.jugadors);
            // Crea el adapter para convertir el array en la lista
            JugadoresAdapter adapter = new JugadoresAdapter(this, arrayJugadores);
            // Asigna el adapter al ListView
            ListView listView = (ListView) findViewById(R.id.lvItems);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed(){
        Intent intVR = getIntent();
        if (intVR.getBooleanExtra("VER_RECORDS", false)){
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("EXTRA_DATOS", true);
            startActivity(intent);
        }
    }

    private void addRecordToFile(String nombre, String intentos, String rtFoto){
        File f = new File(getFilesDir().getPath()+"/recordsAndrevina.txt");
        try {
            if (!f.exists()){
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            String datos = nombre+":"+intentos+":"+rtFoto+System.lineSeparator();
            writer.append(datos);
            writer.close();
            fos.flush();
            fos.close();

            Jugador jugador = new Jugador(nombre, Integer.parseInt(intentos), rtFoto);
            Jugador.jugadors.add(jugador);

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
