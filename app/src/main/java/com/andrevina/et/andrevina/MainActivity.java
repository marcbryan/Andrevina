package com.andrevina.et.andrevina;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int intentos;
    private static Jugador jugador;
    private static ArrayList<Jugador> jugadors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int numero = (int) (Math.random()*100)+1;
        final Button button = findViewById(R.id.button);
        final EditText eText = new EditText(this);
        createPlayers();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv;
                tv = (TextView) findViewById(R.id.numIntro);
                String tvValue = tv.getText().toString();
                System.out.println("Pista: "+numero);//borrar
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
                        jugador = new Jugador("Marc", intentos);
                        System.out.println("Nº jugadores: "+jugadors.size());
                        jugadors.add(jugador);
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.custom_dialog);
                        final TextView nombre = (TextView) dialog.findViewById(R.id.txt);
                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (nombre.getText().equals("")){
                                    dialog.show();
                                }else {
                                    jugador.setNombre(nombre.getText() + "");
                                    dialog.dismiss();
                                    nuevaActivity();
                                }
                            }
                        });
                        dialog.setTitle("Has encertat!!");
                        dialog.show();
                    }
                }
            }
        });

    }

    public void createPlayers(){
        jugadors = new ArrayList<Jugador>();
        Jugador j1 = new Jugador("Jug1", 6);
        jugadors.add(j1);

        Jugador j2 = new Jugador("Jug2", 7);
        jugadors.add(j2);

        Jugador j3 = new Jugador("Jug3", 11);
        jugadors.add(j3);

        Jugador j4 = new Jugador("Jug4", 5);
        jugadors.add(j4);

        Jugador j5 = new Jugador("Jug5", 14);
        jugadors.add(j5);

        Jugador j6 = new Jugador("Jug6", 9);
        jugadors.add(j6);
    }

    public static ArrayList<Jugador> getJugadors() {
        return jugadors;
    }

    public void nuevaActivity(){
        Intent intent = new Intent(this, FameActivity.class);
        startActivity(intent);
    }
}
