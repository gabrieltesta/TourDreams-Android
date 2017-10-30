package app.tourdreams.com.br;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class BuscaActivity extends AppCompatActivity
{
    ListView list_view_busca;
    String Hotel,checkin,checkout,descricao;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view_busca = (ListView) findViewById(R.id.list_view_busca);
        context = this;

        final List<Hotel> lstHotel = new ArrayList<>();

        list_view_busca.setAdapter(new HotelAdapter(this, R.layout.list_view_busca, lstHotel));


    }
        private class BuscaTask extends AsyncTask<Void, Void, Void> {
            String retorno;

            @Override
            protected Void doInBackground(Void... params) {
                String href = "http://10.107.144.15/tourdreams/";
                String link = String.format("%sbusca.php?",
                        href,
                        Hotel,
                        checkin,
                        checkout,
                        descricao
                );
                retorno = HttpConnection.get(link);
                Log.d("retorno", retorno);
                return null;
            }


        }
    }


