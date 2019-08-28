package com.yao.project.viva.app.vivaeditor.mgr.operationManager.items.menus

import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBaseCantUndo

class OperationItemNewProject : OperationItemDataBaseCantUndo() {
    private val TAG = javaClass.simpleName

    override fun generate() = Instance()

    inner class Instance : OperationItemBase() {
        private val TAG = javaClass.simpleName

        override fun doOperation(completion: (OperationItemDataBase) -> Unit) {
            completion.invoke(this@OperationItemNewProject)
        }
    }
}
