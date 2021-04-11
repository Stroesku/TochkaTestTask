package space.stroesku.tochkatesttask.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_general.*
import space.stroesku.tochkatesttask.databinding.ActivityGeneralBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeneralBinding
    private lateinit var adapterUserList: UserListAdapter
    private lateinit var _layoutManager: LinearLayoutManager
    private lateinit var viewModelMain: MainViewModel
    private var isLoading = false
    private var page = 1
    private var totalPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneralBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapterUserList = UserListAdapter()
        viewModelMain = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        binding.apply {
            recycler_View.apply {
                _layoutManager = LinearLayoutManager(this@MainActivity)
                adapterUserList = UserListAdapter()
                layoutManager = _layoutManager
                adapter = adapterUserList
                adapterUserList.submitList(listOf())

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (_layoutManager.findLastVisibleItemPosition() == _layoutManager.childCount - 5) {
                            searchUsers()
                        }
                    }
                })
            }

            //click to search Button
            search_btn.setOnClickListener {
                searchUsers()
            }

            et_query.setOnKeyListener { v, keycode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keycode == KeyEvent.KEYCODE_ENTER) {
                    searchUsers()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        //set observer to viewModel's data
        viewModelMain.getSearchUsers().observe(this, {
            if (it != null) {
                adapterUserList.submitList(it)
                showLoading(false)
            }
        })
    }


    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun searchUsers() {

        binding.apply {
            RxTextView.textChanges(etQuery)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    val query = etQuery.text.toString()
                    if (query.length > 3) {
                        showLoading(true)
                        viewModelMain.setSearchUsers(query)
                    }
                }
        }
    }
}