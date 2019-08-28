package com.yao.project.viva.app.vivaeditor.mgr.operationManager.base

abstract class OperationItemDataBaseCantUndo() : OperationItemDataBase(){
    private val TAG = javaClass.simpleName

    override fun canUndoRedo() = false
    override fun reverse() = throw IllegalAccessException("$TAG cannot reverse() because it cant undo/redo")
}