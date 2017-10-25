package app.tourdreams.com.br;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 16165823 on 25/10/2017.
 */




public class DataBase extends SQLiteOpenHelper  {


        SQLiteDatabase db;
        private static final String tourdreams = "tourdreams.db";
        private static final int VERSAO = 1;

        public DataBase(Context context) {
            super(context, tourdreams, null, VERSAO);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {


            db.execSQL("create table tblcadastro( " +
                    "_id INTEGER primary key," +
                    "login TEXT," +
                    "senha TEXT " +
                    "nome TEXT " +
                    "email TEXT " +
                    "rg  INT" +
                    "cpf  INT;" +
                    "celular  INT" +
                    "sexo  TEXT" );



        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {



        }
    }

