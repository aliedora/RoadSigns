package com.example.roadsigns
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.roadsigns.adapters.SignsAdapter
import com.example.roadsigns.models.SignModel
import com.google.android.material.internal.ParcelableSparseArray
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var vpSigns: ViewPager2
    lateinit var btnMix: Button
    lateinit var btnShow: Button
    lateinit var switchShow: Switch
    lateinit var tvCounter: TextView
    private var signsAdapter: SignsAdapter? = null
    private var isDescriptionVisible = false
    private var signs: ArrayList<SignModel> = LoadSigns().loadMixedSigns()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vpSigns = findViewById(R.id.vpSigns)
        btnMix = findViewById(R.id.btnMix)
        btnShow = findViewById(R.id.btnShow)
        switchShow = findViewById(R.id.swShow)
        tvCounter = findViewById(R.id.tvCounter)
//        mix()

        if (savedInstanceState != null) {
            val currentPos = savedInstanceState.getInt("position");
            vpSigns.currentItem = currentPos
            signsAdapter = SignsAdapter(this, signs, isDescriptionVisible)
            tvCounter.text = "${(currentPos)} / ${signsAdapter?.itemCount}"
            vpSigns.adapter = signsAdapter
        }


        btnMix.setOnClickListener {
            signs = ArrayList(signs.shuffled())
            signsAdapter = SignsAdapter(this, signs, isDescriptionVisible)
            vpSigns.adapter = signsAdapter
        }

        btnShow.setOnClickListener {
            signs = LoadSigns().loadMixedSigns()
            signsAdapter = SignsAdapter(this, signs, isDescriptionVisible)
            vpSigns.adapter = signsAdapter
        }

        switchShow.setOnCheckedChangeListener { buttonView, isChecked ->
                isDescriptionVisible = isChecked
                signsAdapter?.changeVisibleStatus(isDescriptionVisible)
                signsAdapter?.notifyDataSetChanged()
        }


        vpSigns.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tvCounter.text = "${(position + 1)} / ${signsAdapter?.itemCount}"
            }

        })
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("position", vpSigns.currentItem)
        super.onSaveInstanceState(savedInstanceState)
    }

    private fun mix(){
        val mixedSigns = ArrayList(LoadSigns().loadSigns().shuffled())
        mixedSigns.forEach{
            println(it.toString())
        }
    }
}