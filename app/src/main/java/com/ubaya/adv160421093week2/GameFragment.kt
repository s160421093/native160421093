package com.ubaya.adv160421093week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ubaya.adv160421093week2.databinding.FragmentGameBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    private lateinit var binding:FragmentGameBinding
    private var num1: Int = 0
    private var num2: Int = 0
    private var answer: Int = 0
    private var scores: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$playerName's Turn"
        }
        binding.btnBack.setOnClickListener {
            val action = GameFragmentDirections.actionMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.btnSubmit.setOnClickListener {
            val playerAnswer = binding.txtAnswer.text.toString()
            if (playerAnswer.isNotEmpty()) {
                if (playerAnswer.toInt() == answer) {
                    scores++
                    generateQuestion()
                } else {
                    val action = GameFragmentDirections.actionGameFragmentToGameOverFragment(scores)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
        generateQuestion()
    }

    private fun generateQuestion() {
        num1 = Random.nextInt(100)
        num2 = Random.nextInt(100)
        answer = num1 + num2
        binding.txtQuestion.text = "$num1 + $num2 = ?"
    }
}