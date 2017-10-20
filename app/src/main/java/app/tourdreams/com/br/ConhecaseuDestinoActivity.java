package app.tourdreams.com.br;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class ConhecaseuDestinoActivity extends AppCompatActivity {
    ListView list_view_avaliacoes;
    List<Avaliacao> lstAvaliacoes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conhecaseu_destino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Avaliacao avaliacao = new Avaliacao();
        int cont = 0;
        while(cont < 25)
        {
            avaliacao.setNomeCliente("João da Silva");
            avaliacao.setMensagem("Lorem ipsum dolor sit amet, ne assum suscipit per, phaedrum delicatissimi cum ea. No sit reque etiam disputando, pri rebum fabellas in. Sit vocibus assueverit eloquentiam et, denique splendide his at, ne quo odio dolorem efficiendi. Sed enim quot id.");
            lstAvaliacoes.add(avaliacao);
            cont++;
        }


        list_view_avaliacoes = (ListView) findViewById(R.id.list_view_avaliacoes);
        list_view_avaliacoes.setAdapter(new AvaliacaoAdapter(this, R.layout.list_view_avaliacoes, lstAvaliacoes));
    }
}