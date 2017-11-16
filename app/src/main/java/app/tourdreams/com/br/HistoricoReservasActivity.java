package app.tourdreams.com.br;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HistoricoReservasActivity extends AppCompatActivity
{
    ListView list_view_historico_reservas;
    List<Transacao> lstTransacao = new ArrayList<>();
    Context context;
    TextView text_view_historico_erro;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_reservas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view_historico_reservas = (ListView) findViewById(R.id.list_view_historico_reservas);
        context = this;
        text_view_historico_erro = (TextView) findViewById(R.id.text_view_historico_erro);

        new PreencherListaReservasTask().execute();

    }

    private class PreencherListaReservasTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%shistoricoReserva.php?login=%s", href, Sessao.getLogin());
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstTransacao= gson.fromJson(retorno, new TypeToken<List<Transacao>>(){}.getType());
            list_view_historico_reservas.setAdapter(new TransacaoAdapter(context, R.layout.list_view_reservas, lstTransacao));
            if(lstTransacao.isEmpty())
            {
                text_view_historico_erro.setVisibility(View.VISIBLE);
            }
            else
            {
                text_view_historico_erro.setVisibility(View.GONE);
            }
        }
    }
}
