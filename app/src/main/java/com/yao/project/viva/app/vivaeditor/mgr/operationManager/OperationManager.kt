package com.yao.project.viva.app.vivaeditor.mgr.operationManager

import android.support.annotation.UiThread
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.SingleOperationManagerPrivate

@UiThread
object OperationManager {

    private val TAG = javaClass.simpleName

    private var isProcessing = false

    private var isProcessingChanged: ((Boolean) -> Unit) = {
    }

    fun init() {
        SingleOperationManagerPrivate.init()
    }

    fun doOperation(item: OperationItemDataBase, completion: (OperationItemDataBase) -> Unit) {
        SingleOperationManagerPrivate.doOperation(item, {
            completion.invoke(it)
        })
    }

    fun canUndo() = SingleOperationManagerPrivate.canRedo()
    fun undo(completion: (OperationItemDataBase) -> Unit) {
        SingleOperationManagerPrivate.undo({
            completion.invoke(it)
        })
    }

    fun canRedo() = SingleOperationManagerPrivate.canRedo()
    fun redo(completion: (OperationItemDataBase) -> Unit) {
        SingleOperationManagerPrivate.redo({
            completion.invoke(it)
        })
    }

    fun destroy() {
        SingleOperationManagerPrivate.destroy()
    }
}
