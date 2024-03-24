package com.lekoal.astonintensiv4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lekoal.astonintensiv4.databinding.ActivityMainBinding
import com.lekoal.astonintensiv4.part_1.ui.AFragment
import com.lekoal.astonintensiv4.part_2.ui.UserListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonsListeners()
    }

    private fun setButtonsListeners() {
        binding.partOneButton.setOnClickListener {
            if (supportFragmentManager.findFragmentByTag(AFragment.TAG) == null) {
                val aFragment = AFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .add(binding.fragmentContainer.id, aFragment, AFragment.TAG)
                    .addToBackStack(AFragment.TAG)
                    .commit()
            }
        }
        binding.partTwoButton.setOnClickListener {
            if (supportFragmentManager.findFragmentByTag(UserListFragment.TAG) == null) {
                val userListFragment = UserListFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .add(binding.fragmentContainer.id, userListFragment, UserListFragment.TAG)
                    .addToBackStack(UserListFragment.TAG)
                    .commit()
            }
        }
    }
}