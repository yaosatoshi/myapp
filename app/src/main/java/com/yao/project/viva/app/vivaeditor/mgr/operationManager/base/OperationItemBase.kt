package com.yao.project.viva.app.vivaeditor.mgr.operationManager.base

abstract class OperationItemBase {
    abstract fun doOperation(completion: (OperationItemDataBase) -> Unit)
}
