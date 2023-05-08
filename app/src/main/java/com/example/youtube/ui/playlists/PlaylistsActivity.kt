package com.example.youtube.ui.playlists

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import com.example.a5month_youtube.ui.playlists.PlaylistsViewModel
import com.example.youtube.core.BaseActivity
import com.example.youtube.core.BaseViewModel
import com.example.youtube.core.ConnectionLiveData
import com.example.youtube.data.model.Item
import com.example.youtube.databinding.ActivityPlaylistsBinding
import com.example.youtube.ui.detail.DetailActivity
import com.example.youtube.ui.playlists.adapter.PlaylistAdapter

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, BaseViewModel>() {

    private var adapter = PlaylistAdapter(this::onClick)


    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("desc", item.snippet.description)
        intent.putExtra("count", item.contentDetails.itemCount)
        startActivity(intent)
    }

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    
    override fun initViewModel() {

        viewModel.playlists().observe(this) {
            binding.recyclerView.adapter = adapter

            adapter.setList(it.items)
            Toast.makeText(this, it.kind, Toast.LENGTH_SHORT).show()
        }
    }

    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    companion object {
        const val ID = "id"
    }
}