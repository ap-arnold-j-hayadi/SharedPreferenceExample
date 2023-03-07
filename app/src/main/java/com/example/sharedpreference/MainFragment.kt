package com.example.sharedpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.sharedpreference.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MySharedPref", AppCompatActivity.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()
        initListener()
        init()
        return binding.root
    }

    private fun init() {
        if (sharedPreferences.getBoolean("isLogin", false)) {
            navigateToHomeFragment(HomeFragment())
        }
    }

    private fun initListener() {
        binding.btnPrimary.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isLogin", true)
            bundle.putString("email", binding.email.text.toString())
            val homeFragment = HomeFragment()
            homeFragment.arguments = bundle
            navigateToHomeFragment(homeFragment)
        }
    }

    private fun navigateToHomeFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
        }
    }
}