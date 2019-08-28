package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.skeleton

import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBaseCantUndo

class OperationItem00SkeletonCantUndo : OperationItemDataBaseCantUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(completion: (OperationItemDataBase) -> Unit) {
            completion.invoke(this@OperationItem00SkeletonCantUndo)
        }
    }
}
