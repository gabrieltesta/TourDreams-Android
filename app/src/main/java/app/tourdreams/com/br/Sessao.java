package app.tourdreams.com.br;

public final class Sessao
{
    private static boolean statusLogin = false;
    private static boolean parceiro = false;
    private static boolean usuario = false;

    private Sessao()
    {
        statusLogin = false;
        parceiro = false;
        usuario = false;
    }

    public static void setStatusLogin(boolean status)
    {
        statusLogin=status;
    }

    public static boolean getStatusLogin()
    {
        return statusLogin;
    }

    public static boolean isParceiro()
    {
        return parceiro;
    }

    public static void setParceiro(boolean status)
    {
        parceiro = status;
    }

    public static boolean isUsuario()
    {
        return usuario;
    }

    public static void setUsuario(boolean status)
    {
        usuario = status;
    }


}
