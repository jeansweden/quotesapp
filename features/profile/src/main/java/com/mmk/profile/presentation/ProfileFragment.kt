package com.mmk.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.mmk.common.ui.fragmentdelegations.viewBinding
import com.mmk.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private val binding by viewBinding(FragmentProfileBinding::inflate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        with(binding.profileComposeView) {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
            }
        }
        return binding.root
    }
}
