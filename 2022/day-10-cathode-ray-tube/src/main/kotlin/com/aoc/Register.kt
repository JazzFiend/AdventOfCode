package com.aoc

import java.lang.RuntimeException

class Register {
  var value = 1
    private set
  private var addend: Int? = null
  var inProgress = false
    private set

  fun startAdd(addend: Int) {
    if (this.addend != null) {
      throw RuntimeException("Add started without completing previous")
    }

    inProgress = true
    this.addend = addend
  }

  fun completeAdd() {
    value += addend!!
    addend = null
    inProgress = false
  }
}
