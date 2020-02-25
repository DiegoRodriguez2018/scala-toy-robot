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
    private var position: Array[Int] =  Array(0,0) 
    private var direction: Direction =  North 
    private val tableSize = 5;

    def stringToDirection(str:String):Direction = {
        str match {
            case "NORTH" => North
            case "EAST" => East
            case "SOUTH" => South
            case "WEST" => West
        }
    }

    def directionToString(direction:Direction)={
        direction match {
            case  North =>  "NORTH"
            case  East => "EAST"
            case  South => "SOUTH"
            case  West => "WEST"
        }
    }

    def getCommandFromLine(line:String):Command = {
        line.split(" ").head match {
            case x if x == "PLACE" =>{
                val Array(x, y, directionAsString) = line.replace("PLACE ", "").split(",")
                val direction = this.stringToDirection(directionAsString)
                val position = Array(x.toInt, y.toInt)
                Place(position, direction)
            } 
            case "MOVE" => Move
            case "LEFT" => Left
            case "RIGHT" => Right
            case "REPORT" => Report
        }
    }

    def isValidPosition(position:Array[Int]) = {
        val Array(x,y) = position
        x >= 0 & x <= tableSize & y >= 0 & y <= tableSize
    }

    def executeCommand(command:Command): String= {
        command match{
            case Place(position, direction) => {
                // Checking if position is valid, otherwise ignoring command.
                if (this.isValidPosition(position)){
                    this.position = position;
                    this.direction = direction;
                    s"Placing robot in position ${x},${y} and direction ${this.direction}" 
                } else {
                    s"Invalid Input. Can not PLACE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${this.position(0)},${this.position(1)}"
                }
            } 
            case Move => {
                // TODO: add validation
                val Array(x, y) = position;

                val newPosition = this.direction match {
                    case North => Array(x, y + 1)
                    case East => Array(x + 1, y)
                    case South => Array(x, y -1)
                    case West => Array(x -1 , y)
                }

                if (this.isValidPosition(newPosition)) {
                    this.position = newPosition
                    "Moving"
                } else {
                    s"Invalid Input. Can not MOVE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${this.position(0)},${this.position(1)}"
                }
            }
            case Left =>  {
                this.direction = this.direction match {
                    case North => West
                    case West => South
                    case South => East
                    case East => North
                }
                "Rotating Left"
            }
            case Right =>  {
                this.direction = this.direction match {
                    case  North =>  East
                    case  East => South
                    case  South => West
                    case  West => North
                }
                "Rotating Right"
            }
            case Report => s"${this.position(0)},${this.position(1)},${directionToString(this.direction)}"
        }
    }

    def execute():List[String] ={
        val commands = for {
            line <-commandsList
            command = this.getCommandFromLine(line)
        } yield command

        val results = commands.map(executeCommand)
        results
    }
}