package app.tourdreams.com.br;

public class Hotel
{
    private Integer idHotel;
    private String hotel;
    private String caminhoImagem;
    private Integer qtdEstrelas;
    private Double valorMinimo;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer avaliacao;
    private Integer qtdAvaliacoes;


    public Integer getIdHotel()
    {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel)
    {
        this.idHotel = idHotel;
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

    public Integer getQtdEstrelas()
    {
        return qtdEstrelas;
    }

    public void setQtdEstrelas(Integer qtdEstrelas)
    {
        this.qtdEstrelas = qtdEstrelas;
    }

    public Double getValorMinimo()
    {
        return valorMinimo;
    }

    public void setValorMinimo(Double valorMinimo)
    {
        this.valorMinimo = valorMinimo;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getUf()
    {
        return uf;
    }

    public void setUf(String uf)
    {
        this.uf = uf;
    }

    public Integer getAvaliacao()
    {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao)
    {
        this.avaliacao = avaliacao;
    }

    public Integer getQtdAvaliacoes()
    {
        return qtdAvaliacoes;
    }

    public void setQtdAvaliacoes(Integer qtdAvaliacoes)
    {
        this.qtdAvaliacoes = qtdAvaliacoes;
    }
}
