package app.tourdreams.com.br;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class RegistroUsuarioActivity extends AppCompatActivity
{
    Context context;
    Button btn_salvar;
    String login,senha,nome,email,rg,cpf,celular;
    EditText edit_login,edit_senha,edit_nome,edit_email,edit_rg,edit_cpf,edit_celular;
    Spinner spn_local;







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




        spn_local = (Spinner) findViewById(R.id.spn_local);
        List<String> lstLocais = new ArrayList<>();
        lstLocais.add("Campo");
        lstLocais.add("Litoral");
        lstLocais.add("Montanhas");
        lstLocais.add("Cidade");
        spn_local.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lstLocais));
    }

    private void pegarView() {
        edit_login = (EditText) findViewById(R.id.edit_login);
        edit_senha = (EditText) findViewById(R.id.edit_senha);
        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_rg = (EditText) findViewById(R.id.edit_rg);
        edit_cpf = (EditText) findViewById(R.id.edit_cpf);
        edit_celular = (EditText) findViewById(R.id.edit_celular);
        btn_salvar = (Button) findViewById(R.id.btn_salvar);


    }



    private void pegarDados(){

        login = edit_login.getText().toString();
        senha = edit_senha.getText().toString();
        nome = edit_nome.getText().toString();
        email = edit_email.getText().toString();
        rg = edit_rg.getText().toString();
        cpf = edit_cpf.getText().toString();
        celular = edit_celular.getText().toString();


    }

    }


