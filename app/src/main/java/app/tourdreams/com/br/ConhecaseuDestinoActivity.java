package app.tourdreams.com.br;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class ConhecaseuDestinoActivity extends AppCompatActivity {
    ListView list_view_avaliacoes;
    EditText edit_text_cidade;
    List<Avaliacao> lstAvaliacoes = new ArrayList<>();
    Context context;
    AvaliacaoAdapter avaliacaoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conhecaseu_destino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        list_view_avaliacoes = (ListView) findViewById(R.id.list_view_avaliacoes);
        edit_text_cidade = (EditText) findViewById(R.id.edit_text_cidade);

        edit_text_cidade.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                avaliacaoAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        
        new PreencherListaAvaliacoesTask().execute();
    }

    private class PreencherListaAvaliacoesTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.107.144.24/tourdreams/";
            String link = String.format("%savaliacoes.php", href);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstAvaliacoes= gson.fromJson(retorno, new TypeToken<List<Avaliacao>>(){}.getType());
            avaliacaoAdapter = new AvaliacaoAdapter(context, R.layout.list_view_avaliacoes, lstAvaliacoes);
            list_view_avaliacoes.setAdapter(avaliacaoAdapter);
        }
    }
}