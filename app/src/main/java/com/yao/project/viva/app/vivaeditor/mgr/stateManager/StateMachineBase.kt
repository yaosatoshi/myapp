package com.yao.project.viva.app.vivaeditor.mgr.stateManager

import com.yao.project.viva.app.vivaeditor.mgr.stateManager.stm.StateMachineNormal
import kotlin.reflect.KClass

abstract class StateMachineBase(var own: Int) : StateMachineNormal() {

    private var stateMap: MutableMap<KClass<out StateBase>, Int>? = null

    abstract fun isRootStateMachine(): Boolean

    fun set(l: List<StateBase>) {
        super._set(l, isRootStateMachine())
        stateMap = mutableMapOf<KClass<out StateBase>, Int>().apply {
            var i = 0
            l.forEach {
                put(it::class, i++)
            }
        }
    }

    fun getStateIndex(state: KClass<out StateBase>): Int {
        return stateMap!![state]!!
    }

    fun setState(state: KClass<out StateBase>) {
        super._setState(stateMap!![state]!!)
    }

    override fun destroy() {
        super.destroy()
        stateMap?.clear()
        stateMap = null
    }
}
