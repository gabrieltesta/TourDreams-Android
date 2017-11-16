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

public class ParceirosAdapter extends ArrayAdapter<Parceiro>
{
    Context context;
    int resource;
    View view;
    ImageView img_view_parceiro;
    TextView text_view_nome;
    String mensagem;
    Parceiro parceiro;


    public ParceirosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Parceiro> objects)
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

        parceiro = getItem(position);
        pegarView();
        inserirCamposLayout();

        return view;
    }

    //Insere os campos com os dados do banco de dados
    private void inserirCamposLayout()
    {
        text_view_nome.setText(parceiro.getNomeParceiro());
        Glide.with(view).load("http://www.portaltourdreams.com.br/"+parceiro.getCaminhoImagem()).thumbnail(Glide.with(view).load(R.drawable.loading)).into(img_view_parceiro);
    }

    // Método formata as horas iniciais e finais em um só campo, e também coloca a moeda no preço.
    public void pegarView()
    {
        text_view_nome = (TextView) view.findViewById(R.id.text_view_nome);
        img_view_parceiro = (ImageView) view.findViewById(R.id.img_view_parceiro);
    }

}
