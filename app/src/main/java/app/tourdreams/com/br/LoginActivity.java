package app.tourdreams.com.br;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



public class LoginActivity extends AppCompatActivity {

    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buscarDadosNoBanco();
    }





    private void buscarDadosNoBanco() {

        SQLiteDatabase db = new
                DataBase(context).getReadableDatabase();
        String comandoSql = "SELECT * FROM tblcadastro;";



        }

        }
