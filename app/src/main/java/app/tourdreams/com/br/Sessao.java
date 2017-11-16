package app.tourdreams.com.br;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.view.Menu;

public final class Sessao
{
    private static boolean statusLogin = false;
    private static boolean parceiro = false;
    private static boolean usuario = false;
    private static String login = null;

    private Sessao()
    {
        statusLogin = false;
        parceiro = false;
        usuario = false;
        login = null;
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

    public static String getLogin()
    {
        return login;
    }

    public static void setLogin(String login)
    {
        Sessao.login = login;
    }

    public static void deslogarUsuario(final Context context, final NavigationView navigationView)
    {
        new AlertDialog.Builder(context)
                .setTitle("Logout")
                .setMessage("Tem certeza que deseja efetuar o logout?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Sessao.setStatusLogin(false);
                                Sessao.setParceiro(false);
                                Sessao.setUsuario(false);
                                Sessao.setLogin(null);
                                Menu menu = navigationView.getMenu();
                                menu.findItem(R.id.nav_login).setVisible(true);
                                menu.findItem(R.id.nav_registrar).setVisible(true);
                                menu.findItem(R.id.nav_perfil_parceiro).setVisible(false);
                                menu.findItem(R.id.nav_perfil_usuario).setVisible(false);
                                menu.findItem(R.id.nav_logoff).setVisible(false);
                                new AlertDialog.Builder(context)
                                        .setTitle("Logoff")
                                        .setMessage("Logoff efetuado com sucesso.")
                                        .setNeutralButton("OK", new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i)
                                            {
                                                context.startActivity(new Intent(context, MainActivity.class));
                                            }
                                        })
                                        .show();
                            }
                        }, 1000);
                    }
                })
                .setNegativeButton("NÃO", null)
                .show();
    }
}
