package app.tourdreams.com.br;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReservaActivity extends AppCompatActivity
{

    static int condicaoHora;
    static EditText edit_text_data_entrada, edit_text_data_saida;
    EditText edit_text_nome_titular, edit_text_numero_cartao, edit_text_codigo_seguranca, edit_text_validade_cartao;
    TextView text_view_datas, text_view_valorfinal;
    ImageView image_view_data_inicio, image_view_data_fim;
    Integer idQuarto;
    Long qtdDias;
    Double valorDiario;
    Intent intent;
    String dataEntrada, dataSaida, nomeTitular, numeroCartao, codigoSeguranca, validadeCartao;
    Button btn_reservar;
    Double valorTotal;
    boolean clicavel = false;
    boolean statusData = false;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pegarIntent();
        pegarObjetosView();

        btn_reservar.setBackgroundColor(Color.RED);
        btn_reservar.setTextColor(Color.WHITE);
        verificacaoCampos();

        btn_reservar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(clicavel)
                {
                    nomeTitular = edit_text_nome_titular.getText().toString().replaceAll(" ", "+");
                    numeroCartao = edit_text_numero_cartao.getText().toString().replaceAll(" ", "+");
                    codigoSeguranca = edit_text_codigo_seguranca.getText().toString().replaceAll(" ", "+");
                    validadeCartao = edit_text_validade_cartao.getText().toString().replaceAll(" ", "+");
                    valorTotal = qtdDias*valorDiario;
                    new ReservarTask().execute();
                }
                else
                {
                    Toast.makeText(ReservaActivity.this, "Preencha todos os campos corretamente", Toast.LENGTH_LONG).show();
                }
            }
        });

        text_view_valorfinal.setText(String.format("R$ %.2f", valorDiario));
        abrirDialogHora();
        verificacaoSelecaoData();
    }

    private void verificacaoCampos()
    {
        edit_text_nome_titular.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                alterarStatusBotao();
            }
        });
        edit_text_numero_cartao.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                alterarStatusBotao();
            }
        });
        edit_text_codigo_seguranca.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                alterarStatusBotao();
            }
        });
        edit_text_validade_cartao.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                alterarStatusBotao();
            }
        });
    }

    private void alterarStatusBotao()
    {
        if(statusData == true &&
                !edit_text_nome_titular.getText().toString().isEmpty() &&
                !edit_text_numero_cartao.getText().toString().isEmpty() &&
                !edit_text_codigo_seguranca.getText().toString().isEmpty() &&
                !edit_text_validade_cartao.getText().toString().isEmpty())
        {
            clicavel = true;
            btn_reservar.setBackgroundColor(Color.GREEN);
        }
        else
        {
            clicavel = false;
            btn_reservar.setBackgroundColor(Color.RED);
        }
    }

    private void verificacaoSelecaoData()
    {
        edit_text_data_entrada.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!edit_text_data_saida.getText().toString().isEmpty())
                {
                    checarData();
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        edit_text_data_saida.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                text_view_datas.setVisibility(View.GONE);
                if(!edit_text_data_entrada.getText().toString().isEmpty())
                {
                    checarData();
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
    }

    private void pegarIntent()
    {
        intent = getIntent();
        idQuarto = intent.getIntExtra("idQuarto", -1);
        valorDiario = intent.getDoubleExtra("valorDiario", -1);
    }

    private void pegarObjetosView()
    {
        edit_text_data_entrada = (EditText) findViewById(R.id.edit_text_data_entrada);
        edit_text_data_saida = (EditText) findViewById(R.id.edit_text_data_saida);
        image_view_data_inicio = (ImageView) findViewById(R.id.image_view_data_inicio);
        image_view_data_fim = (ImageView) findViewById(R.id.image_view_data_fim);
        text_view_datas = (TextView) findViewById(R.id.text_view_datas);
        text_view_valorfinal = (TextView) findViewById(R.id.text_view_valorfinal);
        edit_text_nome_titular = (EditText) findViewById(R.id.edit_text_nome_titular);
        edit_text_numero_cartao = (EditText) findViewById(R.id.edit_text_numero_cartao);
        edit_text_codigo_seguranca = (EditText) findViewById(R.id.edit_text_codigo_seguranca);
        edit_text_validade_cartao = (EditText) findViewById(R.id.edit_text_validade_cartao);
        btn_reservar = (Button) findViewById(R.id.btn_reservar);
    }

    private void checarData()
    {
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat formatodb = new SimpleDateFormat("yyyy-mm-dd");
        try
        {
            Date datainicial = formato.parse(edit_text_data_entrada.getText().toString());
            Date datafinal = formato.parse(edit_text_data_saida.getText().toString());

            qtdDias = (datafinal.getTime()-datainicial.getTime())/(1000*60*60*24);
            String valorfinal = (String.format("R$ %.2f", (qtdDias*valorDiario)*1.10));
            if((qtdDias*valorDiario)>=valorDiario)
                text_view_valorfinal.setText(valorfinal);

            if(datafinal.after(datainicial))
            {
                dataEntrada = formatodb.format(datainicial);
                dataSaida = formatodb.format(datafinal);
                new VerificarDataTask().execute();
            }
            else if(datafinal.getTime() ==  datainicial.getTime())
            {
                text_view_datas.setText("A data de saída não pode ser a mesma que a data de entrada.");
                text_view_datas.setTextColor(Color.RED);
                text_view_datas.setVisibility(View.VISIBLE);
            }
            else
            {
                text_view_datas.setText("A data de saída não pode ser antes da data de entrada");
                text_view_datas.setTextColor(Color.RED);
                text_view_datas.setVisibility(View.VISIBLE);
            }
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String data = String.format("%d/%d/%d", day, ++month, year);
            if (condicaoHora == 0)
            {
                edit_text_data_entrada.setText(data);
            } else if (condicaoHora == 1)
            {
                edit_text_data_saida.setText(data);
            }
        }

    }

    private void abrirDialogHora()
    {
        image_view_data_inicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment date = new DatePickerFragment();
                condicaoHora = 0;
                date.show(getFragmentManager(), "datePickerInicio");
            }
        });
        image_view_data_fim.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment date = new DatePickerFragment();
                condicaoHora = 1;
                date.show(getFragmentManager(), "datePickerFim");
            }
        });
    }

    private class ReservarTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;

        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%sreservar.php?idQuarto=%d&dataEntrada=%s&dataSaida=%s&nometitular=%s&numerocartao=%s&codigoseguranca=%s&validade=%s&valorTotal=%f&qtdDias=%d&login=%s",
                    href, idQuarto, dataEntrada, dataSaida, nomeTitular, numeroCartao, codigoSeguranca, validadeCartao, valorTotal, qtdDias, Sessao.getLogin());
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            if (retorno != null)
            {
                if (retorno.contains("ok"))
                {
                    new AlertDialog.Builder(context)
                            .setTitle("Sucesso")
                            .setMessage("Sua reserva foi efetuada com sucesso.")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    startActivity(new Intent(context, HistoricoReservasActivity.class));
                                }
                            })
                            .show();
                }
                else
                {
                    new AlertDialog.Builder(context)
                            .setTitle("Erro")
                            .setMessage("Houve um erro ao processar sua reserva.\nTente novamente mais tarde")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    //startActivity(new Intent(context, MainActivity.class));
                                }
                            })
                            .show();
                }
            }

        }
    }
    private class VerificarDataTask extends AsyncTask<Void, Void, Void>
    {
        String retorno;
        @Override
        protected Void doInBackground(Void... voids)
        {
            String href = "http://www.portaltourdreams.com.br/mobile/";
            String link = String.format("%schecarDataReserva.php?idQuarto=%d&dataEntrada=%s&dataSaida=%s", href, idQuarto, dataEntrada, dataSaida);
            retorno = HttpConnection.get(link);
            Log.d("retorno", retorno);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            if(retorno != null)
            {
                if(retorno.contains("ok"))
                {
                    text_view_datas.setText("Esta data está disponível!");
                    text_view_datas.setTextColor(Color.GREEN);
                    text_view_datas.setVisibility(View.VISIBLE);
                    statusData = true;
                }
                else
                {
                    text_view_datas.setText("Esta data está indisponível para este quarto.");
                    text_view_datas.setTextColor(Color.RED);
                    text_view_datas.setVisibility(View.VISIBLE);
                }
            }

        }
    }
}
