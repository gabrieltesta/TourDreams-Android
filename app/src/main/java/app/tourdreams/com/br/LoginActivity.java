package app.tourdreams.com.br;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    Context context;
    EditText edit_text_login, edit_text_senha;
    String login, senha;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        pegarView();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pegarDados();
                new LoginTask().execute();
            }
        });




    }

    private void pegarDados() {
        login = edit_text_login.getText().toString();
        senha = edit_text_senha.getText().toString();
    }

    private void pegarView() {
        edit_text_login = (EditText) findViewById(R.id.edit_text_login);
        edit_text_senha = (EditText) findViewById(R.id.edit_text_senha);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    private class LoginTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... params)
        {
            String href = "http://10.0.2.2/tourdreams/";
            String link = String.format("%slogin.php?login=%s&senha=%s",
                    href,
                    login,
                    senha);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            if(retorno.contains("parceiro"))
            {
                Sessao.setStatusLogin(true);
                Sessao.setParceiro(true);
                Sessao.setUsuario(false);
                Sessao.setLogin(login);
                startActivity(new Intent(context, MainActivity.class));
            }
            else if(retorno.contains("usuario"))
            {
                Sessao.setStatusLogin(true);
                Sessao.setUsuario(true);
                Sessao.setParceiro(false);
                Sessao.setLogin(login);
                startActivity(new Intent(context, MainActivity.class));
            }
            else
            {
                Toast.makeText(context, "Usuário ou Senha inválido", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
