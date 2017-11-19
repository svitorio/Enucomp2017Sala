package com.example.ricardo.enucompsala;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /*
    Atributos relacionados ao formulario de cadastro de Ponto
     */
    private EditText nome,latitude,longitude;
    private String nomest;
    private String latitudest,longitudest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Inicializando os atributos Nome, Latitude e Longitude
         */
        nome = findViewById(R.id.edtNome);
        latitude = findViewById(R.id.edtLatitude);
        longitude = findViewById(R.id.edtLongitude);

        //Criando o botao de confirmar
        Button confirmar = findViewById(R.id.btnConfirmar);

        //Adicionando açao ao botao de confirmar.
        /*
        Ao clicar no botao confirmar, os dados contidos nos campos de texto serao convertidos em
        String. Esses valores sao passados para a classe MapsActivity por meio do Intent.
         */
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recuperando valores do EditText e setando em nas variaveis.
                nomest = nome.getText().toString();
                latitudest = latitude.getText().toString();
                longitudest = longitude.getText().toString();
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                //inseriando valores no intent e enviandos para a outra activity
                intent.putExtra("nome",nomest);
                intent.putExtra("latitude",latitudest);
                intent.putExtra("longitude",longitudest);
                startActivity(intent);
                finish(); // destroi a activity atual da memoria.
            }
        });
    }
}
