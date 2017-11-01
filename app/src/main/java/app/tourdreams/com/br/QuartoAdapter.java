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

public class QuartoAdapter extends ArrayAdapter<Quarto>
{
    Context context;
    int resource;
    View view;
    Quarto quarto;
    ImageView img_view_quarto;
    TextView text_view_preco, text_view_descricao, text_view_nome_quarto;
    String preco, descricao;

    public QuartoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Quarto> objects)
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

        quarto = getItem(position);
        pegarView();
        formatarStrings();
        inserirCamposLayout();

        return view;
    }

    //Insere os campos com os dados do banco de dados
    private void inserirCamposLayout()
    {
        Glide.with(view).load("http://10.0.2.2/inf4t/Gabriel%20Augusto/"+quarto.getCaminhoImagem()).thumbnail(Glide.with(view).load(R.drawable.loading)).into(img_view_quarto);
        text_view_nome_quarto.setText(quarto.getNome());
        text_view_descricao.setText(descricao);
        text_view_preco.setText(preco);
    }

    // Método formata as horas iniciais e finais em um só campo, e também coloca a moeda no preço.
    private void formatarStrings()
    {
        preco = String.format("R$ %.2f", quarto.getValorDiario());
        descricao = quarto.getDescricao();
        if(descricao.length() > 30)
        {
            descricao = descricao.substring(0,30);
            descricao += "(...)";
        }
    }

    public void pegarView()
    {
        img_view_quarto = (ImageView) view.findViewById(R.id.img_view_quarto);
        text_view_nome_quarto = (TextView) view.findViewById(R.id.text_view_nome_quarto);
        text_view_descricao = (TextView) view.findViewById(R.id.text_view_descricao);
        text_view_preco = (TextView) view.findViewById(R.id.text_view_preco);
    }

}
