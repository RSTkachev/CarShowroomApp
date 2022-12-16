package com.example.carshowroom.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carshowroom.CarData
import com.example.carshowroom.databinding.FragmentListCarBinding
import com.google.firebase.database.*

class ListCarFragment : Fragment() {

    private var _binding: FragmentListCarBinding? = null

    private lateinit var dbRef : DatabaseReference
    private lateinit var  carRecyclerView: RecyclerView
    private lateinit var carArrayList: ArrayList<CarData>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentListCarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        carRecyclerView = binding.carList
        carRecyclerView.layoutManager = LinearLayoutManager(context)
        carRecyclerView.setHasFixedSize(true)

        carArrayList = arrayListOf<CarData>()
        carRecyclerView.adapter = context?.let { ListCarFragmentAdapter(it, carArrayList) }
        getUserData()



        return root
    }


    private fun getUserData() {
        dbRef = FirebaseDatabase.getInstance().getReference("Cars")

        dbRef.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    carArrayList.clear()
                    for (carSnapshot in snapshot.children) {

                        val car = carSnapshot.getValue(CarData::class.java)
                        carArrayList.add(car!!)

                    }
                    carRecyclerView.adapter = ListCarFragmentAdapter(context!!, carArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}