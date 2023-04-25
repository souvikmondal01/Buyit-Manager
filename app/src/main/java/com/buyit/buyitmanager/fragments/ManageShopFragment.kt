package com.buyit.buyitmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.buyit.buyitmanager.R
import com.buyit.buyitmanager.adapters.ShopAdapter
import com.buyit.buyitmanager.databinding.FragmentManageShopBinding
import com.buyit.buyitmanager.models.ShopModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageShopFragment : Fragment() {

    private lateinit var adapter: ShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentManageShopBinding.inflate(inflater, container, false)
        binding.cvBackArrow.setOnClickListener {
//            activity?.onBackPressed()
            it.findNavController().navigate(R.id.action_manageShopFragment_to_homeFragment)

        }

        val db = Firebase.firestore

        val collection = db.collection("shop")
        val query = collection
        val recyclerOptions =
            FirestoreRecyclerOptions.Builder<ShopModel>().setQuery(query, ShopModel::class.java)
                .build()

        adapter = ShopAdapter(recyclerOptions)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.startListening()

        return binding.root
    }


}