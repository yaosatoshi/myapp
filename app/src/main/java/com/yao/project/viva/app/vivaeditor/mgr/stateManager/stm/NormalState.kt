package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stm

abstract class NormalState(var trans: StateMachineNormal?) {

    abstract fun name(): String

    abstract fun prologue()

    abstract fun epilogue()

    open fun destroy() {
        this.trans = null
    }

    fun restart() {
        trans!!.restart()
    }
}
