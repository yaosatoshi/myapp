package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBaseCanUndo
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.SingleOperationManagerPrivate
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.reverse.OperationItem00SkeletonCanUndoReverse

class OperationItem00SkeletonCanUndo : OperationItemDataBaseCanUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()
    override fun reverse(): OperationItemDataBase {
        return OperationItem00SkeletonCanUndoReverse()
    }

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit) {
            instance.doOperation(OperationItem01_01SkeletonCanUndo(), {
                instance.doOperation(OperationItem01_02SkeletonCanUndo(), {
                    instance.doOperation(OperationItem01_01SkeletonCanUndo(), {
                        Log.i(TAG, "doOperation() finished. ${this@OperationItem00SkeletonCanUndo.javaClass.simpleName}")
                        completion.invoke(this@OperationItem00SkeletonCanUndo)
                    })
                })
            })
        }
    }
}
