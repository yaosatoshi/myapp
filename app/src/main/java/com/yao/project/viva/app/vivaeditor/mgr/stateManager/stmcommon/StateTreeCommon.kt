package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.StateTreeBase

class StateTreeCommon : StateTreeBase() {
    private val allTypes = StateMachineCommonTypes.values()

    override fun getRootStateMachineIndex() = StateMachineCommonTypes.ROOT.ordinal
    override fun generateStateMachineInstance(idx: Int) = allTypes[idx].generateInstance()
    override fun getSizeOfStateMachines() = allTypes.size
    override fun getParentStateMachineIndex(idx:Int) = allTypes[idx].getParent()!!.ordinal
}