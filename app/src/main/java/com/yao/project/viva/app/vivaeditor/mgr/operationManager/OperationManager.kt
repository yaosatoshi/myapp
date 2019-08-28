package com.yao.project.viva.app.vivaeditor.mgr.operationManager

import android.support.annotation.UiThread
import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.OperationItemDataBase
import com.yao.project.viva.app.vivaeditor.mgr.operationManager.base.SingleOperationManagerPrivate
import java.lang.IllegalArgumentException

@UiThread
object OperationManager {
    private val TAG = javaClass.simpleName

    private val UNDOLIST_MAX_SIZE = 200

    val undoList: MutableList<OperationItemDataBase> = mutableListOf()
    val redoList: MutableList<OperationItemDataBase> = mutableListOf()

    private var isProcessing = false
    private var isProcessingChanged: ((Boolean) -> Unit) = {
        Log.i(TAG, "isProcessingChanged $it")
    }

    private var isCanUndoChanged: ((Boolean) -> Unit) = {
        Log.i(TAG, "isCanUndoChanged $it")
    }

    private var isCanRedoChanged: ((Boolean) -> Unit) = {
        Log.i(TAG, "isCanRedoChanged $it")
    }


    fun init() {
    }

    fun doOperation(item: OperationItemDataBase, completion: (OperationItemDataBase) -> Unit) {
        if (isProcessing) {
            throw IllegalAccessException("$TAG doOperation() failed.")

        } else {
            if (undoList.size == 0) {
                isCanUndoChanged.invoke(true)
            }
            undoList.add(item)
            if (undoList.size > UNDOLIST_MAX_SIZE) {
                undoList.removeAt(0)
            }
            isProcessingChanged.invoke(true)
            SingleOperationManagerPrivate().also {
                it.doOperation(item, {
                    isProcessing = false
                    completion.invoke(it)
                    isProcessingChanged.invoke(false)
                })
            }
        }
    }

    fun canUndo() = undoList.size > 0
    fun undo(completion: (OperationItemDataBase) -> Unit) {
        if (isProcessing || undoList.size == 0) {
            throw IllegalAccessException("$TAG undo() failed. /size:${undoList.size} /isProcessing:$isProcessing")

        } else {
            isProcessing = true
            isProcessingChanged.invoke(true)

            undoList.removeAt(undoList.size - 1).let { item ->
                if (redoList.size == 0) {
                    isCanRedoChanged.invoke(true)
                }
                redoList.add(item)
                SingleOperationManagerPrivate().also {
                    val reversedItem = item.reverse()
                    if (reversedItem.canUndoRedo() != item.canUndoRedo()) {
                        throw IllegalArgumentException("$TAG reversed item must be undo operation. /item:${item.javaClass.simpleName} /reversedItem:${reversedItem.javaClass.simpleName}")
                    }
                    it.doOperation(reversedItem, {
                        isProcessing = false
                        completion.invoke(it)
                        isProcessingChanged.invoke(false)
                    })
                }
            }
            if (undoList.size == 0) {
                isCanUndoChanged.invoke(false)
            }
        }
    }

    fun canRedo() = redoList.size > 0
    fun redo(completion: (OperationItemDataBase) -> Unit) {
        if (isProcessing || redoList.size == 0) {
            throw IllegalAccessException("$TAG redo() failed. /size:${redoList.size} /isProcessing:$isProcessing")

        } else {
            redoList.removeAt(redoList.size - 1).let {
                doOperation(it, completion)
            }
            if (redoList.size == 0) {
                isCanRedoChanged.invoke(false)
            }
        }
    }

    fun destroy() {
        undoList.clear()
        redoList.clear()
    }
}
