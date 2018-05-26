package com.miandroidchallenge.ucoppp.io18test.ui.signinform

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.miandroidchallenge.ucoppp.io18test.R

class SignInFormFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFormFragment()
    }

    private lateinit var viewModel: SignInFormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sign_in_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignInFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
