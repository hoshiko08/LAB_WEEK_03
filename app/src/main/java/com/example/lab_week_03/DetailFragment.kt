package com.example.lab_week_03

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    private lateinit var coffeeTitle: TextView
    private lateinit var coffeeDesc: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setupCoffeeData()
    }

    private fun initializeViews(view: View) {
        coffeeTitle = view.findViewById(R.id.coffee_title)
        coffeeDesc = view.findViewById(R.id.coffee_desc)
    }

    private fun setupCoffeeData() {
        arguments?.getInt(COFFEE_ID_KEY)?.let { coffeeId ->
            setCoffeeData(coffeeId)
        }
    }

    fun setCoffeeData(id: Int) {
        val (titleRes, descRes) = when (id) {
            R.id.affogato -> R.string.affogato_title to R.string.affogato_desc
            R.id.americano -> R.string.americano_title to R.string.americano_desc
            R.id.latte -> R.string.latte_title to R.string.latte_desc
            else -> return // Handle unknown ID
        }

        coffeeTitle.text = getString(titleRes)
        coffeeDesc.text = getString(descRes)
    }

    companion object {
        private const val COFFEE_ID_KEY = "coffee_id"

        fun newInstance(coffeeId: Int): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(COFFEE_ID_KEY, coffeeId)
                }
            }
        }
    }
}