package app.tourdreams.com.br;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    TextView text_view_hotel, text_view_avaliacao, text_view_qtd_avaliacoes, text_view_bairro, text_view_valor, text_view_cidadeuf;
    ImageView img_busca_hotel, img_view_estrela1, img_view_estrela2, img_view_estrela3, img_view_estrela4, img_view_estrela5;

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
        text_view_bairro.setText(hotel.getBairro());
        text_view_cidadeuf.setText(String.format("%s - %s", hotel.getCidade(), hotel.getUf()));
        text_view_valor.setText(valorMinimo);
        text_view_qtd_avaliacoes.setText(qtdAvaliacoes);
        text_view_avaliacao.setText(String.format("%.0f", hotel.getAvaliacao()));
        Glide.with(view).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/td/"+ hotel.getCaminhoImagem()).thumbnail(Glide.with(view).load(R.drawable.loading)).into(img_busca_hotel);

        switch (hotel.getQtdEstrelas())
        {
            case 1:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                break;
            case 2:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                break;
            case 3:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                img_view_estrela3.setImageResource(R.drawable.estrela);
                break;
            case 4:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                img_view_estrela3.setImageResource(R.drawable.estrela);
                img_view_estrela4.setImageResource(R.drawable.estrela);
                break;
            case 5:
                img_view_estrela1.setImageResource(R.drawable.estrela);
                img_view_estrela2.setImageResource(R.drawable.estrela);
                img_view_estrela3.setImageResource(R.drawable.estrela);
                img_view_estrela4.setImageResource(R.drawable.estrela);
                img_view_estrela5.setImageResource(R.drawable.estrela);
                break;
        }
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
        text_view_bairro = (TextView) view.findViewById(R.id.text_view_bairro);
        text_view_cidadeuf = (TextView) view.findViewById(R.id.text_view_cidadeuf);
        text_view_qtd_avaliacoes = (TextView) view.findViewById(R.id.text_view_qtd_avaliacoes);
        text_view_valor = (TextView) view.findViewById(R.id.text_view_valor);
        img_busca_hotel = (ImageView) view.findViewById(R.id.img_busca_hotel);
        img_view_estrela1 = (ImageView) view.findViewById(R.id.img_view_estrela1);
        img_view_estrela2 = (ImageView) view.findViewById(R.id.img_view_estrela2);
        img_view_estrela3 = (ImageView) view.findViewById(R.id.img_view_estrela3);
        img_view_estrela4 = (ImageView) view.findViewById(R.id.img_view_estrela4);
        img_view_estrela5 = (ImageView) view.findViewById(R.id.img_view_estrela5);
    }

}
