package com.example.staticfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_count_up.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CountUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_count_up, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var count = 0

        view.btnAdd.setOnClickListener {
            count ++
            view.tvCount.text = count.toString()
        }
    }

}