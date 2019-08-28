package com.yao.project.viva

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.OperationManager
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.cantundo.OperationItem00SkeletonCantUndo
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.OperationItem00SkeletonCanUndo
import java.util.concurrent.CountDownLatch


class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OperationManager.init()

        Log.i(TAG, "----- doOperation() start -------")

        var cdl = CountDownLatch(1)
        OperationManager.doOperation(OperationItem00SkeletonCanUndo(), {
            Log.i(TAG, "doOperation() finished. ${it.javaClass.simpleName}")
            cdl.countDown()
        })
        cdl.await()

        Log.i(TAG, "----- undo() start -------")
        cdl = CountDownLatch(1)
        OperationManager.undo {
            Log.i(TAG, "undo() finished. ${it.javaClass.simpleName}")
            cdl.countDown()
        }
        cdl.await()

        Log.i(TAG, "----- redo() start -------")
        cdl = CountDownLatch(1)
        OperationManager.redo {
            Log.i(TAG, "redo() finished. ${it.javaClass.simpleName}")
            cdl.countDown()
        }
        cdl.await()
    }

    override fun onDestroy() {
        super.onDestroy()
        OperationManager.destroy()
    }
}
