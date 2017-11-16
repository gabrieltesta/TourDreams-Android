package app.tourdreams.com.br;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransacaoAdapter extends ArrayAdapter<Transacao>
{
    Context context;
    int resource;
    View view;
    Transacao transacao;
    TextView text_view_hotel, text_view_periodo, text_view_transacao, text_view_status;
    ImageView image_view_hotel;
    String periodo, dtTransacao;

    public TransacaoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Transacao> objects)
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

        transacao = getItem(position);
        pegarView();
        formatarStrings();
        inserirCamposLayout();

        return view;
    }

    //Insere os campos com os dados do banco de dados
    private void inserirCamposLayout()
    {
        Glide.with(view).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+ transacao.getCaminhoImagem()).thumbnail(Glide.with(view).load(R.drawable.loading)).into(image_view_hotel);
        text_view_hotel.setText(transacao.getHotel());
        text_view_periodo.setText(periodo);
        text_view_transacao.setText(dtTransacao);
        text_view_status.setText(transacao.getStatus());
        if(transacao.getStatus().contains("Pendente"))
        {
            text_view_status.setTextColor(Color.RED);
        }
        else if(transacao.getStatus().contains("Aceito"))
        {
            text_view_status.setTextColor(Color.GREEN);
        }
        else if(transacao.getStatus().contains("Rejeitado"))
        {
            text_view_status.setTextColor(Color.BLACK);
        }
    }

    // Método formata as horas iniciais e finais em um só campo, e também coloca a moeda no preço.
    private void formatarStrings()
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        periodo = String.format("Check-in: %s - %s", formato.format(transacao.getDataInicio()), formato.format(transacao.getDataFim()));

        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date data = formato2.parse(transacao.getDtTransacao());
            dtTransacao = "Reservado em: "+formato.format(data);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public void pegarView()
    {
        text_view_hotel = (TextView) view.findViewById(R.id.text_view_hotel);
        text_view_periodo = (TextView) view.findViewById(R.id.text_view_periodo);
        text_view_status = (TextView) view.findViewById(R.id.text_view_status);
        text_view_transacao = (TextView) view.findViewById(R.id.text_view_transacao);
        image_view_hotel = (ImageView) view.findViewById(R.id.image_view_hotel);
    }

}
