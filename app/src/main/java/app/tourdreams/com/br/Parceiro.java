package app.tourdreams.com.br;


public class Parceiro
{
    private Integer idParceiro;
    private String cnpj;
    private String nomeParceiro;
    private String emailParceiro;
    private String caminhoImagem;


    public Integer getIdParceiro()
    {
        return idParceiro;
    }

    public void setIdParceiro(Integer idParceiro)
    {
        this.idParceiro = idParceiro;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getNomeParceiro()
    {
        return nomeParceiro;
    }

    public void setNomeParceiro(String nomeParceiro)
    {
        this.nomeParceiro = nomeParceiro;
    }

    public String getEmailParceiro()
    {
        return emailParceiro;
    }

    public void setEmailParceiro(String emailParceiro)
    {
        this.emailParceiro = emailParceiro;
    }

    public String getCaminhoImagem()
    {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem)
    {
        this.caminhoImagem = caminhoImagem;
    }

    @Override
    public String toString()
    {
        return nomeParceiro;
    }
}
