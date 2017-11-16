package app.tourdreams.com.br;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class NossosParceirosActivity extends AppCompatActivity
{
    List<Parceiro> lstParceiros = new ArrayList<>();
    ListView list_view_parceiros;
    Spinner spn_nossosparceiros;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nossos_parceiros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        //spn_nossosparceiros = (Spinner) findViewById(R.id.spn_nossosparceiros);
        list_view_parceiros = (ListView) findViewById(R.id.list_view_parceiros);
        //new PreencherSpinnerTask().execute();
        new PreencherListaDestaqueTask().execute();
    }

    private class PreencherListaDestaqueTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sparceirosDestaque.php", href);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstParceiros= gson.fromJson(retorno, new TypeToken<List<Parceiro>>(){}.getType());
            list_view_parceiros.setAdapter(new ParceirosAdapter(context, R.layout.list_view_parceiros, lstParceiros));
        }
    }

    private class PreencherSpinnerTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sparceirosSpinner.php", href);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstParceiros= gson.fromJson(retorno, new TypeToken<List<Parceiro>>(){}.getType());
            spn_nossosparceiros.setAdapter(new ArrayAdapter<Parceiro>(context, android.R.layout.simple_spinner_item, lstParceiros));
        }
    }

}
