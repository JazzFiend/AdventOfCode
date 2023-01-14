package com.aoc.commands

class CommandDecoder {
  companion object {
    fun createCommand(commandList: List<String>, index: Int): FileTreeCommand {
      return if(isListInstruction(commandList[index])) {
        val i = findEndOfLsCommand(index + 1, commandList)
        ListCommand(commandList.subList(index + 1, i))
      } else if(commandList[index] == "$ cd ..") {
        ChangeDirectoryParentCommand()
      } else if(commandList[index] == "$ cd /") {
        ChangeDirectoryRootCommand()
      } else {
        val tokenizedCommand = commandList[index].split(" ")
        ChangeDirectoryByNameCommand(tokenizedCommand[2])
      }
    }

    fun calculateIndexOffset(commandList: List<String>, index: Int): Int {
      return findEndOfLsCommand(index + 1, commandList)
    }

    private fun findEndOfLsCommand(i: Int, commandList: List<String>): Int {
      var index = i
      while (index < commandList.size) {
        if (isCommand(commandList, index)) {
          break
        }
        index++
      }
      return index
    }

    private fun isListInstruction(command: String) =
      command == "$ ls"

    private fun isCommand(commands: List<String>, i: Int) =
      commands[i][0] == '$'
  }
}
