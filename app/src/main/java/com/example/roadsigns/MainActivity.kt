package com.example.roadsigns
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.roadsigns.adapters.SignsAdapter
import com.example.roadsigns.models.SignModel




class MainActivity : AppCompatActivity() {
    lateinit var vpSigns: ViewPager2
    lateinit var btnMix: Button
    lateinit var switchShow: Switch
    lateinit var tvCounter: TextView
    private lateinit var signsAdapter: SignsAdapter
    private var isDescriptionVisible = false
    lateinit var signs : ArrayList<SignModel>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vpSigns = findViewById(R.id.vpSigns)
        btnMix = findViewById(R.id.btnMix)
        switchShow = findViewById(R.id.swShow)
        tvCounter = findViewById(R.id.tvCounter)

        signs = LoadSigns().loadSigns()

        btnMix.setOnClickListener {
            signs.shuffle()
            signsAdapter = SignsAdapter(this, signs, isDescriptionVisible)
            vpSigns.adapter = signsAdapter
        }

        switchShow.setOnCheckedChangeListener { buttonView, isChecked ->
            isDescriptionVisible = isChecked
            signsAdapter.changeVisibleStatus(isDescriptionVisible)
            signsAdapter.notifyDataSetChanged()
        }


        vpSigns.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tvCounter.text = "${(position + 1)} / ${signsAdapter.itemCount}"
            }

        })
    }
}