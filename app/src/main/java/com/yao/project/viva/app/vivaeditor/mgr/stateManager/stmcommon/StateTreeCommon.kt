package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.StateBase
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.StateTreeBase
import kotlin.reflect.KClass

class StateTreeCommon : StateTreeBase() {
    private val allTypes = StateMachineCommonTypes.values()

    private var rootIndex: Int? = null

    private val childStates = mutableListOf<Map<KClass<out StateBase>, Int>>().apply {
        allTypes.forEach {
            it.getChildStates().let {
                add(mutableMapOf<KClass<out StateBase>, Int>().apply {
                    it.forEach {
                        put(it.key, it.value.ordinal)
                    }
                })
            }
            if (it.getParent() == null) {
                rootIndex = it.ordinal
            }
        }
    }

    override fun getRootStateMachineIndex() = rootIndex!!
    override fun generateStateMachineInstance(idx: Int) = allTypes[idx].generateInstance()
    override fun getSizeOfStateMachines() = allTypes.size
    override fun getParentStateMachineIndex(idx: Int) = allTypes[idx].getParent()!!.ordinal

    fun setState(type: StateMachineCommonTypes, state: KClass<out StateCommonBase>) {
        super.setState(type.ordinal, state)
    }

    override fun getChildStateMachines(idx: Int) = childStates[idx]
}