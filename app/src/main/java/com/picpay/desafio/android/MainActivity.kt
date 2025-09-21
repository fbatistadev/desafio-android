package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
        setupObservers()

        val currentState = viewModel.uiState.value
        if (currentState.users.isEmpty() && !currentState.isLoading) {
            viewModel.loadUsers()
        }
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    progressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE

                    uiState.errorMessage?.let { errorMessage ->
                        recyclerView.visibility = View.GONE
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                        return@collect
                    }

                    if (uiState.users.isNotEmpty()) {
                        val wasEmpty = adapter.currentList.isEmpty()

                        adapter.submitList(uiState.users) {
                            recyclerView.visibility = View.VISIBLE

                            if (wasEmpty) {
                                recyclerView.postDelayed({
                                    adapter.notifyItemRangeChanged(0, uiState.users.size)
                                }, 100)
                            }
                        }
                    } else {
                        adapter.submitList(emptyList()) {
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }
}