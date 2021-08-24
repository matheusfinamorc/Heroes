package com.example.heroes.ui.heroes

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.heroes.R
import com.example.heroes.adapter.ListHeroesAdapter
import kotlinx.android.synthetic.main.heroes_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroesFragment : Fragment() {
    private val listHeroesViewModel: ListHeroesViewModel by viewModel()

    private val adapter by lazy {
        context?.let {
            ListHeroesAdapter(context = it)
        }
    }
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.heroes_main,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        configList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_app_bar_heroes_main, menu)
        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu.findItem(R.id.search_hero).actionView as SearchView
        val searchMenuItem = menu.findItem(R.id.search_hero)

        configSearchView(searchManager, searchView, searchMenuItem)
    }

    private fun configSearchView(
        searchManager: SearchManager,
        searchView: SearchView,
        searchMenuItem: MenuItem
    ) {
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = "Procurando por herois..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return true
            }

        })
        //searchMenuItem.icon.setVisible(false, false)
    }

    private fun search(query: String?) {
        if (query?.length!! >= 3) {
            listHeroesViewModel.getSearch(query)
            listHeroesViewModel.mSearchResponse.observe(this@HeroesFragment, {
                if (it.isSuccessful) {
                    it.body()?.let { result ->
                        adapter?.appendSearch(result)
                    }
                }
            })
        }
    }


    private fun configList() {
        heroes_main_recyclerview.layoutManager = LinearLayoutManager(context)
        heroes_main_recyclerview.adapter = adapter
    }

    private fun configRecyclerView() {
        heroes_main_recyclerview.adapter = adapter
        layoutManager = LinearLayoutManager(context)
        heroes_main_recyclerview.layoutManager = layoutManager

    }
}