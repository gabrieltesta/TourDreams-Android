package app.tourdreams.com.br;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AvaliacaoAdapter extends ArrayAdapter<Avaliacao> implements Filterable
{
    Context context;
    int resource;
    View view;
    Avaliacao avaliacao;
    ImageView img_view_cliente;
    TextView text_view_nome, text_view_texto;
    String mensagem;



    public AvaliacaoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Avaliacao> objects)
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

        avaliacao = getItem(position);
        pegarView();
        formatarStrings();
        inserirCamposLayout();

        return view;
    }

    //Insere os campos com os dados do banco de dados
    private void inserirCamposLayout()
    {
        text_view_nome.setText(avaliacao.getNomeCliente());
        text_view_texto.setText(mensagem);
        Glide.with(view).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/td/"+avaliacao.getCaminhoImagem()).thumbnail(Glide.with(view).load(R.drawable.loading)).into(img_view_cliente);
    }

    // Método formata as horas iniciais e finais em um só campo, e também coloca a moeda no preço.
    private void formatarStrings()
    {
        mensagem = avaliacao.getMensagem();
        if(mensagem.length() > 100)
        {
            mensagem = mensagem.substring(0,100);
            mensagem += "(...)";
        }
    }

    public void pegarView()
    {
        text_view_nome = (TextView) view.findViewById(R.id.text_view_nome);
        text_view_texto = (TextView) view.findViewById(R.id.text_view_texto);
        img_view_cliente = (ImageView) view.findViewById(R.id.img_view_cliente);
    }



}
