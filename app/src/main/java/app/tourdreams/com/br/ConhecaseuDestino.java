package app.tourdreams.com.br;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class ConhecaseuDestino extends AppCompatActivity {
    List<String> listadestino = new ArrayList<>();
    Spinner conheca_destino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conhecaseu_destino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        listadestino.add("Norte");
        listadestino.add("Nordeste");
        listadestino.add("Centro-Oeste");
        listadestino.add("Sul");
        listadestino.add("Sudeste");

        conheca_destino.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item ,listadestino));
    }


    }




