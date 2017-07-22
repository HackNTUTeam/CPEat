package com.cpeat.cpeat.fragments

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
        var buttonDietEat = view.findViewById(R.id.btn_diet_eat) as Button
        buttonDietEat.setOnClickListener { view ->
            var price = 0.0
            try {
                price = editPrice.text.toString().toDouble();
            } finally {

            }
            changeFragment(price, 0.5)
        }

        var buttonOkEat = view.findViewById(R.id.btn_ok_eat) as Button
        buttonOkEat.setOnClickListener { view ->
            var price = 0.0
            try {
                price = editPrice.text.toString().toDouble();
            } finally {

            }
            changeFragment(price, 1.0)
        }

        var buttonLargeEat = view.findViewById(R.id.btn_diet_eat) as Button
        buttonLargeEat.setOnClickListener { view ->
            var price = 0.0
            try {
                price = editPrice.text.toString().toDouble();
            } finally {

            }
            changeFragment(price, 1.5)
        }
        return view
    }

    fun changeFragment(price: Double, level: Double) {
        val newFragment = ChooseFragment()
        val bundle = Bundle()
        bundle.putDouble("price", price)
        bundle.putDouble("price", level)
        newFragment.arguments = bundle

        var manager = this.activity.supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_context, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
