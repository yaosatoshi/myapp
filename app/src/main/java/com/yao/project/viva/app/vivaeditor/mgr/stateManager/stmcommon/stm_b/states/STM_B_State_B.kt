package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.states

import android.util.Log

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.StateCommonBase
import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon.stm_b.STM_B

class STM_B_State_B(stm: STM_B) : StateCommonBase(stm) {
    private val TAG = javaClass.simpleName

    override fun name(): String {
        return "STM_B_State_B"
    }

    override fun prologue() {
        Log.d(TAG, "prologue()")
        super.prologue()
    }

    override fun epilogue() {
        Log.d(TAG, "epilogue()")
        super.epilogue()
    }

    override fun destroy() {
        Log.d(TAG, "destroy()")
        super.destroy()
    }
}
