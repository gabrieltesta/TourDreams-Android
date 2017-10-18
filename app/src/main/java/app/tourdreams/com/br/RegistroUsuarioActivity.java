package app.tourdreams.com.br;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class RegistroUsuarioActivity extends AppCompatActivity
{
    Spinner spn_local;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spn_local = (Spinner) findViewById(R.id.spn_local);

        List<String> lstLocais = new ArrayList<>();
        lstLocais.add("Campo");
        lstLocais.add("Litoral");
        lstLocais.add("Montanhas");
        lstLocais.add("Cidade");

        spn_local.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lstLocais));

    }

}
