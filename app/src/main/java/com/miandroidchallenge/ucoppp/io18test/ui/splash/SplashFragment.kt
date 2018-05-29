package com.miandroidchallenge.ucoppp.io18test.ui.splash

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miandroidchallenge.ucoppp.io18test.R
import com.miandroidchallenge.ucoppp.io18test.databinding.SplashFragmentBinding
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : Fragment() {

    companion object {
        fun newInstance(): SplashFragment = SplashFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
    }

    lateinit var binding: SplashFragmentBinding

    private val factory by lazy {
        SplashFactoryViewModel(
                application = activity!!.application
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener({ view: View ->
            viewModel.checkUser(view)
        })
    }
}
