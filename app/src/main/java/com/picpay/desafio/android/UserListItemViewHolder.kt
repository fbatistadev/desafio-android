package com.picpay.desafio.android

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.dispose
import coil.load
import de.hdodenhof.circleimageview.CircleImageView

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val picture: CircleImageView = itemView.findViewById(R.id.picture)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val username: TextView = itemView.findViewById(R.id.username)
    private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

    fun bind(user: User) {
        name.text = user.name
        username.text = user.username

        // Clear any previous image requests to avoid conflicts
        picture.dispose()

        // Smart fallback based on URL
        val fallbackDrawable = if (user.img.contains("men")) {
            R.drawable.avatar_male
        } else if (user.img.contains("women")) {
            R.drawable.avatar_female
        } else {
            R.drawable.avatar_placeholder
        }

        // Show progress bar and placeholder immediately
        progressBar.visibility = View.VISIBLE
        picture.setImageResource(fallbackDrawable)

        picture.load(user.img) {
            placeholder(fallbackDrawable)
            error(fallbackDrawable)
            crossfade(200)
            memoryCacheKey("user_avatar_${user.id}")
            diskCacheKey("user_avatar_${user.id}")
            allowHardware(true)
            listener(
                onError = { _, result ->
                    progressBar.visibility = View.GONE
                    Log.e("ImageLoad", "Failed to load ${user.name}: ${result.throwable}")
                },
                onSuccess = { _, _ ->
                    progressBar.visibility = View.GONE
                    Log.d("ImageLoad", "Successfully loaded ${user.name}")
                }
            )
        }
    }
}