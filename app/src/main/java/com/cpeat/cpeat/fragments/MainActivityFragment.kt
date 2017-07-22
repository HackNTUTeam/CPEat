package com.cpeat.cpeat.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        (activity as AppCompatActivity).supportActionBar!!.setTitle(R.string.app_name)
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        var editPrice = view.findViewById(R.id.edit_price) as EditText

        var buttonDietEat = view.findViewById(R.id.btn_diet_eat) as Button
        buttonDietEat.setOnClickListener { _ ->
            var price = 0.0f
            try {
                price = editPrice.text.toString().toFloat();
            } catch (e: Exception) {
            }
            changeFragment(price, 0.5f)
        }

        var buttonOkEat = view.findViewById(R.id.btn_ok_eat) as Button
        buttonOkEat.setOnClickListener { _ ->
            var price = 0.0f
            try {
                price = editPrice.text.toString().toFloat();
            } catch (e: Exception) {
            }
            changeFragment(price, 1.0f)
        }

        var buttonLargeEat = view.findViewById(R.id.btn_large_eat) as Button
        buttonLargeEat.setOnClickListener { _ ->
            var price = 0.0f
            try {
                price = editPrice.text.toString().toFloat();
            } catch (e: Exception) {
            }
            changeFragment(price, 1.5f)
        }

        return view
    }

    fun changeFragment(price: Float, level: Float) {
        val newFragment = ChooseFragment()
        val bundle = Bundle()
        bundle.putFloat("price", price)
        bundle.putFloat("level", level)
        newFragment.arguments = bundle

        var manager = this.activity.supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_context, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
