package app.tourdreams.com.br;

public class Usuario
{
    private Integer idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String cpf;
    private String rg;
    private Integer milhasPontuacao;
    private String caminhoImagem;
    private String login;
    private String tipoLocal;
    private String telefone;


    public Integer getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente)
    {
        this.idCliente = idCliente;
    }

    public String getNomeCliente()
    {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente)
    {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente()
    {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente)
    {
        this.emailCliente = emailCliente;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public Integer getMilhasPontuacao()
    {
        return milhasPontuacao;
    }

    public void setMilhasPontuacao(Integer milhasPontuacao)
    {
        this.milhasPontuacao = milhasPontuacao;
    }

    public String getCaminhoImagem()
    {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem)
    {
        this.caminhoImagem = caminhoImagem;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getTipoLocal()
    {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal)
    {
        this.tipoLocal = tipoLocal;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }
}
