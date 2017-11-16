package app.tourdreams.com.br;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class PerfilParceiroActivity extends AppCompatActivity
{
    ImageView img_view_parceiro;
    TextView text_view_parceiro, text_view_cnpj, text_view_email;
    ListView list_view_hoteis;
    Context context;
    Parceiro parceiro;
    List<Hotel> lstHotel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_parceiro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pegarObjetosView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        new PegarDadosParceiroTask().execute();
    }

    private void pegarObjetosView()
    {
        img_view_parceiro = (ImageView) findViewById(R.id.img_view_parceiro);
        text_view_parceiro = (TextView) findViewById(R.id.text_view_parceiro);
        text_view_cnpj = (TextView) findViewById(R.id.text_view_cnpj);
        text_view_email = (TextView) findViewById(R.id.text_view_email);
        list_view_hoteis = (ListView) findViewById(R.id.list_view_hoteis);
    }

    public void entrarAtendimentoCliente(View view)
    {

    }

    private class PegarDadosParceiroTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%sdadosParceiro.php?login=%s",
                    href, Sessao.getLogin()
            );
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            parceiro = gson.fromJson(retorno, Parceiro.class);
            text_view_parceiro.setText(parceiro.getNomeParceiro());
            text_view_email.setText(parceiro.getEmailParceiro());
            text_view_cnpj.setText(parceiro.getCnpj());

            Glide.with(context).load("http://www.portaltourdreams.com.br/"+ parceiro.getCaminhoImagem()).thumbnail(Glide.with(context).load(R.drawable.loading)).into(img_view_parceiro);
            new PreencherListaHotelTask().execute();
        }
    }

    private class PreencherListaHotelTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%sbusca.php?tipo=parceiro&idParceiro=%d", href, parceiro.getIdParceiro());
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstHotel = gson.fromJson(retorno, new TypeToken<List<Hotel>>(){}.getType());
            list_view_hoteis.setAdapter(new HotelAdapter(context, R.layout.list_view_busca, lstHotel));
        }
    }


}
