package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateMachineCommonBase
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateMachineCommonTypes

class STM_B(var ownState: StateMachineCommonTypes) : StateMachineCommonBase(ownState.ordinal) {
    private val TAG = javaClass.simpleName

    override fun isRootStateMachine() = ownState.getParent() == null
}
