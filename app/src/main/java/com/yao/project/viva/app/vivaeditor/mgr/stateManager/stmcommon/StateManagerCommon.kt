package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stmcommon

import kotlin.reflect.KClass

/**
 * stateの一元管理をここで行う。全ての階層のStateを管理する
 */

object StateManagerCommon {

    private lateinit var stateTree : StateTreeCommon

    fun init() {
        stateTree = StateTreeCommon()
        stateTree.init()
    }

    fun setState(type: StateMachineCommonTypes, state: KClass<out StateCommonBase>) {
        TODO()
    }

    // Singletonなのでタスクキルした時にdestroyを実行する必要がある。
    fun destroy() {
        stateTree.destroy()
    }
}
