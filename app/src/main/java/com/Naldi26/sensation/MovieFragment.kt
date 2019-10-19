package com.Naldi26.sensation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.Naldi26.sensation.connection.ConfigRetrofit
import com.Naldi26.sensation.connection.MovieInterface
import com.hanifabdullah21.smkcoding.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.Android
import org.jetbrains.anko.support.v4.toast
import java.util.concurrent.ScheduledExecutorService

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_movie, container, false)
        getListMovie()

//
//        val list = Movie.listMovie
//                as ArrayList<MovieModel>
//        val layoutmanager =
//            LinearLayoutManager(activity)
//        val adapter =
//            MovieAdapter(list,activity!!.applicationContext)
//
//        rootView.rv_movie.apply {
//            layoutManager = layoutmanager
//             setAdapter(adapter)
        //       }

        return rootView
    }

    private fun getListMovie() {
        val movieNowPlaying =
            ConfigRetrofit.retrofitConfig()
                .create(MovieInterface::class.java)
                .getListMovieNowPlaying(
                    "d2c37740b8537e8828565b9dd89d62ad"
                )
        movieNowPlaying
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                //Menerima response yang berhasil

                val list = response.results
                val layoutmanager =
                    LinearLayoutManager(activity)
                val adapter =
                    MovieAdapter(list, activity!!.applicationContext)

                rootView.rv_movie.apply {
                    layoutManager = layoutmanager
                }
                toast("Berhasil")
            }, {
                //Menerima response yang gagal
                toast("gagal")
            }
            )
    }


}
