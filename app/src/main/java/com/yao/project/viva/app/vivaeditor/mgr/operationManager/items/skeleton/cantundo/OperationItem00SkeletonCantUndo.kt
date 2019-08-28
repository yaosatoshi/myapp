package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.cantundo

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBaseCantUndo
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.SingleOperationManagerPrivate

class OperationItem00SkeletonCantUndo : OperationItemDataBaseCantUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit) {
            instance.doOperation(OperationItem01_01SkeletonCantUndo(), {
                instance.doOperation(OperationItem01_02SkeletonCantUndo(), {
                    instance.doOperation(OperationItem01_01SkeletonCantUndo(), {
                        Log.i(TAG, "doOperation() finished. ${this@OperationItem00SkeletonCantUndo.javaClass.simpleName}")
                        completion.invoke(this@OperationItem00SkeletonCantUndo)
                    })
                })
            })
        }
    }
}
