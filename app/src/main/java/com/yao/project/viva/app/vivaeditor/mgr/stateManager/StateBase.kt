package com.yao.project.viva.app.vivaeditor.mgr.stateManager

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stm.NormalState

abstract class StateBase(sm: StateMachineBase) : NormalState(sm) {
    protected var isLock = false

    fun setLock() {
        isLock = true
    }

    override fun prologue() {
        isLock = false
    }

    override fun epilogue() {
    }

    override fun destroy() {
        super.destroy()
    }
}
