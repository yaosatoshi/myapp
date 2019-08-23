package com.yao.project.viva

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateMachineCommonTypes
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateManagerCommon
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_a.states.*
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_A
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_B
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states.STM_B_State_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_c.states.STM_C_State_C
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d.states.STM_D_State_A

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StateManagerCommon.init()

//        StateManagerCommon.setState(StateMachineCommonTypes.ROOT_IDX, STM_A_State_A::class)
        StateManagerCommon.setState(StateMachineCommonTypes.ROOT_IDX, STM_A_State_A::class)
        StateManagerCommon.setState(StateMachineCommonTypes.STM_B_IDX, STM_B_State_B::class)
        StateManagerCommon.setState(StateMachineCommonTypes.STM_C_IDX, STM_C_State_C::class)
        StateManagerCommon.setState(StateMachineCommonTypes.ROOT_IDX, STM_A_State_D::class)
//        StateManagerCommon.setState(StateMachineCommonTypes.STM_B_IDX, STM_B_State_A::class)
//        StateManagerCommon.setState(StateMachineCommonTypes.STM_B_IDX, STM_B_State_B::class)
//        StateManagerCommon.setState(StateMachineCommonTypes.STM_B_IDX, STM_B_State_C::class)
//        StateManagerCommon.setState(StateMachineCommonTypes.ROOT_IDX, STM_A_State_D::class)
//        StateManagerCommon.setState(StateMachineCommonTypes.ROOT_IDX, STM_A_State_C::class)
//        StateManagerCommon.setState(StateMachineCommonTypes.STM_C_IDX, STM_C_State_C::class)
//
//        StateManagerCommon.setState(StateMachineCommonTypes.STM_D_IDX, STM_D_State_A::class)
//
//        StateManagerCommon.setState(StateMachineCommonTypes.ROOT_IDX, STM_A_State_None::class)
    }

    override fun onDestroy() {
        super.onDestroy()
        StateManagerCommon.destroy()
    }
}
