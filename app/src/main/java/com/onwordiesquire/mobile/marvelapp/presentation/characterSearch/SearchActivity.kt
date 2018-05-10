package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.onwordiesquire.mobile.marvelapp.MarvelApp
import com.onwordiesquire.mobile.marvelapp.R
import com.onwordiesquire.mobile.marvelapp.data.model.MarvelCharacter
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches
import com.onwordiesquire.mobile.marvelapp.presentation.BaseActivity
import com.onwordiesquire.mobile.marvelapp.presentation.characterDetails.DetailsActivity
import com.onwordiesquire.mobile.marvelapp.util.EmptyStateRecyclerView
import com.onwordiesquire.mobile.marvelapp.util.MARVEL_CHARACTER_ID
import com.onwordiesquire.mobile.marvelapp.util.textValue
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView {

    @Inject lateinit var searchPresenter: SearchPresenter

    @BindView(R.id.recyclerView) lateinit var recyclerView: EmptyStateRecyclerView
    @BindView(R.id.search_field_edt_txt) lateinit var searchField: TextInputEditText
    @BindView(R.id.empty_view) lateinit var emptyView: View

    val snackbar: Snackbar by lazy {
        Snackbar.make(recyclerView, "", Snackbar.LENGTH_LONG)
    }

    override fun displayLastFiveSearches(searches: List<RecentSearches>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        showSnackbar(message)
    }

    override fun showEmptyState(message: String) {
        showSnackbar(message)
    }


    override fun showCharacterDetails(marvelCharacter: MarvelCharacter) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            intent.putExtra(MARVEL_CHARACTER_ID, marvelCharacter.id)
        }
        startActivity(intent)
        finish()
    }

    override fun showProgress(visible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getView(): AppCompatActivity = this

    override fun initializePresenter() {
        presenter = searchPresenter
        (presenter as SearchPresenter).attachView(this)
    }

    private fun showSnackbar(message: String) {
        with(snackbar) {
            setText(message)
            show()
        }
    }

    @OnClick(R.id.button)
    fun searchBtnClick() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)

        searchPresenter.onSearchClick(searchField.textValue)

    }

    override fun initList() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            setEmptyView(emptyView)
            adapter = ItemListAdapter({ characterName ->
                searchField.setText(characterName)
            })
        }
    }

    override fun initializeDagger() {
        MarvelApp.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeDagger()
        unbinder = ButterKnife.bind(this)
        searchPresenter.attachView(this)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
        searchPresenter.detachView()
    }

}
