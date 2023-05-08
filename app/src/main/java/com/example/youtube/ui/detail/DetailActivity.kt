package com.example.youtube.ui.detail

import android.widget.Toast
import com.example.youtube.core.BaseActivity
import com.example.youtube.core.BaseViewModel
import com.example.youtube.databinding.ActivityDetailBinding
import com.example.youtube.ui.playlists.PlaylistsActivity

class DetailActivity(override val viewModel: BaseViewModel) : BaseActivity<ActivityDetailBinding, BaseViewModel>() {
    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }


    override fun initListener() {
        super.initListener()
        val result  = intent.getStringExtra(PlaylistsActivity.ID)
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }


}