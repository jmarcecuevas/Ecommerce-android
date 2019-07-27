package com.marcecuevas.easybuy.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcecuevas.hotelsapp.data.model.Error
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import kotlin.coroutines.CoroutineContext

abstract class GenericFragment: Fragment(), CoroutineScope, KodeinAware {

    private lateinit var job: Job
    override val kodein by closestKodein()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(layout(),container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    abstract fun layout(): Int

    abstract fun init()

    fun showError(error: Error){
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(error.title)
                .setMessage(error.message)
                .setCancelable(false)
                .setPositiveButton("OK"){dialog,which ->
                    dialog.dismiss()
                }
                .create().show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun showProgress(){
        activity?.let {
            ProgressDialogFragment.showProgressBar(it)
        }
    }

    fun hideProgress(){
        activity?.let {
            ProgressDialogFragment.hideProgressBar(it)
        }
    }
}