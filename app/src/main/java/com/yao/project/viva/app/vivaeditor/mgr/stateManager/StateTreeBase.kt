package com.yao.project.viva.app.vivaeditor.mgr.stateManager

import kotlin.reflect.KClass

abstract class StateTreeBase {

    private class Node(
        var sm: StateMachineBase,
        var childlist: MutableList<Node>? = null
    ) {
        fun find(type: Int): Node? {
            return findPrivate(type, this)
        }

        private fun findPrivate(type: Int, node: Node?): Node? {
            return node?.let {
                if (it.sm.own == type) {
                    it
                } else {
                    var found: Node? = null
                    it.childlist?.forEach {
                        found = findPrivate(type, it)
                        if (found != null) return@forEach
                    }
                    found
                }
            }
        }
    }

    private lateinit var rootNode: Node

    abstract fun getRootStateMachineIndex(): Int
    abstract fun generateStateMachineInstance(idx: Int): StateMachineBase
    abstract fun getSizeOfStateMachines(): Int
    abstract fun getParentStateMachineIndex(idx: Int): Int
    abstract fun getChildStateMachines(idx: Int): Map<KClass<out StateBase>, Int>

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
                pendingList.forEach { addIndex ->
                    root.find(addIndex) ?: run {
                        val value = generateStateMachineInstance(addIndex)
                        root.find(getParentStateMachineIndex(value.own))?.let {
                            val newnode = Node(value, null)
                            it.childlist?.add(newnode) ?: run {
                                it.childlist = mutableListOf(newnode)
                            }
                            pendingList.remove(addIndex)
                        }
                    }
                }
            } while (pendingList.size > 0)

            root
        }
    }

    fun setState(type: Int, state: KClass<out StateBase>) {
        rootNode.find(type)?.let {
            it.sm.apply {
                setState(state)
                getChildStateMachines(type)[state]?.let {
                    // 子供のStateMachineがある場合
                    rootNode.find(it)?.let {

                        it.sm.startState()
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
