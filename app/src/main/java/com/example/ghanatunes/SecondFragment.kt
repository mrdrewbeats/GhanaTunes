package com.example.ghanatunes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.media_player_bottom_sheet, container, false)
    }

    companion object{
        const val TAG = "MEDACONTROLSTAG"
    }

}