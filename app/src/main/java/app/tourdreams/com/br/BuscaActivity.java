package app.tourdreams.com.br;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BuscaActivity extends AppCompatActivity
{
    ListView list_view_busca;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view_busca = (ListView) findViewById(R.id.list_view_busca);

        List<Hotel> lstHotel = new ArrayList<>();

        int cont = 0;
        while (cont < 25)
        {
            Hotel hotel = new Hotel();
            hotel.setIdHotel(1);
            hotel.setHotel("Hotelzão");
            hotel.setCaminhoImagem("url");
            hotel.setQtdEstrelas(4);
            hotel.setValorMinimo(150.00);
            hotel.setBairro("Bairro Exemplo");
            hotel.setCidade("São Paulo");
            hotel.setUf("RJ");
            hotel.setAvaliacao(50);
            hotel.setQtdAvaliacoes(124);
            lstHotel.add(hotel);
            cont++;
        }

        list_view_busca.setAdapter(new HotelAdapter(this, R.layout.list_view_busca, lstHotel));
    }

}
