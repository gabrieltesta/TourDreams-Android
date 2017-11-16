package app.tourdreams.com.br;

import java.util.Date;

public class Transacao
{
    private Integer idTransacao;
    private Date dataInicio;
    private Date dataFim;
    private Double desconto;
    private Double vlrTotal;
    private String dtTransacao;
    private String status;
    private String hotel;
    private String caminhoImagem;


    public Integer getIdTransacao()
    {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao)
    {
        this.idTransacao = idTransacao;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(Date dataFim)
    {
        this.dataFim = dataFim;
    }

    public Double getDesconto()
    {
        return desconto;
    }

    public void setDesconto(Double desconto)
    {
        this.desconto = desconto;
    }

    public Double getVlrTotal()
    {
        return vlrTotal;
    }

    public void setVlrTotal(Double vlrTotal)
    {
        this.vlrTotal = vlrTotal;
    }

    public String getDtTransacao()
    {
        return dtTransacao;
    }

    public void setDtTransacao(String dtTransacao)
    {
        this.dtTransacao = dtTransacao;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getHotel()
    {
        return hotel;
    }

    public void setHotel(String hotel)
    {
        this.hotel = hotel;
    }

    public String getCaminhoImagem()
    {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem)
    {
        this.caminhoImagem = caminhoImagem;
    }
}
