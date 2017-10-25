package app.tourdreams.com.br;

import android.content.ContentValues;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
public class RegistroUsuarioActivity extends AppCompatActivity
{
    Button btn_salvar;
    EditText edit_login,edit_senha,edit_nome,edit_email,edit_rg,edit_cpf,edit_celular,edit_sexo;
    Spinner spn_local;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context= this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spn_local = (Spinner) findViewById(R.id.spn_local);
        List<String> lstLocais = new ArrayList<>();
        lstLocais.add("Campo");
        lstLocais.add("Litoral");
        lstLocais.add("Montanhas");
        lstLocais.add("Cidade");
        spn_local.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lstLocais));
    }
    private void BtnSalvar() {
        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirDadosBanco();
            }
        });
    }
    private void inserirDadosBanco() {
        SQLiteDatabase db = new

                DataBase(context).getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("login", edit_login.getText().toString());
        contentValues.put("senha", edit_senha.getText().toString());
        contentValues.put("nome", edit_nome.getText().toString());
        contentValues.put("email", edit_email.getText().toString());
        contentValues.put("rg", edit_rg.getText().toString());
        contentValues.put("cpf", edit_cpf.getText().toString());
        contentValues.put("celular", edit_celular.getText().toString());
        contentValues.put("sexo", edit_sexo.getText().toString());
        db.insert("tblcadastro", null, contentValues);
    }
}

