package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_a.STM_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_a.states.*
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.STM_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_None
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_c.STM_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_c.states.STM_C_State_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_c.states.STM_C_State_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_c.states.STM_C_State_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_c.states.STM_C_State_None
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d.STM_D
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d.states.STM_D_State_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d.states.STM_D_State_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d.states.STM_D_State_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d.states.STM_D_State_None
import kotlin.reflect.KClass

enum class StateMachineCommonTypes {
    // このデータ構造をTreeで表現するのは非常に辛いので横並びで定義する。こうしておくとStateMachineの追加と紐付けが非常に楽になるので。
    ROOT_IDX {
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

        override fun getChildStates(): Map<KClass<out StateCommonBase>, StateMachineCommonTypes> = mapOf(
            STM_A_State_C::class to STM_B_IDX
        )
    },
    STM_B_IDX {
        override fun getParent() = ROOT_IDX
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

        override fun getChildStates(): Map<KClass<out StateCommonBase>, StateMachineCommonTypes> = mapOf(
            STM_B_State_None::class to STM_C_IDX
        )
    },
    STM_C_IDX {
        override fun getParent() = STM_B_IDX
        override fun generateInstance() = STM_C(this).apply {
            set(
                listOf(
                    STM_C_State_None(this),
                    STM_C_State_A(this),
                    STM_C_State_B(this),
                    STM_C_State_C(this)
                )
            )
        }

        override fun getChildStates(): Map<KClass<out StateCommonBase>, StateMachineCommonTypes> = mapOf(
            STM_C_State_None::class to STM_D_IDX
        )
    },
    STM_D_IDX {
        override fun getParent() = STM_C_IDX
        override fun generateInstance() = STM_D(this).apply {
            set(
                listOf(
                    STM_D_State_None(this),
                    STM_D_State_A(this),
                    STM_D_State_B(this),
                    STM_D_State_C(this)
                )
            )
        }

        override fun getChildStates(): Map<KClass<out StateCommonBase>, StateMachineCommonTypes> = mapOf()
    }
    ;

    abstract fun generateInstance(): StateMachineCommonBase
    abstract fun getParent(): StateMachineCommonTypes?
    abstract fun getChildStates(): Map<KClass<out StateCommonBase>, StateMachineCommonTypes>
}
