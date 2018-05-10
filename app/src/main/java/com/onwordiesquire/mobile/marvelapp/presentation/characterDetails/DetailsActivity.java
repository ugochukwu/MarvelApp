package com.onwordiesquire.mobile.marvelapp.presentation.characterDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.onwordiesquire.mobile.marvelapp.R;
import com.onwordiesquire.mobile.marvelapp.data.model.MarvelCharacter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by michelonwordi on 10/25/16.
 */

public class DetailsActivity extends AppCompatActivity implements DetailsView {

    @BindView(R.id.name)
    TextView nameTxtView;
    @BindView(R.id.thumbnail)
    ImageView imageView;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.details_toolbar)
    Toolbar toolbar;

    @Inject
    DetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        // TODO: 07.10.17  obtain the character id

        String characterDataId = "";
        presenter.attachView(this);
        presenter.loadCharacter(characterDataId);

        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
            // TODO: 07.10.17 create intent
        }
        return super.onOptionsItemSelected(item);
    }
// TODO: 07.10.17
//    @Override
//    public void displayCharacterDetails(CharacterData characterData) {
//        String name = characterData.name();
//        nameTxtView.setText(name);
//        description.setText(TextUtils.isEmpty(characterData.description()) ? getString(R.string.desc_not_available) : characterData.description());
//        Thumbnail thumbnail = characterData.thumbnail();
//        if (thumbnail != null) {
//            String portrait_xlarge = thumbnail.path().concat("/portrait_xlarge").concat(".").concat(thumbnail.extension());
//            Timber.i("Loading image at path %s ....", portrait_xlarge);
//
//            BitmapImageViewTarget bimt = new BitmapImageViewTarget(imageView) {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    super.onResourceReady(resource, glideAnimation);
//                    Palette.from(resource).generate(palette -> {
//                        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//                        Palette.Swatch lightVibrantSwatch = palette.getDarkMutedSwatch();
//
//                        if (vibrantSwatch != null) {
//                            nameTxtView.setTextColor(vibrantSwatch.getRgb());
//                        }
//
//                    });
//                }
//            };
//
//            Glide.with(this).load(portrait_xlarge)
//                .asBitmap()
//                .placeholder(R.mipmap.ic_marvel_launcher)
//                .into(bimt);
//        }
//    }

    @Override
    public void showError(String message) {
        Snackbar.make(nameTxtView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onBackPressed() {
        // TODO: 07.10.17
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void displayCharacterDetails(MarvelCharacter characterData) {
        // TODO: 07.10.17 to be implemented
    }
}
