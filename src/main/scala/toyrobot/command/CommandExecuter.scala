package toyrobot
import toyrobot.{Trajectory, Direction, Position, Command}

object CommandExecuter {
    def execute(commandsList:List[String]):List[Robot] ={
        val commands = for {
            line <-commandsList
            command = this.getCommandFromLine(line)
        } yield command
        Trajectory.build(commands)
    }

    def getCommandFromLine(line:String):Command = {
        line.split(" ").head match {
            case l if l == "PLACE" =>{
                val Array(x, y, directionAsString) = line.replace("PLACE ", "").split(",")
                val direction = Direction.fromString(directionAsString)
                val position = Position(x.toInt, y.toInt)
                Place(position, direction)
            } 
            case "MOVE" => Move
            case "LEFT" => Left
            case "RIGHT" => Right
            case "REPORT" => Report
        }
    }


}