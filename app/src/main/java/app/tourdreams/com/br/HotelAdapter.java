package app.tourdreams.com.br;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HotelAdapter extends ArrayAdapter<Hotel>
{
    Context context;
    int resource;
    View view;
    Hotel hotel;
    String valorMinimo, qtdAvaliacoes;
    TextView text_view_hotel, text_view_avaliacao, text_view_qtd_avaliacoes, text_view_local, text_view_valor;
    RatingBar rat_qtd_estrelas;
    ImageView img_busca_hotel;

    public HotelAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Hotel> objects)
    {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        view = convertView;
        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        hotel = getItem(position);
        pegarView();
        formatarStrings();
        inserirCamposLayout();

        return view;
    }

    //Insere os campos com os dados do banco de dados
    private void inserirCamposLayout()
    {
        text_view_hotel.setText(hotel.getHotel());
        text_view_local.setText(String.format("%s, %s - %s", hotel.getBairro(), hotel.getCidade(), hotel.getUf()));
        text_view_valor.setText(valorMinimo);
        text_view_qtd_avaliacoes.setText(qtdAvaliacoes);
        text_view_avaliacao.setText(hotel.getAvaliacao().toString());
        rat_qtd_estrelas.setNumStars(hotel.getQtdEstrelas());
        Glide.with(view).load("https://taj.tajhotels.com/content/dam/luxury/hotels/Taj_Mahal_Delhi/images/4x3/HotelFacade4x3.jpg").thumbnail(Glide.with(view).load(R.drawable.loading)).into(img_busca_hotel);
    }

    // Método formata as horas iniciais e finais em um só campo, e também coloca a moeda no preço.
    private void formatarStrings()
    {
        String valor = String.format("R$ %.2f", hotel.getValorMinimo());
        valorMinimo = valor.replace(".", ",");
        qtdAvaliacoes = String.format("%d avaliações", hotel.getQtdAvaliacoes());
    }

    public void pegarView()
    {
        text_view_avaliacao = (TextView) view.findViewById(R.id.text_view_avaliacao);
        text_view_hotel = (TextView) view.findViewById(R.id.text_view_hotel);
        text_view_local = (TextView) view.findViewById(R.id.text_view_local);
        text_view_qtd_avaliacoes = (TextView) view.findViewById(R.id.text_view_qtd_avaliacoes);
        text_view_valor = (TextView) view.findViewById(R.id.text_view_valor);
        rat_qtd_estrelas = (RatingBar) view.findViewById(R.id.rat_qtd_estrelas);
        img_busca_hotel = (ImageView) view.findViewById(R.id.img_busca_hotel);
    }

}
