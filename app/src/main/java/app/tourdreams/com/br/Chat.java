package app.tourdreams.com.br;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Chat extends AppCompatActivity {

        ImageView imagem1;
         ImageView imagem2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagem1=(ImageView)findViewById(R.id.imagem1);

        Picasso.with(this).load(R.drawable.pessoa).transform(new CircleTransform()).into(imagem1);


        imagem2=(ImageView)findViewById(R.id.imagem2);

        Picasso.with(this).load(R.drawable.ba).transform(new CircleTransform()).into(imagem2);




    }



}
