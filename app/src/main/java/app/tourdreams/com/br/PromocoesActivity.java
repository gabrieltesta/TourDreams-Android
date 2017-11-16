package app.tourdreams.com.br;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class PromocoesActivity extends AppCompatActivity
{
    List<Promocao> lstPromocao = new ArrayList<>();
    BannerSlider bannerSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        new PegarPromocoesTask().execute();

    }

    private class PegarPromocoesTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%spromocoes.php", href);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstPromocao = gson.fromJson(retorno, new TypeToken<List<Promocao>>(){}.getType());
            List<Banner> banners = new ArrayList<>();
            for (int i = 0; i < lstPromocao.size(); i++)
            {
                banners.add(new RemoteBanner("http://www.portaltourdreams.com.br/"+lstPromocao.get(i).getCaminhoImagem()));
            }

            bannerSlider.setBanners(banners);
        }
    }
}
