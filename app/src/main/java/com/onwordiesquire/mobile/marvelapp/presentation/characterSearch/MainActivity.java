package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.MarvelApp;
import com.onwordiesquire.mobile.marvelapp.R;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.presentation.characterDetails.DetailsActivity;
import com.onwordiesquire.mobile.marvelapp.util.DialogFactory;
import com.onwordiesquire.mobile.marvelapp.util.EmptyStateRecyclerView;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements SearchView {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String CHARACTER_DATA_ID = TAG.concat("character_data_id");
    @Inject
    SearchPresenter searchPresenter;

    @BindView(R.id.recyclerView)
    EmptyStateRecyclerView recyclerView;
    @BindView(R.id.search_field_edt_txt)
    TextInputEditText search_field;
    @BindView(R.id.empty_view)
    View emptyView;
    private ItemListAdapter adapter;
    private Dialog errorDialog;
    private ProgressDialog progressDialog;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MarvelApp.component.inject(this);
        searchPresenter.attachView(this);
        setupRecycler();

        searchPresenter.loadLastSearchedCharacters();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void setupRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setEmptyView(emptyView);
        adapter = new ItemListAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void displayLastFiveSearches(List<RecentSearches> characters) {
        adapter.setData(characters);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        showSnackbar(message);
    }

    @Override
    public void showEmptyState() {
        String message = " The entered name is wrong";
        showSnackbar(message);
    }

    private void showSnackbar(String message) {
        if (snackbar == null) {
            snackbar = Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        snackbar.show();
    }

    @Override
    public void showCharacterDetails(CharacterData characterData) {

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(CHARACTER_DATA_ID, characterData.id());
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }

    @Override
    public void showProgressIndicator(boolean value) {
        if (value) {
            if (progressDialog == null) {
                progressDialog = DialogFactory.createProgressDialog(this, "Searching...");
            }
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @OnClick(R.id.button)
    public void searchBtnClick(View view) {

        //close soft keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        String message = "Please enter a character name before searching";

        String characterName = search_field.getText().toString();
        if (!TextUtils.isEmpty(characterName)) {
            Timber.i("Search Field Not empty");
            int millis = Calendar.getInstance().get(Calendar.MILLISECOND);
            searchPresenter.loadCharacter(characterName, BuildConfig.PUBLIC_API_KEY_MARVEL, String.valueOf(millis), BuildConfig.PRIVATE_API_KEY_MARVEL);
        } else {
            Timber.i("Search Field  empty");

            showSnackbar(message);


        }
    }

    /**
     * Adapter to handle list of recent searches
     */
    class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {

        private List<RecentSearches> data;

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data != null ? data.size() : 0;
        }

        public void setData(List<RecentSearches> data) {
            this.data = data;
        }


        class ItemViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.character_name)
            TextView textView;
            @BindView(R.id.name_container)
            LinearLayout nameContainer;

            ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

            }

            void bind(RecentSearches characterData) {
                textView.setText(characterData.name());
            }

            @OnClick(R.id.name_container)
            public void onItemClick(View view) {
                search_field.setText(data.get(getAdapterPosition()).name());
            }
        }
    }
}
