package toyrobot



sealed trait Direction
case object North extends Direction
case object East extends Direction
case object South extends Direction
case object West extends Direction

sealed trait Command
case class Place(position:Array[Int], direction:Direction) extends Command
case object Move extends Command
case object Left extends Command
case object Right extends Command
case object Report extends Command

class ToyRobot (commandsList:List[String]) {
    private var commandIndex: Int = 0
    
    def getCommands():List[String] = commandsList
    def getFirstCommand():String = this.commandsList.head
    def increaseIndex = { this.commandIndex = this.commandIndex + 1}



    def getInitialDirection(): Direction = {
        val directionAsString = this.getFirstCommand.replace("PLACE ", "").split(",").last
        this.getDirection(directionAsString)
    }

    def getDirection(str:String):Direction = {
        str match {
            case "NORTH" => North
            case "EAST" => East
            case "SOUTH" => South
            case "WEST" => West
        }
    }

    def getNextCommand():String = commandsList(commandIndex)

    def executeCommand(command: String) ={
        println(command)
    }

    def getInitialPosition(): Array[Int] = {
        val positionAsString = this.getFirstCommand.replace("PLACE ", "").split(",")
        Array(positionAsString(0).toInt, positionAsString(1).toInt)
    }

    def getCommandFromLine(line:String):Command = {
        line.split(" ").head match {
            case x if x == "PLACE" => Place(Array(0,0), North) // TODO: call a function
            case "MOVE" => Move
            case "LEFT" => Left
            case "RIGHT" => Right
            case "REPORT" => Report
        }
    }

    def executeCommand(command:Command)= {
        command match{
            case Place(position, direction) => println(s"Placing robot in position ${position}, and direction ${direction}") 
            case Move => println("moving")
            case Left => println("left")
            case Right => println("right")
            case Report => println("report")
        }
    }

    def execute():String ={
        
        val commands = for {line <-commandsList
            command = this.getCommandFromLine(line)
        } yield command


        commands.map(executeCommand) 

               "done"
    }


}