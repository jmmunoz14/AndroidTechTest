package com.example.androidtechtest.Main.View

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.androidtechtest.Models.Team
import com.example.androidtechtest.R
import com.example.androidtechtest.databinding.ActivityDetailBinding
import android.content.Intent
import android.net.Uri


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.activity_detail, null, false)
        setContentView(binding.root)

        team = intent.getParcelableExtra<Team>("team")!!

        binding.name.text = team.strAlternate
        binding.description.text = team.strDescriptionEN
        binding.foundation.text = "Foundation year: " + team.intFormedYear

        var chrome = binding.iccr
        var facebook = binding.icfb
        var twitter = binding.ictw
        var insta = binding.icinst

        chrome.visibility = View.GONE
        facebook.visibility = View.GONE
        twitter.visibility = View.GONE
        insta.visibility = View.GONE

        var chromeUrl = team.strWebsite
        var facebookUrl = team.strFacebook
        var twitterUrl = team.strTwitter
        var instaUrl = team.strInstagram

        if (chromeUrl != "") {
            chrome.visibility = View.VISIBLE

            chrome.setOnClickListener {
                if (!chromeUrl.startsWith("http://") && !chromeUrl.startsWith("https://")) {
                    chromeUrl = "http://$chromeUrl";
                }

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(chromeUrl))
                startActivity(browserIntent)
            }
        }
        if (facebookUrl != "") {
            facebook.visibility = View.VISIBLE

            facebook.setOnClickListener {
                if (!facebookUrl.startsWith("http://") && !facebookUrl.startsWith("https://")) {
                    facebookUrl = "http://$facebookUrl";
                }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl))
                startActivity(browserIntent)
            }
        }
        if (twitterUrl != "") {
            twitter.visibility = View.VISIBLE

            if (!twitterUrl.startsWith("http://") && !twitterUrl.startsWith("https://")) {
                twitterUrl = "http://$twitterUrl";
            }
            twitter.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
                startActivity(browserIntent)
            }
        }
        if (instaUrl != "") {
            insta.visibility = View.VISIBLE

            if (!instaUrl.startsWith("http://") && !instaUrl.startsWith("https://")) {
                instaUrl = "http://$instaUrl";
            }
            insta.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(instaUrl))
                startActivity(browserIntent)
            }
        }

        var progressBar1 = binding.progressBar1
        var progressBar2 = binding.progressBar2
        var reqOpt: RequestOptions = RequestOptions
            .fitCenterTransform()
            .transform(RoundedCorners(5))
            .diskCacheStrategy(DiskCacheStrategy.ALL) // It will cache your image after loaded for first time
        // Overrides size of downloaded image and converts it's bitmaps to your desired image size;
        Glide.with(this).load(team.strTeamJersey).listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar1.visibility = View.GONE
                return false
            }
        }).apply(reqOpt)
            .into(binding.jersey)

        // Overrides size of downloaded image and converts it's bitmaps to your desired image size;
        Glide.with(this).load(team.strTeamBadge).listener(object :
            RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar2.visibility = View.GONE
                return false
            }
        }).apply(reqOpt)
            .into(binding.badge)


    }


}