package com.yao.project.viva.app.vivaeditor.mgr.stateManager.stm

import android.util.Log

abstract class StateMachineNormal {

    private var mTrans: StateTransition = StateTransition()

    abstract fun onStartStateMachine()
    abstract fun onStopStateMachine()

    val currentStateMachine: NormalState
        @Synchronized get() = getStateClass(currentState)

    val currentState: Int
        @Synchronized get() = mTrans.currentState

    var isStart = false

    open fun _set(list: List<NormalState>, isStartState: Boolean) {
        mTrans.startState(list, isStartState)
    }

    open fun destroy() {
        mTrans.destroy()
    }

    @Synchronized
    fun restart() {
        mTrans.restart()
    }

    @Synchronized
    open fun _setState(state: Int) {
        mTrans.setState(state)
    }

    @Synchronized
    open fun startState() {
        onStartStateMachine()
        mTrans.setState(0)
        isStart = true
    }

    @Synchronized
    open fun stopState() {
        mTrans.setState(-1)
        isStart = false
        onStopStateMachine()
    }

    @Synchronized
    fun getStateClass(state: Int): NormalState {
        return mTrans.getStateMachine(state)
    }

    @Synchronized
    fun getStateList() = mTrans.getStateList()

    private inner class StateTransition {

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

        fun startState(list: List<NormalState>?, isStartState: Boolean) {
            if (list != null && list.size > 0) {
                this.list = list
                if (isStartState) {
                    onStartStateMachine()
                    setState(0)
                    isStart = true
                }
            }
        }

        fun setState(new_state: Int) {
            if (new_state < 0 && !is_state_changing) {
                // stop state
                is_state_changing = true
                if (currentState >= 0) {
                    list!![currentState].epilogue()
                    currentState = -1
                }
                is_state_changing = false

            } else if (new_state != currentState && !is_state_changing) {
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

        fun getStateList(): List<NormalState>? = list
    }
}
