package com.marcecuevas.easybuy.view.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.marcecuevas.easybuy.R
import kotlinx.android.synthetic.main.view_overlay_image.view.*

class OverlayImageViewer: FrameLayout {

    var pageIndicatorTV: TextView? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_overlay_image, this)

        pageIndicatorTV = view.findViewById(R.id.pageIndicatorTV)
        closeButton.setOnClickListener(){

        }
    }

    @SuppressLint("SetTextI18n")
    fun setup(selected: Int, total: Int){
        pageIndicatorTV?.text = "${selected}/${total}"
    }

}