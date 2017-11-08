package app.tourdreams.com.br;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    TextView img_norte, img_nordeste, img_centrooeste, img_sudeste, img_sul;
    NavigationView navigationView;
    Menu menu;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        configurarRegioes();

        if(Sessao.getStatusLogin() && Sessao.isUsuario())
        {
            alterarMenuLateralUsuario();
        }

        if(Sessao.getStatusLogin() && Sessao.isParceiro())
        {
            alterarMenuLateralParceiro();
        }
    }

    private void alterarMenuLateralUsuario()
    {
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_registrar).setVisible(false);
        menu.findItem(R.id.nav_perfil_parceiro).setVisible(false);
        menu.findItem(R.id.nav_perfil_usuario).setVisible(true);
        menu.findItem(R.id.nav_logoff).setVisible(true);
    }

    private void alterarMenuLateralParceiro()
    {
        menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_registrar).setVisible(false);
        menu.findItem(R.id.nav_perfil_parceiro).setVisible(true);
        menu.findItem(R.id.nav_perfil_usuario).setVisible(false);
        menu.findItem(R.id.nav_logoff).setVisible(true);
    }

    public void configurarRegioes()
    {

        img_norte = (TextView) findViewById(R.id.img_norte);
        img_nordeste = (TextView) findViewById(R.id.img_nordeste);
        img_centrooeste = (TextView) findViewById(R.id.img_centrooeste);
        img_sudeste = (TextView) findViewById(R.id.img_sudeste);
        img_sul = (TextView) findViewById(R.id.img_sul);

        img_norte.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buscarRegiao("Norte");
            }
        });

        img_nordeste.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buscarRegiao("Nordeste");
            }
        });

        img_centrooeste.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buscarRegiao("Centro-Oeste");
            }
        });

        img_sudeste.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buscarRegiao("Sudeste");
            }
        });

        img_sul.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buscarRegiao("Sul");
            }
        });
    }

    public void buscarRegiao(String regiao)
    {
        Intent intent = new Intent(this, BuscaActivity.class);
        intent.putExtra("regiao", regiao);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.busca_simples);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Intent busca = new Intent(context, BuscaActivity.class);
                busca.putExtra("busca", query);
                startActivity(busca);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.busca_simples)
        {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        switch(item.getItemId())
        {
            case R.id.nav_home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.nav_sobrenos:
                startActivity(new Intent(this, SobreNosActivity.class));
                break;
            case R.id.nav_melhoresdestinos:
                startActivity(new Intent(this, MelhoresDestinosActivity.class));
                break;
            case R.id.nav_nossosparceiros:
                startActivity(new Intent(this, NossosParceirosActivity.class));
                break;
            case R.id.nav_conhecaseudestino:
                startActivity(new Intent(this, ConhecaseuDestinoActivity.class));
                break;
            case R.id.nav_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.nav_faleconosco:
                startActivity(new Intent(this, FaleconoscoActivity.class));
                break;
            case R.id.nav_registrar:
                startActivity(new Intent(this, RegistroUsuarioActivity.class));
                break;
            case R.id.nav_promocoes:
                startActivity(new Intent(this, PromocoesActivity.class));
                break;
            case R.id.nav_perfil_usuario:
                startActivity(new Intent(this, PerfilUsuarioActivity.class));
                break;
            case R.id.nav_perfil_parceiro:
                startActivity(new Intent(this, PerfilParceiroActivity.class));
                break;
            case R.id.nav_logoff:
                Sessao.deslogarUsuario(context, navigationView);
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
