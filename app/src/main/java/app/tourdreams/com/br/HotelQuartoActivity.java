package app.tourdreams.com.br;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HotelQuartoActivity extends AppCompatActivity
{
    ListView list_view_quarto;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_quarto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        list_view_quarto = (ListView) findViewById(R.id.list_view_quarto);

        List<Quarto> lstQuarto = new ArrayList<>();

        int cont = 0;
        Quarto quarto = new Quarto();
        while (cont < 4)
        {
            quarto.setDescricao("Lorem ipsum dolor sit amet, tantas virtute ut usu, ei eirmod convenire his. Per labores detraxit te, eu nam partem sapientem. Eos inimicus electram eu. His ne quaeque saperet nostrum. Latine tritani ad eos, et qui feugiat detracto, mutat tantas mei ea. Vix no nibh graecis. No duo graeco fastidii constituto, singulis atomorum ut qui.");
            quarto.setNome("Quarto simples");
            quarto.setValorDiario(150d);
            lstQuarto.add(quarto);
            cont++;
        }

        list_view_quarto.setAdapter(new QuartoAdapter(this, R.layout.list_view_quarto, lstQuarto));

        list_view_quarto.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                startActivity(new Intent(context, QuartoActivity.class));
            }
        });
    }

}
