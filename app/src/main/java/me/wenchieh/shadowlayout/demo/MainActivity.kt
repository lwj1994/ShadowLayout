package me.wenchieh.shadowlayout.demo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import me.wenchieh.shadowlayout.ShadowLayout

class MainActivity : AppCompatActivity() {

  private lateinit var seekBarLeft: SeekBar
  private lateinit var seekBarRight: SeekBar
  private lateinit var seekBarTop: SeekBar
  private lateinit var seekBarBottom: SeekBar
  private lateinit var seekBarBlur: SeekBar
  private lateinit var seekBarRadius: SeekBar

  private lateinit var textViewLeft: TextView
  private lateinit var textViewRight: TextView
  private lateinit var textViewTop: TextView
  private lateinit var textViewBottom: TextView
  private lateinit var textViewRadius: TextView
  private lateinit var textViewBlur: TextView
  private lateinit var shadowLayout: ShadowLayout

  private var mLeft = 0
  private var mRight = 0
  private var mTop = 0
  private var mBottom = 0
  private var mBlur = 0
  private var mRadius = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    seekBarLeft = findViewById(R.id.leftSeekBar)
    seekBarRight = findViewById(R.id.rightSeekBar)
    seekBarTop = findViewById(R.id.topSeekBar)
    seekBarBottom = findViewById(R.id.bottomSeekBar)
    seekBarBlur = findViewById(R.id.blurSeekBar)
    seekBarRadius = findViewById(R.id.radiusSeekBar)

    textViewLeft = findViewById(R.id.leftTextView)
    textViewRight = findViewById(R.id.rightTextView)
    textViewTop = findViewById(R.id.topTextView)
    textViewBottom = findViewById(R.id.bottomTextView)
    textViewRadius = findViewById(R.id.radiusTextView)
    textViewBlur = findViewById(R.id.blurTextView)
    shadowLayout = findViewById(R.id.shadowLayout)


    seekBarLeft.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
      @SuppressLint("SetTextI18n")
      override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        mLeft = p1
        textViewLeft.text = "left: $mLeft"
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
        requestLayout()

      }
    })
    seekBarRight.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
      @SuppressLint("SetTextI18n")
      override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        mRight = p1
        textViewRight.text = "right: $mRight"
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
        requestLayout()

      }
    })
    seekBarTop.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
      @SuppressLint("SetTextI18n")
      override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        mTop = p1
        textViewTop.text = "top: $mTop"
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
        requestLayout()

      }
    })

    seekBarBottom.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
      @SuppressLint("SetTextI18n")
      override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        mBottom = p1
        textViewBottom.text = "bottom: $mBottom"
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
        requestLayout()
      }
    })


    seekBarBlur.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
      @SuppressLint("SetTextI18n")
      override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        mBlur = p1
        textViewBlur.text = "blur: $mBlur"
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
        requestLayout()
      }
    })


    seekBarRadius.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
      @SuppressLint("SetTextI18n")
      override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        mRadius = p1
        textViewRadius.text = "Radius: $mRadius"
      }

      override fun onStartTrackingTouch(p0: SeekBar?) {
      }

      override fun onStopTrackingTouch(p0: SeekBar?) {
        requestLayout()
      }
    })
  }

  private fun requestLayout() {
    shadowLayout.apply {
      shadowLeft = mLeft.toFloat()
      shadowRight = mRight.toFloat()
      shadowTop = mTop.toFloat()
      shadowBottom = mBottom.toFloat()
      blur = mBlur.toFloat()
      radius = mRadius.toFloat()
      color = Color.YELLOW
    }
    shadowLayout.requestLayout()
    shadowLayout.invalidate()
  }
}
