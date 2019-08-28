package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.reverse

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.*
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.OperationItem01_01SkeletonCanUndo

class OperationItem01_01SkeletonCanUndoReverse : OperationItemDataBaseCanUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()

    override fun reverse(): OperationItemDataBase {
        return OperationItem01_01SkeletonCanUndo()
    }

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit) {
            Log.i(TAG, "doOperation() finished. ${this@OperationItem01_01SkeletonCanUndoReverse.javaClass.simpleName}")
            completion.invoke(this@OperationItem01_01SkeletonCanUndoReverse)
        }
    }
}
