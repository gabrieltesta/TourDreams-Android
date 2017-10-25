package app.tourdreams.com.br;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NossosParceiros extends AppCompatActivity
{
    List<String> lstParceiros = new ArrayList<>();
    Spinner spn_nossosparceiros;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nossos_parceiros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spn_nossosparceiros = (Spinner) findViewById(R.id.spn_nossosparceiros);

        lstParceiros.add("Hilton");
        lstParceiros.add("Ibis");

        spn_nossosparceiros.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item ,lstParceiros));
    }

}
