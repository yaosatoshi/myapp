package com.yao.project.viva

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.OperationManager


class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OperationManager.init()

    }

    override fun onDestroy() {
        super.onDestroy()
        OperationManager.destroy()
    }
}
