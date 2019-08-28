package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.*
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton.canundo.reverse.OperationItem00SkeletonCanUndoReverse

class OperationItem01_02SkeletonCanUndo : OperationItemDataBaseCanUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()

    override fun reverse(): OperationItemDataBase {
        return OperationItem00SkeletonCanUndoReverse()
    }

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit) {
            instance.doOperation(OperationItem02SkeletonCanUndo(), {
                Log.i(TAG, "doOperation() finished. ${this@OperationItem01_02SkeletonCanUndo.javaClass.simpleName}")
                completion.invoke(this@OperationItem01_02SkeletonCanUndo)
            })
        }
    }
}
