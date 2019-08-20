package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_a.STM_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_a.states.*
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.STM_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_None
import kotlin.reflect.KClass

enum class StateMachineCommonTypes {
    // このデータ構造をTreeで表現するのは非常に辛いので横並びで定義する。こうしておくとStateMachineの追加と紐付けが非常に楽になるので。
    ROOT {
        override fun getParent() = null     // null is only StateMachineTypes.ROOT
        override fun generateInstance() = STM_A(this).apply {
            set(
                listOf(
                    STM_A_State_None(this),
                    STM_A_State_A(this),
                    STM_A_State_B(this),
                    STM_A_State_C(this),
                    STM_A_State_D(this)
                )
            )
        }

        override fun getChildStates(): Map<StateMachineCommonTypes, KClass<out StateCommonBase>> = mapOf(
            SELECT_CONTENTS to STM_A_State_C::class
        )
    },
    SELECT_CONTENTS {
        override fun getParent() = ROOT
        override fun generateInstance() = STM_B(this).apply {
            set(
                listOf(
                    STM_B_State_None(this),
                    STM_B_State_A(this),
                    STM_B_State_B(this),
                    STM_B_State_C(this)
                )
            )
        }

        override fun getChildStates(): Map<StateMachineCommonTypes, KClass<out StateCommonBase>> = mapOf()
    },
    ;

    abstract fun generateInstance(): StateMachineCommonBase
    abstract fun getParent(): StateMachineCommonTypes?
    abstract fun getChildStates(): Map<StateMachineCommonTypes, KClass<out StateCommonBase>>
}
