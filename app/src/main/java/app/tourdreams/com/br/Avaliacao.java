package app.tourdreams.com.br;

public class Avaliacao
{
    private Integer idMensagem;
    private String mensagem;
    private String dataMensagem;
    private Integer idCliente;
    private Integer idCidade;
    private String nomeCliente;


    public Integer getIdMensagem()
    {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem)
    {
        this.idMensagem = idMensagem;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String mensagem)
    {
        this.mensagem = mensagem;
    }

    public String getDataMensagem()
    {
        return dataMensagem;
    }

    public void setDataMensagem(String dataMensagem)
    {
        this.dataMensagem = dataMensagem;
    }

    public Integer getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente)
    {
        this.idCliente = idCliente;
    }

    public Integer getIdCidade()
    {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade)
    {
        this.idCidade = idCidade;
    }

    public String getNomeCliente()
    {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente)
    {
        this.nomeCliente = nomeCliente;
    }
}
