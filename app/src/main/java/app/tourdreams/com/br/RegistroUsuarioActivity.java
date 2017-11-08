package app.tourdreams.com.br;

import android.app.ProgressDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class RegistroUsuarioActivity extends AppCompatActivity
{
    Context context;
    Button btn_salvar;
    String login, senha, nome, email, numdoc, celular, documento, local;
    EditText edit_login, edit_senha, edit_nome, edit_email, edit_documento, edit_celular;
    TextView text_view_documento;
    Spinner spn_local;
    RadioButton radio_cpf, radio_rg;
    List<TipoLocal> lstTipoLocal = new ArrayList<>();
    Integer idTipoDeLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        pegarView();

        btn_salvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pegarDados();
                consertarURL();
                new CadastroTask().execute();
            }
        });

        spn_local = (Spinner) findViewById(R.id.spn_local);
        new PreencherSpinnerTask().execute();
    }


    private void consertarURL()
    {
        login = login.replaceAll(" ", "+");
        senha = senha.replaceAll(" ", "+");
        nome = nome.replaceAll(" ", "+");
        email = email.replaceAll(" ", "+");
        celular = celular.replaceAll(" ", "+");
        numdoc = numdoc.replaceAll(" ", "+");
    }

    private void pegarView()
    {
        edit_login = (EditText) findViewById(R.id.edit_login);
        edit_senha = (EditText) findViewById(R.id.edit_senha);
        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_celular = (EditText) findViewById(R.id.edit_celular);
        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        text_view_documento = (TextView) findViewById(R.id.text_view_documento);
        edit_documento = (EditText) findViewById(R.id.edit_documento);
        radio_cpf = (RadioButton) findViewById(R.id.radio_cpf);
        radio_rg = (RadioButton) findViewById(R.id.radio_rg);
    }


    private void pegarDados()
    {

        login = edit_login.getText().toString();
        senha = edit_senha.getText().toString();
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


    private class CadastroTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        ProgressDialog progressDialog = new ProgressDialog(context);

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog.setMessage("Estamos efetuando seu registro.");
            progressDialog.setTitle("Aguarde");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sregistroUsuario.php?login=%s&senha=%s&nome=%s&email=%s&documento=%s&numdoc=%s&celular=%s&idTipoDeLocal=%s",
                    href,
                    login,
                    senha,
                    nome,
                    email,
                    documento,
                    numdoc,
                    celular,
                    idTipoDeLocal.toString());
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(retorno.contains("ok"))
            {
                new AlertDialog.Builder(context)
                        .setTitle("Registro efetuado")
                        .setMessage("Seu registro foi efetuado com sucesso.\nDeseja efetuar o login?")
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                startActivity(new Intent(context, LoginActivity.class));
                            }
                        }).setNegativeButton("NÃO", null)
                        .show();
            }
            else if (retorno.contains("erro"))
            {
                new AlertDialog.Builder(context)
                        .setTitle("Erro no registro")
                        .setMessage("Houve um erro no registro. Tente novamente mais tarde.")
                        .setNeutralButton("OK", null)
                        .show();
            }
        }
    }
    private class PreencherSpinnerTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
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
}

