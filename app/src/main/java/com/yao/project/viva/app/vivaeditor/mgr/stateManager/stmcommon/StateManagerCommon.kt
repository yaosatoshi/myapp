package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon

import android.util.Log
import kotlin.reflect.KClass

/**
 * stateの一元管理をここで行う。全ての階層のStateを管理する
 */

object StateManagerCommon {
    private val TAG = javaClass.simpleName

    private lateinit var stateTree: StateTreeCommon

    fun init() {
        stateTree = StateTreeCommon()
        stateTree.init()
    }

    fun setState(type: StateMachineCommonTypes, state: KClass<out StateCommonBase>) {
        Log.i(TAG, "STM setState start /type:${type.name} /state:${state.java.simpleName}")
        stateTree.setState(type, state)
        Log.i(TAG, "--- STM setState end /current:${stateTree.getCurrentTopStateMachine().javaClass.simpleName} / ${stateTree.getCurrentTopState().javaClass.simpleName}")
    }

    // Singletonなのでタスクキルした時にdestroyを実行する必要がある。
    fun destroy() {
        stateTree.destroy()
    }
}
