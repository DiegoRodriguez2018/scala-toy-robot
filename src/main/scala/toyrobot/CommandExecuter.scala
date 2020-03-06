package toyrobot

sealed trait Direction
case object North extends Direction
case object East extends Direction
case object South extends Direction
case object West extends Direction

sealed trait Command
case class Place(position:Position, direction:Direction) extends Command
case object Move extends Command
case object Left extends Command
case object Right extends Command
case object Report extends Command

case class Position(x:Int, y:Int)

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


case class Robot(position: Position, direction: Direction){

    def directionToString(direction:Direction)={
        direction match {
            case  North =>  "NORTH"
            case  East => "EAST"
            case  South => "SOUTH"
            case  West => "WEST"
        }
    }

    def report() = {
         s"${this.position.x},${this.position.y},${directionToString(this.direction)}"
    }
}


class ToyRobot(commands: List[Command]){
    private val tableSize = 5;
    
    def execute(): List[Robot]= {
        val initialValue = new Robot(Position(0,0), North)
        val robots = commands.scanLeft(initialValue)((previousRobot,command)=> executeCommand(previousRobot,command))
        robots
    } 

    def getNextPosition (currentRobot: Robot, command:Command): Robot = {
        currentRobot
    }

    def isValidPosition(position:Position) = {
        val (x, y) = (position.x, position.y)
        x >= 0 & x <= tableSize & y >= 0 & y <= tableSize
    }

    def executeCommand(previousRobot: Robot, command:Command): Robot = {
        command match{
            case Place(position: Position, direction: Direction) => {
                new Robot(position, direction)
            } 
            case Report => previousRobot
        }
    }
}