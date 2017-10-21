package app.tourdreams.com.br;

public class Quarto
{
    private Integer idQuarto;
    private String nome;
    private Double valorDiario;
    private String descricao;
    private Integer maxHospedes;
    private Integer qtdQuartos;
    private Integer idHotel;


    public Integer getIdQuarto()
    {
        return idQuarto;
    }

    public void setIdQuarto(Integer idQuarto)
    {
        this.idQuarto = idQuarto;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Double getValorDiario()
    {
        return valorDiario;
    }

    public void setValorDiario(Double valorDiario)
    {
        this.valorDiario = valorDiario;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Integer getMaxHospedes()
    {
        return maxHospedes;
    }

    public void setMaxHospedes(Integer maxHospedes)
    {
        this.maxHospedes = maxHospedes;
    }

    public Integer getQtdQuartos()
    {
        return qtdQuartos;
    }

    public void setQtdQuartos(Integer qtdQuartos)
    {
        this.qtdQuartos = qtdQuartos;
    }

    public Integer getIdHotel()
    {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel)
    {
        this.idHotel = idHotel;
    }
}
