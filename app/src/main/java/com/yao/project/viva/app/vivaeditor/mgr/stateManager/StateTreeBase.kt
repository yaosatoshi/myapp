package com.yao.project.viva.app.vivaeditor.mgr.stateManager

import kotlin.reflect.KClass

abstract class StateTreeBase {

    abstract fun getRootStateMachineIndex(): Int
    abstract fun generateStateMachineInstance(idx: Int): StateMachineBase
    abstract fun getSizeOfStateMachines(): Int
    abstract fun getParentStateMachineIndex(idx: Int): Int
    abstract fun getChildStateMachines(idx: Int): Map<KClass<out StateBase>, Int>

    private inner class Node(
        var sm: StateMachineBase,
        var childlist: MutableList<Node>? = null
    ) {
        fun findNode(type: Int): Node? {
            return findNodePrivate(type, this)
        }

        private fun findNodePrivate(type: Int, node: Node?): Node? {
            return node?.let {
                if (it.sm.own == type) {
                    it
                } else {
                    var found: Node? = null
                    it.childlist?.forEach {
                        found = findNodePrivate(type, it)
                        if (found != null) return@forEach
                    }
                    found
                }
            }
        }

        fun callbackCurrentFromBottomNode(cb: (StateMachineBase) -> Unit) {
            callbackCurrentFromBottomNodePrivate(this, cb)
        }

        private fun callbackCurrentFromBottomNodePrivate(node: Node, cb: (StateMachineBase) -> Unit) {
            if ( node.sm.currentState >= 0 ) {
                getChildStateMachines(node.sm.own)[node.sm.getStateClass(node.sm.currentState)::class]?.let {
                    callbackCurrentFromBottomNodePrivate(node.findNode(it)!!, cb)
                }
                cb.invoke(node.sm)
            }
        }
    }

    private lateinit var rootNode: Node

    // TODO 次ここ。StateMachineTypesを基底クラス化し、STMを沢山作れるようにする。
    fun init() {
        rootNode = run {
            val root = Node(generateStateMachineInstance(getRootStateMachineIndex()), null)

            val pendingList = mutableListOf<Int>().apply {
                for (i in 0 until getSizeOfStateMachines()) {
                    if (getRootStateMachineIndex() != i) {
                        add(i)
                    }
                }
            }

            do {
                val deleteList = mutableListOf<Int>()
                pendingList.forEach { addIndex ->
                    root.findNode(addIndex) ?: run {
                        val value = generateStateMachineInstance(addIndex)
                        root.findNode(getParentStateMachineIndex(value.own))?.let {
                            val newnode = Node(value, null)
                            it.childlist?.add(newnode) ?: run {
                                it.childlist = mutableListOf(newnode)
                            }
                            deleteList.add(addIndex)
                        }
                    }
                }
                deleteList.forEach {
                    pendingList.remove(it)
                }
            } while (pendingList.size > 0)

            root
        }
    }

    fun getCurrentTopStateMachine() : StateMachineBase {
        TODO("最下層のカレントSTMを返す。なぜならonTouchのようなイベントを送りたい場合は中間STMのSTATEには発行できないため")
    }
    fun getCurrentTopState() : StateBase {
        TODO("最下層のカレント状態を返す。なぜならonTouchのようなイベントを送りたい場合は中間STMのSTATEには発行できないため")
    }

    fun setState(type: Int, state: KClass<out StateBase>) {
        rootNode.findNode(type)?.let {
            it.sm.apply {
                if (currentState >= 0) {
                    getChildStateMachines(it.sm.own)[it.sm.getStateClass(it.sm.currentState)::class]?.let {
                        rootNode.findNode(it)!!.callbackCurrentFromBottomNode {
                            it.stopState()
                        }
                    }
                }
                setState(state)
                getChildStateMachines(type)[state]?.let {
                    // 子供のStateMachineがある場合
                    rootNode.findNode(it)?.apply {
                        sm.startState()
                        var sm2 = sm
                        var node = this
                        var childstate = getChildStateMachines(sm2.own)[sm2.getStateClass(sm2.currentState)::class]
                        while(childstate != null) {
                                node = node.findNode(childstate)!!
                                node.sm.startState()
                                sm2 = node.sm
                                childstate = getChildStateMachines(sm2.own)[sm2.getStateClass(sm2.currentState)::class]
                        }
                    }
                }
            }
        }
    }


    fun destroy() {
        destroyPrivate(rootNode)
    }

    private fun destroyPrivate(node: Node?) {
        node?.let { n ->
            n.sm.destroy()
            n.childlist?.let {
                it.forEach {
                    destroyPrivate(it)
                }
                it.clear()
                n.childlist = null
            }
        }
    }
}
