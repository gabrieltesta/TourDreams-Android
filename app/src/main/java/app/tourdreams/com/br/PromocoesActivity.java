package app.tourdreams.com.br;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class PromocoesActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        List<Banner> banners = new ArrayList<>();

        banners.add(new DrawableBanner(R.drawable.bglogin));
        banners.add(new DrawableBanner(R.drawable.bgmenuregistro));
        banners.add(new DrawableBanner(R.drawable.bglogin));
        banners.add(new DrawableBanner(R.drawable.bgmenuregistro));
        banners.add(new DrawableBanner(R.drawable.bglogin));
        banners.add(new DrawableBanner(R.drawable.bgmenuregistro));

        bannerSlider.setBanners(banners);
    }

}
