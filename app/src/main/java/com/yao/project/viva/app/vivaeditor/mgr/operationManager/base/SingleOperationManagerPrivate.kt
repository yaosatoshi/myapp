package com.yao.project.viva.app.vivaeditor.mgr.operationManager.base

import android.support.annotation.UiThread
import android.util.Log

// Operationにする単位は undo / redo 単位と等価。
// ではなく、積まないタイプも存在するように修正

// TODO: ダメ。各OperationItem内で別のOperationをコール可能になっていないと煩雑さは消えず。結局Mgrを作る意味がない。

@UiThread
class SingleOperationManagerPrivate {

    private val TAG = javaClass.simpleName

    private val UNDOBUFFER_MAX_SIZE = 200

    private val list = mutableListOf<OperationItemDataBase>()
    private val redolist = mutableListOf<OperationItemDataBase>()
    private var isProcessing = false

    fun doOperation(item: OperationItemDataBase, completion: (OperationItemDataBase) -> Unit) {
        if (isProcessing) {
            throw IllegalAccessException("$TAG doOperation ignored because now processing.")

        } else {
            isProcessing = true
            if (item.canUndoRedo()) {
                list.add(item)
                if (list.size > UNDOBUFFER_MAX_SIZE) {
                    list.removeAt(0)
                }
            }
            item.generate().doOperation {
                isProcessing = false
                completion.invoke(it)
            }
        }
    }

    fun canUndo() = list.size > 0
    fun undo(completion: (OperationItemDataBase) -> Unit) {
        if (isProcessing || list.size <= 0) {
            throw IllegalAccessException("$TAG undo ignored /isProcessing:$isProcessing /list.size:${list.size}")

        } else {
            isProcessing = true
            list.removeAt(list.size - 1).let {
                redolist.add(it)
                it.reverse()!!.generate().doOperation {
                    isProcessing = false
                    completion.invoke(it)
                }
            }
        }
    }

    fun canRedo() = redolist.size > 0
    fun redo(completion: (OperationItemDataBase) -> Unit) {
        if (isProcessing || redolist.size <= 0) {
            throw IllegalAccessException("$TAG redo ignored /isProcessing:$isProcessing /redolist.size:${redolist.size}")

        } else {
            isProcessing = true
            redolist.removeAt(redolist.size - 1).also {
                doOperation(it, {
                    isProcessing = false
                    completion.invoke(it)
                })
            }
        }
    }

    fun destroy() {
        clearAllBuffers()
    }

    private fun clearAllBuffers() {
        isProcessing = false
        list.clear()
        redolist.clear()
    }
}
