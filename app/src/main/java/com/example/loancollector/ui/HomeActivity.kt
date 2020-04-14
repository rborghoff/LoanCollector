package com.example.loancollector.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.loancollector.AddLoanActivity
import com.example.loancollector.R
import com.example.loancollector.model.Loan
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

const val REQUEST_CODE = 100;
class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var view: View

    private var fragment: Fragment? = null
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        fragment = supportFragmentManager.findFragmentById(R.id.homeFragment2)

        viewModel = ViewModelProviders.of(this).get(AppViewModel ::class.java)

        fabAddLoan.setOnClickListener {
            onAddClick()
        }
//
//        setToolbar()
        initNavigation()
    }

    private fun onAddClick() {
        // From HomeActivity to AddActivity.
        val nextIntent = Intent(this@HomeActivity, AddLoanActivity::class.java)
        startActivityForResult(nextIntent, REQUEST_CODE)

        // Animation to fade into the AddActivity.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun setToolbar() {

        bottomNavigation = findViewById(R.id.navView)
        view = findViewById(R.id.parentMain)
    }

    private fun initNavigation() {
        // The NavController.
        val navController = findNavController(R.id.navHostFragment_dupe)

        NavigationUI.setupWithNavController(navView, navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment2 -> showBottomNavigationBar(true)

            }
        }
    }
    private fun showBottomNavigationBar(visible: Boolean) {
        when (visible) {
            true -> navView.visibility = View.VISIBLE
            false -> navView.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    val reminder = data!!.getParcelableExtra<Loan>(AddLoanActivity.EXTRA_ITEM)


                    viewModel.insertLoan(reminder)

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
