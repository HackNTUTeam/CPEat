package com.cpeat.cpeat.com.cpeat.cpeat.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.cpeat.cpeat.R

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    val TAG = "MainActivityFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        var editPrice = view.findViewById(R.id.edit_price) as EditText
        var buttonStart = view.findViewById(R.id.btn_start) as Button
        buttonStart.setOnClickListener { view ->
            val newFragment = ChooseFragment()
            val bundle = Bundle()
            bundle.putDouble("price", editPrice.text.toString().toDouble())
            newFragment.arguments = bundle

            var manager = this.activity.supportFragmentManager
            var transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_context, newFragment)
            transaction.addToBackStack(null);
            transaction.commit()
        }
        return view
    }

}