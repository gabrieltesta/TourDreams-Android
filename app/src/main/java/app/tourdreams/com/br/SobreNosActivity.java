package app.tourdreams.com.br;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class SobreNosActivity extends AppCompatActivity {

    ImageView image_view_visao, image_view_missao, image_view_valores;
    JustifyTextView text_view_descricaosuperior, text_view_visao, text_view_valores, text_view_missao, text_view_descricaoum, text_view_descricaodois, text_view_descricaotres;
    TextView text_view_anoum, text_view_anodois, text_view_anotres;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        pegarObjetosView();
        new PreencherDadosSobreNosTask().execute();
    }

    private void pegarObjetosView()
    {
        image_view_visao = (ImageView) findViewById(R.id.image_view_visao);
        image_view_missao = (ImageView) findViewById(R.id.image_view_missao);
        image_view_valores = (ImageView) findViewById(R.id.image_view_valores);
        text_view_anoum = (TextView) findViewById(R.id.text_view_anoum);
        text_view_anodois = (TextView) findViewById(R.id.text_view_anodois);
        text_view_anotres = (TextView) findViewById(R.id.text_view_anotres);
        text_view_descricaosuperior = (JustifyTextView) findViewById(R.id.text_view_descricaosuperior);
        text_view_visao = (JustifyTextView) findViewById(R.id.text_view_visao);
        text_view_valores = (JustifyTextView) findViewById(R.id.text_view_valores);
        text_view_missao = (JustifyTextView) findViewById(R.id.text_view_missao);
        text_view_descricaoum = (JustifyTextView) findViewById(R.id.text_view_descricaoum);
        text_view_descricaodois = (JustifyTextView) findViewById(R.id.text_view_descricaodois);
        text_view_descricaotres = (JustifyTextView) findViewById(R.id.text_view_descricaotres);
    }

    private class PreencherDadosSobreNosTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sdadosSobreNos.php", href);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            if(retorno != null)
            {
                String array[] = retorno.split(";");
                text_view_descricaosuperior.setText(array[0]+"\n");
                Glide.with(context).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+array[1]).into(image_view_visao);
                text_view_visao.setText(array[2]+"\n");
                Glide.with(context).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+array[3]).into(image_view_valores);
                text_view_valores.setText(array[4]+"\n");
                Glide.with(context).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+array[5]).into(image_view_missao);
                text_view_missao.setText(array[6]+"\n");
                text_view_anoum.setText(array[7]);
                text_view_descricaoum.setText(array[8]+"\n");
                text_view_anodois.setText(array[9]);
                text_view_descricaodois.setText(array[10]+"\n");
                text_view_anotres.setText(array[11]);
                text_view_descricaotres.setText(array[12]+"\n");
            }

        }
    }

}
