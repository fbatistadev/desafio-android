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

        Log.d("ImageLoad", "Binding user: ${user.name}, img URL: ${user.img}")

        // Smart fallback based on URL
        val fallbackDrawable = if (user.img.contains("men")) {
            R.drawable.avatar_male
        } else if (user.img.contains("women")) {
            R.drawable.avatar_female
        } else {
            R.drawable.avatar_placeholder
        }

        picture.dispose()
        picture.setImageResource(fallbackDrawable)
        progressBar.visibility = View.VISIBLE

        Log.d("ImageLoad", "About to start loading: ${user.img}")

        picture.load(user.img) {
            placeholder(fallbackDrawable)
            error(fallbackDrawable)
            crossfade(200)

            listener(
                onStart = { _ ->
                    Log.d("ImageLoad", "STARTED loading: ${user.name}")
                    progressBar.visibility = View.VISIBLE
                },
                onError = { _, result ->
                    Log.e("ImageLoad", "ERROR loading ${user.name}: ${result.throwable}")
                    Log.e("ImageLoad", "URL was: ${user.img}")
                    progressBar.visibility = View.GONE
                },
                onSuccess = { _, _ ->
                    Log.d("ImageLoad", "SUCCESS loading: ${user.name}")
                    progressBar.visibility = View.GONE
                }
            )
        }
    }
}