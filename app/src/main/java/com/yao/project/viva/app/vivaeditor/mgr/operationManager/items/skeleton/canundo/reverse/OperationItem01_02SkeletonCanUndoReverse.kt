package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.reverse

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.*
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.OperationItem01_02SkeletonCanUndo

class OperationItem01_02SkeletonCanUndoReverse : OperationItemDataBaseCanUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()

    override fun reverse(): OperationItemDataBase {
        return OperationItem01_02SkeletonCanUndo()
    }

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit) {
            instance.doOperation(OperationItem02SkeletonCanUndoReverse(), {
                Log.i(TAG, "doOperation() finished. ${this@OperationItem01_02SkeletonCanUndoReverse.javaClass.simpleName}")
                completion.invoke(this@OperationItem01_02SkeletonCanUndoReverse)
            })
        }
    }
}
