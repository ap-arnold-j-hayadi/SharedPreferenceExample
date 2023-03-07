package com.example.sharedpreference

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.sharedpreference.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("MySharedPref", AppCompatActivity.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()
        initListener()
        init()
        return binding.root
    }

    private fun initListener() {
        binding.btnLogout.setOnClickListener {
            sharedPreferencesEditor.clear().apply()
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container_view, MainFragment())
                addToBackStack(null)
            }
        }
    }

    private fun init() {
        val bundle = this.arguments
        if (bundle != null) {
            val email = bundle.getString("email", "-")
            val isLogin = bundle.getBoolean("isLogin", false)
            sharedPreferencesEditor.putBoolean("isLogin", isLogin)
            sharedPreferencesEditor.putString("email", email)
            sharedPreferencesEditor.commit()
        }
        binding.tvNamePlaceholder.text = sharedPreferences.getString("email", "-")
    }
}