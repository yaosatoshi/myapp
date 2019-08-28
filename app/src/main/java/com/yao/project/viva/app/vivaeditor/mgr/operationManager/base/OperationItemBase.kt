package com.yao.project.viva.app.vivaeditor.mgr.operationManager.base

abstract class OperationItemBase {
    abstract fun doOperation(instance: SingleOperationManagerPrivate, completion: (OperationItemDataBase) -> Unit)
}
