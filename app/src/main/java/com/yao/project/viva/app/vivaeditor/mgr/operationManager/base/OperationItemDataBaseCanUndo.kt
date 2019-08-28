package com.yao.project.viva.app.vivaeditor.mgr.operationManager.base

abstract class OperationItemDataBaseCanUndo() : OperationItemDataBase(){
    private val TAG = javaClass.simpleName

    override fun canUndoRedo() = true
}