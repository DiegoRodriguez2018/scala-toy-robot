package toyrobot
import toyrobot.{ToyRobot, Direction, Position, Command}

class CommandExecuter (commandsList:List[String]) {
    def stringToDirection(str:String):Direction = {
        str match {
            case "NORTH" => North
            case "EAST" => East
            case "SOUTH" => South
            case "WEST" => West
        }
    }

    def getCommandFromLine(line:String):Command = {
        line.split(" ").head match {
            case x if x == "PLACE" =>{
                val Array(x, y, directionAsString) = line.replace("PLACE ", "").split(",")
                val direction = this.stringToDirection(directionAsString)
                val position = Position(x.toInt, y.toInt)
                Place(position, direction)
            } 
            case "MOVE" => Move
            case "LEFT" => Left
            case "RIGHT" => Right
            case "REPORT" => Report
        }
    }

    def execute():List[Robot] ={
        val commands = for {
            line <-commandsList
            command = this.getCommandFromLine(line)
        } yield command

        val robots = new ToyRobot(commands)
        robots.execute
    }
}