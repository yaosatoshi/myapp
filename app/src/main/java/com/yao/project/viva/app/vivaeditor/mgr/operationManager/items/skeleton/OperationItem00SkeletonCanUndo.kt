package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton

import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBaseCanUndo

class OperationItem00SkeletonCanUndo : OperationItemDataBaseCanUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()
    override fun reverse(): OperationItemDataBase? {
        TODO()
        return OperationItem00SkeletonCanUndo()
    }

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(completion: (OperationItemDataBase) -> Unit) {
            completion.invoke(this@OperationItem00SkeletonCanUndo)
        }
    }
}
