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
import android.widget.EditText;
import android.widget.TextView;

public class FaleconoscoActivity extends AppCompatActivity {

    TextView text_view_telefone, text_view_email, text_view_logradouro;
    EditText edit_text_nome, edit_text_email, edit_text_telefone, edit_text_mensagem;
    String nome, email, telefone, mensagem;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(app.tourdreams.com.br.R.layout.activity_faleconosco);
        Toolbar toolbar = (Toolbar) findViewById(app.tourdreams.com.br.R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        pegarObjetosView();
        new PreencherInformacoesTask().execute();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void pegarObjetosView()
    {
        text_view_telefone = (TextView) findViewById(R.id.text_view_telefone);
        text_view_email = (TextView) findViewById(R.id.text_view_email);
        text_view_logradouro = (TextView) findViewById(R.id.text_view_logradouro);
        edit_text_nome = (EditText) findViewById(R.id.edit_text_nome);
        edit_text_email = (EditText) findViewById(R.id.edit_text_email);
        edit_text_telefone = (EditText) findViewById(R.id.edit_text_telefone);
        edit_text_mensagem = (EditText) findViewById(R.id.edit_text_mensagem);
    }

    public void enviarFeedback(View view)
    {
        nome = edit_text_nome.getText().toString();
        nome = nome.replaceAll(" ", "+");
        email = edit_text_email.getText().toString();
        telefone = edit_text_telefone.getText().toString();
        telefone = telefone.replaceAll(" ", "+");
        mensagem = edit_text_mensagem.getText().toString();
        mensagem = mensagem.replaceAll(" ", "+");

        new EnviarFeedbackTask().execute();
    }


    private class PreencherInformacoesTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%sinformacoes.php", href);
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
                String[] dados = retorno.split(";");
                text_view_telefone.setText(String.format("Telefone\n%s", dados[0]));
                text_view_email.setText(String.format("Email\n%s", dados[1]));
                text_view_logradouro.setText(String.format("Endereço\n%s\n%s", dados[2], dados[3]));
            }
        }
    }

    private class EnviarFeedbackTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%senviarFeedback.php?nome=%s&email=%s&telefone=%s&mensagem=%s",
                    href,
                    nome,
                    email,
                    telefone,
                    mensagem);
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
                if(retorno.contains("ok"))
                {
                    new AlertDialog.Builder(context)
                            .setTitle("Sucesso")
                            .setMessage("Seu feedback foi enviado com sucesso.\nDeseja retornar a página inicial?")
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    startActivity(new Intent(context, MainActivity.class));
                                }
                            })
                            .setNegativeButton("NÃO", null)
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(context)
                            .setTitle("Erro")
                            .setMessage("Ocorreu um erro no envio de seu feedback.\nTente novamente mais tarde.")
                            .setNeutralButton("OK", null)
                            .show();
                }
            }
        }
    }

}
