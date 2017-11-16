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

public class PerfilUsuarioActivity extends AppCompatActivity
{

    TextView text_view_nome, text_view_email, text_view_telefone, text_view_tipo_local, text_view_tipo_reserva, text_view_mtf;
    ImageView img_view_usuario;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context=this;
        pegarObjetosView();

        if(Sessao.getLogin() != null && Sessao.getStatusLogin())
        {
            new PegarDadosUsuarioTask().execute();
        }
    }

    private void pegarObjetosView()
    {
        text_view_nome = (TextView) findViewById(R.id.text_view_nome);
        text_view_email = (TextView) findViewById(R.id.text_view_email);
        text_view_telefone = (TextView) findViewById(R.id.text_view_telefone);
        text_view_tipo_local = (TextView) findViewById(R.id.text_view_tipo_local);
        text_view_tipo_reserva = (TextView) findViewById(R.id.text_view_tipo_reserva);
        text_view_mtf = (TextView) findViewById(R.id.text_view_mtf);
        img_view_usuario = (ImageView) findViewById(R.id.img_view_usuario);
    }

    public void excluirConta(View view)
    {
        new AlertDialog.Builder(context)
                .setTitle("Excluir Conta")
                .setMessage("Tem certeza que deseja excluir sua conta?\nEste processo não pode ser desfeito.")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        new ExcluirUsuarioTask().execute();
                    }
                })
                .show();
    }

    public void editarConta(View view)
    {
        startActivity(new Intent(context, EditarUsuarioActivity.class));
    }

    public void abrirHistorico(View view)
    {
        startActivity(new Intent(context, HistoricoReservasActivity.class));
    }

    private class PegarDadosUsuarioTask extends AsyncTask<Void, Void, Void>
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
            String link = String.format("%sdadosUsuario.php?login=%s",
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
            Usuario usuario = gson.fromJson(retorno, Usuario.class);
            text_view_nome.setText(usuario.getNomeCliente());
            text_view_email.setText(usuario.getEmailCliente());
            text_view_telefone.setText(usuario.getTelefone());
            text_view_tipo_local.setText(String.format("Prefere viajar para: %s", usuario.getTipoLocal()));
            text_view_tipo_reserva.setText(String.format("Tem viajado para: %s", "Campo"));
            text_view_mtf.setText(String.format("Você possui %d pontos no Milhas Travel", usuario.getMilhasPontuacao()));
            Glide.with(context).load("http://www.portaltourdreams.com.br/"+ usuario.getCaminhoImagem()).thumbnail(Glide.with(context).load(R.drawable.loading)).into(img_view_usuario);
        }
    }

    private class ExcluirUsuarioTask extends AsyncTask<Void, Void, Void>
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
            String link = String.format("%sexcluirUsuario.php?login=%s",
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
            if(retorno.contains("ok"))
            {
                Sessao.setStatusLogin(false);
                Sessao.setParceiro(false);
                Sessao.setUsuario(false);
                Sessao.setLogin(null);
                new AlertDialog.Builder(context)
                        .setTitle("Conta excluída")
                        .setMessage("Sua conta foi excluída com sucesso\nLembre-se: você pode sempre criar outra conta!")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                startActivity(new Intent(context, MainActivity.class));
                            }
                        })
                        .show();
            }
            else
            {
                new AlertDialog.Builder(context)
                        .setTitle("Erro")
                        .setMessage("Não foi possível excluir sua conta.\nTente novamente mais tarde.")
                        .setNeutralButton("OK", null)
                        .show();
            }

        }
    }
}
