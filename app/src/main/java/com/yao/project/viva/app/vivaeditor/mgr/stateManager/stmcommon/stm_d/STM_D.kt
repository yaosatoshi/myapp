package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_d

import android.util.Log
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateMachineCommonBase
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateMachineCommonTypes

class STM_D(var ownState: StateMachineCommonTypes) : StateMachineCommonBase(ownState.ordinal) {
    private val TAG = javaClass.simpleName

    override fun onStartStateMachine() {
        Log.i(TAG, "onStartStateMachine()")
    }

    override fun onStopStateMachine() {
        Log.i(TAG, "onStopStateMachine()")
    }

}
