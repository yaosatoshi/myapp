package com.yao.project.viva.app.vivaeditor.mgr.operationManager.base

abstract class OperationItemDataBase() {
    // 逆の処理を行う OperationItemDataBase を生成する。undoで使われる
    // Undoできない処理はnullを返す
    abstract fun reverse(): OperationItemDataBase
    abstract fun canUndoRedo() : Boolean
    abstract fun generate() : OperationItemBase
}
