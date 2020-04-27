package com.example.loancollector.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.loancollector.R
import com.example.loancollector.ui.HomeActivity
import com.example.loancollector.ui.fragments.RatedFragmentArgs
import kotlinx.android.synthetic.main.fragment_rated.*


class RatedFragment : Fragment() {
    private val args: RatedFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rated, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTnx.text = getString(R.string.rate_thank_you,args.rating)
        goToHomeScreen()


    }
    private val splashDelay: Long = 2500
    private fun goToHomeScreen(){

        // Use Handler to wait 1 second before opening the HomeActivity.
        Handler().postDelayed({
            val intent = Intent(getActivity(), HomeActivity::class.java)
            startActivity(intent)


            // Call finish, so user can not return to splash screen.

        }, splashDelay )

    }
}
