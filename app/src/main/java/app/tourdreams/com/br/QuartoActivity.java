package app.tourdreams.com.br;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class QuartoActivity extends AppCompatActivity
{
    Intent intent;
    Context context;
    Integer idQuarto;
    TextView text_view_hotel, text_view_quarto, text_view_local, text_view_diaria;
    ImageView img_view_quarto;
    Quarto quarto;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;
        intent = getIntent();
        idQuarto = intent.getIntExtra("idQuarto", 0);

        pegarObjetosView();

        if(!Sessao.getStatusLogin())
        {
            new AlertDialog.Builder(context).setTitle("Você precisa efetuar o login!")
                    .setMessage("É necessário o login para efetuar uma reserva.\nDeseja efetuar o login agora?")
                    .setNegativeButton("NÃO", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            startActivity(new Intent(context, MainActivity.class));
                        }
                    })
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    }).show();
        }

        if(idQuarto != 0)
        {
            new PreencherDadosQuartoTask().execute();
        }

    }

    private void pegarObjetosView()
    {
        text_view_hotel = (TextView) findViewById(R.id.text_view_hotel);
        text_view_quarto = (TextView) findViewById(R.id.text_view_quarto);
        text_view_local = (TextView) findViewById(R.id.text_view_local);
        text_view_diaria = (TextView) findViewById(R.id.text_view_diaria);
        img_view_quarto = (ImageView) findViewById(R.id.img_view_quarto);
    }

    public void abrirReserva(View view)
    {
        Intent intent = new Intent(context, ReservaActivity.class);
        intent.putExtra("idQuarto", idQuarto);
        intent.putExtra("valorDiario", quarto.getValorDiario());
        startActivity(intent);
    }

    private class PreencherDadosQuartoTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sdadosquarto.php?idQuarto=%d", href, idQuarto);
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
                quarto = gson.fromJson(retorno, Quarto.class);
                text_view_hotel.setText(quarto.getHotel());
                text_view_quarto.setText(quarto.getNome());
                text_view_local.setText(String.format("%s, %s - %s", quarto.getBairro(), quarto.getCidade(), quarto.getUf()));
                Glide.with(context).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+ quarto.getCaminhoImagem()).thumbnail(Glide.with(context).load(R.drawable.loading)).into(img_view_quarto);
                text_view_diaria.setText(String.format("R$%.2f/dia", quarto.getValorDiario()));
            }

        }
    }
}
