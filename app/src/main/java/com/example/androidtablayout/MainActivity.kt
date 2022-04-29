package com.example.androidtablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidtablayout.databinding.ActivityMainBinding
import com.example.androidtablayout.fragments.FirstFragment
import com.example.androidtablayout.fragments.SecondFragment
import com.example.androidtablayout.fragments.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val listOfFragments = initList()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startApp()
    }

    private fun initList() = listOf(
        FirstFragment.newInstance(),
        SecondFragment.newInstance(),
        ThirdFragment.newInstance()
    )

    private fun startApp() {
        binding.vp2.adapter = MyAdapter(this, listOfFragments)

        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, position ->
            tab.text = "TAB ${(position + 1)}"
        }.attach()
    }


    class MyAdapter(fragment: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = list.size

        override fun createFragment(position: Int) = list[position]

    }
}