package app.tourdreams.com.br;

public class TipoLocal
{
    private Integer idTipoDeLocal;
    private String tipoDeLocal;


    public Integer getIdTipoDeLocal()
    {
        return idTipoDeLocal;
    }

    public void setIdTipoDeLocal(Integer idTipoDeLocal)
    {
        this.idTipoDeLocal = idTipoDeLocal;
    }

    public String getTipoDeLocal()
    {
        return tipoDeLocal;
    }

    public void setTipoDeLocal(String tipoDeLocal)
    {
        this.tipoDeLocal = tipoDeLocal;
    }

    @Override
    public String toString()
    {
        return tipoDeLocal;
    }
}
