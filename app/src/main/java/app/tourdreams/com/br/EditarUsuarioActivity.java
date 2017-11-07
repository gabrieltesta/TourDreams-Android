package app.tourdreams.com.br;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class EditarUsuarioActivity extends AppCompatActivity
{
    Context context;
    Button btn_salvar;
    String nome, email, numdoc, celular, documento;
    EditText edit_nome, edit_email, edit_documento, edit_celular;
    ImageView img_view_cliente;
    TextView text_view_documento;
    Spinner spn_local;
    RadioButton radio_cpf, radio_rg;
    List<TipoLocal> lstTipoLocal = new ArrayList<>();
    Integer idTipoDeLocal;
    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        pegarView();

        spn_local = (Spinner) findViewById(R.id.spn_local);
        new PegarDadosUsuarioTask().execute();
        new PreencherSpinnerTask().execute();
    }

    public void editarConta(View view)
    {
        pegarDados();
        consertarURL();
        new EditarUsuarioTask().execute();
    }

    private void consertarURL()
    {
        nome = nome.replaceAll(" ", "+");
        email = email.replaceAll(" ", "+");
        celular = celular.replaceAll(" ", "+");
        numdoc = numdoc.replaceAll(" ", "+");
    }

    private void pegarView()
    {
        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_celular = (EditText) findViewById(R.id.edit_celular);
        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        text_view_documento = (TextView) findViewById(R.id.text_view_documento);
        edit_documento = (EditText) findViewById(R.id.edit_documento);
        radio_cpf = (RadioButton) findViewById(R.id.radio_cpf);
        radio_rg = (RadioButton) findViewById(R.id.radio_rg);
        img_view_cliente = (ImageView) findViewById(R.id.img_view_cliente);
    }


    private void pegarDados()
    {
        nome = edit_nome.getText().toString();
        email = edit_email.getText().toString();
        celular = edit_celular.getText().toString();
        if(radio_rg.isChecked())
        {
            numdoc = edit_documento.getText().toString();
            documento = "rg";
        }
        else if(radio_cpf.isChecked())
        {
            numdoc = edit_documento.getText().toString();
            documento = "cpf";
        }
        idTipoDeLocal = lstTipoLocal.get(spn_local.getSelectedItemPosition()).getIdTipoDeLocal();

    }

    public void alterarRG(View view)
    {
        edit_documento.setHint("00.000.000-0");
        text_view_documento.setText("RG");
        radio_cpf.setChecked(false);
        radio_rg.setChecked(true);
    }

    public void alterarCPF(View view)
    {
        edit_documento.setHint("000.000.000-00");
        text_view_documento.setText("CPF");
        radio_cpf.setChecked(true);
        radio_rg.setChecked(false);
    }

    private class PreencherSpinnerTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.107.144.24/tourdreams/";
            String link = String.format("%stipoLocal.php", href);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            lstTipoLocal = gson.fromJson(retorno, new TypeToken<List<TipoLocal>>(){}.getType());
            spn_local.setAdapter(new ArrayAdapter<TipoLocal>(context, android.R.layout.simple_spinner_item, lstTipoLocal));
        }
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
            String href = "http://10.0.2.2/tourdreams/";
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
            usuario = gson.fromJson(retorno, Usuario.class);
            edit_nome.setText(usuario.getNomeCliente());
            edit_email.setText(usuario.getEmailCliente());
            edit_celular.setText(usuario.getTelefone());
            if(usuario.getRg().length()>5)
            {
                edit_documento.setHint("00.000.000-0");
                text_view_documento.setText("RG");
                radio_cpf.setChecked(false);
                radio_rg.setChecked(true);
                edit_documento.setText(usuario.getRg());
            }
            else if (usuario.getCpf().length()>5)
            {
                edit_documento.setHint("000.000.000-00");
                text_view_documento.setText("CPF");
                radio_cpf.setChecked(true);
                radio_rg.setChecked(false);
                edit_documento.setText(usuario.getCpf());
            }
            Glide.with(context).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/td/"+ usuario.getCaminhoImagem()).thumbnail(Glide.with(context).load(R.drawable.loading)).into(img_view_cliente);
        }
    }

    private class EditarUsuarioTask extends AsyncTask<Void, Void, Void>
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
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%seditarUsuario.php?idCliente=%s&nome=%s&email=%s&documento=%s&numdoc=%s&celular=%s&tipolocal=%s",
                    href,
                    usuario.getIdCliente(),
                    nome,
                    email,
                    documento,
                    numdoc,
                    celular,
                    idTipoDeLocal
            );
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            startActivity(new Intent(context, PerfilUsuarioActivity.class));
        }
    }
}
