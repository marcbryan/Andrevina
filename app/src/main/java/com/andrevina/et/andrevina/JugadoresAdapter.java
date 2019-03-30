package com.andrevina.et.andrevina;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class JugadoresAdapter extends ArrayAdapter<Jugador> {

    private TextView tvName;
    private TextView tvIntents;
    private ImageView pic;

    public JugadoresAdapter(Context context, ArrayList<Jugador> jugadores) {
        super(context, 0, jugadores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Jugador jugador = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jugador, parent, false);
            // Buscar los componentes para asignarle los datos
            tvName = (TextView) convertView.findViewById(R.id.tvNombre);
            tvIntents = (TextView) convertView.findViewById(R.id.tvIntentos);
            pic = (ImageView) convertView.findViewById(R.id.ivFoto);
        }
        // Imagen jugador
        Bitmap bitmap = BitmapFactory.decodeFile(new File(jugador.getRutaFoto()).getPath());
        bitmap = Bitmap.createScaledBitmap(bitmap, 48,48, true);

        // Asignar los datos a los componentes
        tvName.setText(jugador.getNombre());
        tvIntents.setText(String.valueOf(jugador.getIntentos()));
        pic.setImageBitmap(bitmap);
        // Devuelve la vista completada para mostrarla en la pantalla
        return convertView;
    }
}
