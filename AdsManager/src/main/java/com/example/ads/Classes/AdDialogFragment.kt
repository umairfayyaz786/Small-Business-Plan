package com.example.ads.Classes

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.ads.R

private const val AD_COUNTER_TIME = 5L

private const val AD_DIALOG_TAG = "AdDialogFragment"
class AdDialogFragment : DialogFragment(){
    private var listener: AdDialogInteractionListener? = null

    private var countDownTimer: CountDownTimer? = null
    private var timeRemaining: Long = 0
    fun setAdDialogInteractionListener(listener: AdDialogInteractionListener) {
        this.listener = listener
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = requireActivity().layoutInflater.inflate(R.layout.fragment_ad_dialog, null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.requireActivity())
        builder.setView(view)

        val args = arguments
        var rewardAmount = -1
        var rewardType: String? = null
        if (args != null) {
            rewardAmount = args.getInt(REWARD_AMOUNT)
            rewardType = args.getString(REWARD_TYPE)
        }
        if (rewardAmount > 0 && rewardType != null) {
            builder.setTitle(getString(R.string.more_coins_text, rewardAmount, rewardType))
        }

        builder.setNegativeButton(
            getString(R.string.negative_button_text)
        ) { _, _ -> dialog?.cancel() }
        val dialog: Dialog = builder.create()
        createTimer(AD_COUNTER_TIME, view)
        return dialog
    }
    private fun createTimer(time: Long, dialogView: View) {
        val textView: TextView = dialogView.findViewById(R.id.timer)
        countDownTimer = object : CountDownTimer(time * 1000, 50) {
            override fun onTick(millisUnitFinished: Long) {
                timeRemaining = millisUnitFinished / 1000 + 1
                textView.text =
                    String.format(getString(R.string.video_starting_in_text), timeRemaining)
            }

            override fun onFinish() {
                dialog?.dismiss()

                if (listener != null) {
                    Log.d(AD_DIALOG_TAG, "onFinish: Calling onShowAd().")
                    listener!!.onShowAd()
                }
            }
        }
        countDownTimer?.start()
    }
    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

        if (listener != null) {
            Log.d(AD_DIALOG_TAG, "onCancel: Calling onCancelAd().")
            listener!!.onCancelAd()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(AD_DIALOG_TAG, "onDestroy: Cancelling the timer.")
        countDownTimer?.cancel()
        countDownTimer = null
    }
    interface AdDialogInteractionListener {
        /** Called when the timer finishes without user's cancellation. */
        fun onShowAd()

        /** Called when the user clicks the "No, thanks" button or press the back button. */
        fun onCancelAd()
    }

    companion object {
        private const val REWARD_AMOUNT = "rewardAmount"
        private const val REWARD_TYPE = "rewardType"
        fun newInstance(rewardAmount: Int, rewardType: String): AdDialogFragment {
            val args = Bundle()
            args.putInt(REWARD_AMOUNT, rewardAmount)
            args.putString(REWARD_TYPE, rewardType)
            val fragment = AdDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}