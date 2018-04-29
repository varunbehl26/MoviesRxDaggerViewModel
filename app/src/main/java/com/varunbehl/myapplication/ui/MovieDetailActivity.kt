package com.varunbehl.myapplication.ui

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.varunbehl.myapplication.MyApplication
import com.varunbehl.myapplication.R
import com.varunbehl.myapplication.adapter.MovieFragmentPagerAdapter
import com.varunbehl.myapplication.dataClass.CastItem
import com.varunbehl.myapplication.dataClass.MovieDetail
import com.varunbehl.myapplication.dataClass.PostersItem
import com.varunbehl.myapplication.databinding.ActivityMovieDetailBinding
import com.varunbehl.myapplication.databinding.CarousalItemLayoutBinding
import com.varunbehl.myapplication.fragment.CastFragment
import com.varunbehl.myapplication.fragment.ImagesFragment
import com.varunbehl.myapplication.network.DataInterface
import com.varunbehl.myapplication.viewmodel.MovieDetailViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), CastFragment.OnListFragmentInteractionListener, ImagesFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: PostersItem?) {

    }

    override fun onListFragmentInteraction(item: CastItem?) {

    }

    var dataInterface: DataInterface? = null
        @Inject set


    private lateinit var binding: ActivityMovieDetailBinding
    private var menu: Menu? = null
    private lateinit var model: MovieDetailViewModel

    private var movieItemId: Int = 0

    private lateinit var movieDetailResponse: MovieDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.background.alpha = 0
        binding.cordinatorLayout.visibility = GONE
        binding.progressBar.visibility = GONE

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        (application as MyApplication).appComponent?.inject(this)

        model = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        movieItemId = intent.extras.getInt(MainActivity.EXTRA_MOVIE_ITEM, -1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = intent.extras.getString(MainActivity.EXTRA_IMAGE_TRANSITION_NAME)
            binding.image.transitionName = imageTransitionName
        }


        model.getMovieDetails(movieItemId)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    movieDetailResponse = it
                    binding.value = it
                    supportActionBar!!.title = it.title
                    binding.cordinatorLayout.visibility = VISIBLE
                    binding.progressBar.visibility = GONE

                    Glide.with(this)
                            .load(getImageUri(it.posterPath))
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.placeholder)
                            .into(binding.image)

                    loadPallet(it)

                    setCaraousel(it)

                    setGenre(it)

                    initializeAdapter(it)

                    setData(it)

                }, {
                    it.printStackTrace()
                    Log.v("---------", it.toString())
                })
    }


    private fun setData(movieDetail: MovieDetail) {
//        var dataFrom = Date()
//        val df = SimpleDateFormat("yyyy-MM-dd")
//'
//        var releasedate: Date = df.format(movieDetail.releaseDate)
        val runtime = (movieDetail.runtime)
        val hour = runtime / 60
        val min = runtime % 60
        val actualRuntime = "$hour hours $min min"

        binding.details.text = " " + actualRuntime + " | " + movieDetail.productionCountries?.get(0)?.name


    }

    private fun setGenre(it: MovieDetail) {
        if (!it.genres.isNullOrEmpty()) {
            val genres = StringBuilder()
            for (id in it.genres) {
                genres.append(id.name)
                genres.append(", ")
            }
            binding.chipGroup.text = genres.toString()
        }
    }

    private fun setCaraousel(it: MovieDetail) {
        if (!it.images.backdrops!!.isEmpty()) {
            binding.ivTopPoster.setViewListener { position ->
                val carouselBinding = DataBindingUtil.inflate<CarousalItemLayoutBinding>(layoutInflater, R.layout.carousal_item_layout, null, true)
                Glide.with(this)
                        .load(getImageUri(it.images.backdrops[position + 1].filePath))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(R.drawable.placeholder)
                        .into(carouselBinding.carousalImage)
                carouselBinding.carousalImage.tag = position + 1
                carouselBinding.root
            }
            if (it.images.backdrops.size > 6) {
                binding.ivTopPoster.pageCount = 5
            } else {
                binding.ivTopPoster.pageCount = it.images.backdrops.size - 1
            }
        } else {
            binding.ivTopPoster.visibility = GONE
        }
    }

    private fun loadPallet(it: MovieDetail) {
        Glide.with(this)
                .load(getImageUri(it.posterPath))
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.placeholder)
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(drawable: Drawable, transition: com.bumptech.glide.request.transition.Transition<in Drawable>?) {
                        binding.viewConstraintLayout.background = drawable
                        binding.viewConstraintLayout.background.alpha = 80
                    }
                })
    }

    fun getImageUri(uri: String): String {
        val IMAGE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"
        return "$IMAGE_POSTER_BASE_URL/$uri"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            R.id.action_favorite -> {
                model.saveMovieAsBookmark(movieDetailResponse)
                menu?.getItem(0)?.icon = resources.getDrawable(R.drawable.ic_add_start_neon)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initializeAdapter(it: MovieDetail) {
        binding.pager.adapter = MovieFragmentPagerAdapter(supportFragmentManager, it)
        binding.pager.offscreenPageLimit = 2
        binding.tabLayout.tabMode = com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
        binding.tabLayout.setupWithViewPager(binding.pager)
        binding.pager.currentItem = 0
        binding.tabLayout.getTabAt(0)?.select()
    }
}
