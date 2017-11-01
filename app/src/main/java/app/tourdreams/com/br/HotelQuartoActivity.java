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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HotelQuartoActivity extends AppCompatActivity
{
    ListView list_view_quarto;
    TextView text_view_hotel, text_view_local, text_view_avaliacao, text_view_checkin, text_view_checkout;
    ImageView img_view_estrela1, img_view_estrela2, img_view_estrela3, img_view_estrela4, img_view_estrela5, img_view_hotel;
    Context context;
    Intent intent;
    Integer idHotel = 0;
    List<Quarto> lstQuarto = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_quarto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        pegarObjetosView();

        intent = getIntent();
        idHotel = intent.getIntExtra("idHotel", 0);

        if(idHotel != 0)
        {
            new PreencherDadosHotelTask().execute();
            new PreencherQuartosTask().execute();
        }

        list_view_quarto.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
            {
                Intent intentQuarto = new Intent(context, QuartoActivity.class);
                Integer idQuarto = lstQuarto.get(pos).getIdQuarto();
                intentQuarto.putExtra("idQuarto", idQuarto);
                startActivity(intentQuarto);
            }
        });
    }

    private void pegarObjetosView()
    {
        list_view_quarto = (ListView) findViewById(R.id.list_view_quarto);
        text_view_hotel = (TextView) findViewById(R.id.text_view_hotel);
        text_view_local = (TextView) findViewById(R.id.text_view_local);
        text_view_avaliacao = (TextView) findViewById(R.id.text_view_avaliacao);
        text_view_checkin = (TextView) findViewById(R.id.text_view_checkin);
        text_view_checkout = (TextView) findViewById(R.id.text_view_checkout);
        img_view_estrela1 = (ImageView) findViewById(R.id.img_view_estrela1);
        img_view_estrela2 = (ImageView) findViewById(R.id.img_view_estrela2);
        img_view_estrela3 = (ImageView) findViewById(R.id.img_view_estrela3);
        img_view_estrela4 = (ImageView) findViewById(R.id.img_view_estrela4);
        img_view_estrela5 = (ImageView) findViewById(R.id.img_view_estrela5);
        img_view_hotel = (ImageView) findViewById(R.id.img_view_hotel);
    }

    private class PreencherQuartosTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%squartosHotel.php?idHotel=%d", href, idHotel);
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
                Gson gson = new Gson();
                lstQuarto = gson.fromJson(retorno, new TypeToken<List<Quarto>>(){}.getType());
                list_view_quarto.setAdapter(new QuartoAdapter(context, R.layout.list_view_quarto, lstQuarto));
            }
        }
    }

    private class PreencherDadosHotelTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sdadosHotel.php?idHotel=%d", href, idHotel);
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
                Gson gson = new Gson();
                Hotel hotel = gson.fromJson(retorno, Hotel.class);
                preencherDadosHotelView(hotel);
            }

        }
    }

    private void preencherDadosHotelView(Hotel hotel)
    {
        text_view_hotel.setText(hotel.getHotel());
        text_view_local.setText(String.format("%s, %s - %s", hotel.getBairro(), hotel.getCidade(), hotel.getUf()));
        text_view_avaliacao.setText(String.format("%.0f - %d avaliações", hotel.getAvaliacao(), hotel.getQtdAvaliacoes()));
        switch (hotel.getQtdEstrelas())
        {
            case 1:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                break;
            case 2:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                break;
            case 3:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                img_view_estrela3.setImageResource(R.drawable.estrela);
                break;
            case 4:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                img_view_estrela3.setImageResource(R.drawable.estrela);
                img_view_estrela4.setImageResource(R.drawable.estrela);
                break;
            case 5:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                img_view_estrela3.setImageResource(R.drawable.estrela);
                img_view_estrela4.setImageResource(R.drawable.estrela);
                img_view_estrela5.setImageResource(R.drawable.estrela);
                break;
        }
        Glide.with(context).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+ hotel.getCaminhoImagem()).thumbnail(Glide.with(context).load(R.drawable.loading)).into(img_view_hotel);
        text_view_checkin.setText(String.format("Check-in: %s", hotel.getCheckin()));
        text_view_checkout.setText(String.format("Check-out: %s", hotel.getCheckout()));
    }


}
