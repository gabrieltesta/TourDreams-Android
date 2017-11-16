package app.tourdreams.com.br;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;



public class BuscaActivity extends AppCompatActivity
{
    ListView list_view_busca;
    TextView text_view_pesquisa, text_view_busca_erro;
    String cidade, regiao, retorno;
    List<Hotel> lstHotel = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        pegarObjetosView();
        chamarBusca();

        list_view_busca.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
            {
                Intent intent = new Intent(context, HotelQuartoActivity.class);
                intent.putExtra("idHotel", lstHotel.get(pos).getIdHotel());
                startActivity(intent);
            }
        });
    }

    private void chamarBusca()
    {
        Intent intent = getIntent();
        if(intent.getStringExtra("busca") != null)
        {
            cidade = intent.getStringExtra("busca");
            text_view_pesquisa.setText(String.format("Você pesquisou por: %s", cidade));
            cidade = cidade.replaceAll(" ", "+");

            new PreencherListaHotelCidadeTask().execute();
        }

        if(intent.getStringExtra("regiao") != null)
        {
            regiao = intent.getStringExtra("regiao");
            text_view_pesquisa.setText(String.format("Você pesquisou por: %s", regiao));
            new PreencherListaHotelRegiaoTask().execute();
        }
    }

    private void pegarObjetosView()
    {
        list_view_busca = (ListView) findViewById(R.id.list_view_busca);
        text_view_pesquisa = (TextView) findViewById(R.id.text_view_pesquisa);
        text_view_busca_erro = (TextView) findViewById(R.id.text_view_busca_erro);
    }

    private class PreencherListaHotelCidadeTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%sbusca.php?tipo=cidade&cidade=%s", href, cidade);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            preencherAdapter();
            checarListaVazia();
        }

    }
    private class PreencherListaHotelRegiaoTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%sbusca.php?tipo=regiao&regiao=%s", href, regiao);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            preencherAdapter();
            checarListaVazia();
        }
    }

    private void checarListaVazia()
    {
        if(lstHotel.isEmpty())
        {
            text_view_busca_erro.setVisibility(View.VISIBLE);
        }
        else
        {
            text_view_busca_erro.setVisibility(View.GONE);
        }
    }

    private void preencherAdapter()
    {
        Gson gson = new Gson();
        lstHotel= gson.fromJson(retorno, new TypeToken<List<Hotel>>(){}.getType());
        list_view_busca.setAdapter(new HotelAdapter(context, R.layout.list_view_busca, lstHotel));
    }
}


