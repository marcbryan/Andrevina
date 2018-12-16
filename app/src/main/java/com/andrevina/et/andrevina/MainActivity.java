package com.andrevina.et.andrevina;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private int intentos;
    private boolean datosCargados = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datosCargados = getIntent().getBooleanExtra("EXTRA_DATOS", false);
        if (!datosCargados) {
            getRecordsFromFile();
        }

        intentos = 0;
        final int numero = (int) (Math.random()*100)+1;
        System.out.println("Pista: "+numero);//pista
        final Button button = findViewById(R.id.button);
        final TextView tvIntentos = findViewById(R.id.txtViewIntentos);
        tvIntentos.setText("Intents: "+intentos);
        final EditText numIntro = findViewById(R.id.numIntro);
        numIntro.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    if (keyCode == KeyEvent.KEYCODE_ENTER){
                        return true;
                    }
                }
                return false;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String tvValue = numIntro.getText().toString();
                final Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_LONG;
                if (!tvValue.equals("")) {
                    int num1 = Integer.parseInt(tvValue);
                    if (num1 < numero){
                        text = "El numero es mes gran";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        numIntro.setText("");
                        intentos++;
                        tvIntentos.setText("Intents: "+intentos);
                    }
                    else if (num1 > numero){
                        text = "El numero es mes petit";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        numIntro.setText("");
                        intentos++;
                        tvIntentos.setText("Intents: "+intentos);
                    }
                    else if (num1 == numero) {
                        intentos++;
                        tvIntentos.setText("Intents: "+intentos);
                        text = "Has encertat!";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.custom_dialog);
                        final TextView nombre = (TextView) dialog.findViewById(R.id.txt);
                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String playerName = nombre.getText().toString();
                                if (playerName.equals("")){
                                    dialog.dismiss();
                                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                    alertDialog.setCancelable(false);
                                    alertDialog.setTitle("Escriu un nom correcte");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int which) {
                                                    dialogInterface.dismiss();
                                                    dialog.show();
                                                }
                                            }
                                    );
                                    alertDialog.show();
                                }else {
                                    Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                                    String nombreJugador = nombre.getText().toString();
                                    intent.putExtra("EXTRA_INTENTOS", intentos+"");
                                    intent.putExtra("EXTRA_NAME", nombreJugador);
                                    dialog.dismiss();
                                    startActivity(intent);
                                }
                            }
                        });
                        dialog.show();
                    }
                }
            }
        });

        ImageView img = findViewById(R.id.verRecords);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                intent.putExtra("VER_RECORDS", true);
                startActivity(intent);
            }
        });
    }

    private void getRecordsFromFile(){
        File f = new File(getFilesDir().getPath()+"/recordsAndrevina.txt");
        try{
            if (f.exists()){
                FileInputStream fis = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                String linea = "";
                while ((linea = br.readLine()) != null) {
                    if (linea.split(":").length == 2) {
                        String[] player = linea.split(":");
                        Jugador.jugadors.add(new Jugador(player[0], Integer.parseInt(player[1])));
                    }
                }
                br.close();
                fis.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
