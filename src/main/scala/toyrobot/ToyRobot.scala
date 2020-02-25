package toyrobot

// Position, Direction


sealed trait Direction
case object North extends Direction
case object East extends Direction
case object South extends Direction
case object West extends Direction

class ToyRobot (commands:List[String]) {

    private var position: Array[Int] = this.getInitialPosition()

    def getCommands():List[String] = commands

    def getInitialPosition(): Array[Int] = {
        val firstCommand = commands.head
        println(firstCommand)
        Array(0,0)
    }

}