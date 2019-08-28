package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.reverse

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBaseCanUndo
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.SingleOperationManagerPrivate
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.OperationItem00SkeletonCanUndo

class OperationItem00SkeletonCanUndoReverse : OperationItemDataBaseCanUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()
    override fun reverse(): OperationItemDataBase {
        return OperationItem00SkeletonCanUndo()
    }

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit) {
            instance.doOperation(OperationItem01_01SkeletonCanUndoReverse(), {
                instance.doOperation(OperationItem01_02SkeletonCanUndoReverse(), {
                    instance.doOperation(OperationItem01_01SkeletonCanUndoReverse(), {
                        Log.i(TAG, "doOperation() finished. ${this@OperationItem00SkeletonCanUndoReverse.javaClass.simpleName}")
                        completion.invoke(this@OperationItem00SkeletonCanUndoReverse)
                    })
                })
            })
        }
    }
}
