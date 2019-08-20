package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stm

import android.util.Log

abstract class StateMachineNormal {

    private var mTrans: StateTransition? = null

    val currentStateMachine: NormalState
        @Synchronized get() = getStateMachine(currentState)

    val currentState: Int
        @Synchronized get() = mTrans!!.currentState

    fun set(list: List<NormalState>) {
        mTrans = StateTransition()
        mTrans!!.startState(list)
    }

    open fun destroy() {
        mTrans!!.destroy()
        mTrans = null
    }

    @Synchronized
    fun restart() {
        mTrans!!.restart()
    }

    @Synchronized
    fun setState(state: Int) {
        mTrans!!.setState(state)
    }

    @Synchronized
    fun getStateMachine(state: Int): NormalState {
        return mTrans!!.getStateMachine(state)
    }

    private class StateTransition {

        var currentState = -1
        private var list: List<NormalState>? = null

        private var is_state_changing = false

        fun destroy() {
            for (nsm in list!!) {
                nsm.destroy()
            }
//            list!!.clear()
            list = null
        }

        fun startState(list: List<NormalState>?) {
            if (list != null && list.size > 0) {
                this.list = list
                setState(0)
            }
        }

        fun setState(new_state: Int) {
            if (new_state != currentState && !is_state_changing) {
                is_state_changing = true
                if (currentState >= 0) {
                    list!![currentState].epilogue()
                }
                currentState = new_state
                list!![currentState].prologue()
                is_state_changing = false
            } else {
                Log.d("StateMachineNormal", "ignore setState().")
            }
        }

        fun restart() {
            if (!is_state_changing) {
                is_state_changing = true
                if (currentState >= 0) {
                    val nsm = list!![currentState]
                    nsm.epilogue()
                    nsm.prologue()
                }
                is_state_changing = false
            } else {
                Log.d("StateMachineNormal", "ignore restart().")
            }
        }

        fun getStateMachine(state: Int): NormalState {
            return list!![state]
        }
    }
}
