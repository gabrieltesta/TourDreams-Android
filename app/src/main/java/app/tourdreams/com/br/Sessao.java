package app.tourdreams.com.br;

public final class Sessao
{
    private static boolean statusLogin = false;

    private Sessao()
    {
        statusLogin = false;
    }

    public static void setStatusLogin(boolean status)
    {
        statusLogin=status;
    }

    public static boolean getStatusLogin()
    {
        return statusLogin;
    }


}
