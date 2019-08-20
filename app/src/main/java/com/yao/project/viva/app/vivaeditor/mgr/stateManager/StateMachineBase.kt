package com.yao.project.viva.app.vivaeditor.mgr.stateManager

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stm.StateMachineNormal

abstract class StateMachineBase(var own: Int) : StateMachineNormal() {

//    fun parentType() = own.getParent()

    override fun destroy() {
        super.destroy()
    }
}
