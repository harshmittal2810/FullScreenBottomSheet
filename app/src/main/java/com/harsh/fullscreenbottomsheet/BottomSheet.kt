package com.harsh.fullscreenbottomsheet

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.harsh.fullscreenbottomsheet.databinding.LayoutBottomSheetBinding

class BottomSheet : BottomSheetDialogFragment() {

  lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
  internal var bi: LayoutBottomSheetBinding? = null

  private val actionBarSize: Int
    get() {
      val array = context!!.theme
        .obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
      return array.getDimension(0, 0f).toInt()
    }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

    //inflating layout
    val view = View.inflate(context, R.layout.layout_bottom_sheet, null)

    //binding views to data binding.
    bi = DataBindingUtil.bind(view)

    //setting layout with bottom sheet
    bottomSheet.setContentView(view)

    bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)

    //setting Peek at the 16:9 ratio keyline of its parent.
    bottomSheetBehavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO

    //setting max height of bottom sheet
    bi!!.extraSpace.minimumHeight = Resources.getSystem().displayMetrics.heightPixels / 2

    bottomSheetBehavior.bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
      override fun onStateChanged(view: View, i: Int) {
        if (BottomSheetBehavior.STATE_EXPANDED == i) {
          showView(bi!!.appBarLayout, actionBarSize)
          hideAppBar(bi!!.profileLayout)
        }
        if (BottomSheetBehavior.STATE_COLLAPSED == i) {
          hideAppBar(bi!!.appBarLayout)
          showView(bi!!.profileLayout, actionBarSize)
        }

        if (BottomSheetBehavior.STATE_HIDDEN == i) {
          dismiss()
        }
      }

      override fun onSlide(view: View, v: Float) {

      }
    }

    //aap bar cancel button clicked
    bi!!.cancelBtn.setOnClickListener { dismiss() }

    //aap bar edit button clicked
    bi!!.editBtn.setOnClickListener {
      Toast.makeText(
        context,
        "Edit button clicked",
        Toast.LENGTH_SHORT
      ).show()
    }

    //aap bar more button clicked
    bi!!.moreBtn.setOnClickListener {
      Toast.makeText(
        context,
        "More button clicked",
        Toast.LENGTH_SHORT
      ).show()
    }

    //hiding app bar at the start
    hideAppBar(bi!!.appBarLayout)

    return bottomSheet
  }

  override fun onStart() {
    super.onStart()

    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
  }

  private fun hideAppBar(view: View) {
    val params = view.layoutParams
    params.height = 0
    view.layoutParams = params
  }

  private fun showView(view: View, size: Int) {
    val params = view.layoutParams
    params.height = size
    view.layoutParams = params
  }
}