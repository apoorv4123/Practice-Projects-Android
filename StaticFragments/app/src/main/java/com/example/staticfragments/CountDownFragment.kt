package com.example.staticfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_count_down.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CountDownFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountDownFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var count = 0
        val fragmentView =  inflater.inflate(R.layout.fragment_count_down, container, false)

        fragmentView.btnSubtract.setOnClickListener {
            count --
            fragmentView.tvCount.text = count.toString()
        }

        return fragmentView
    }
}