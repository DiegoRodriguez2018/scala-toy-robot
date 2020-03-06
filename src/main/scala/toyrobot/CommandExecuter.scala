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
    private var position: Position = Position(0,0)
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
                val position = Position(x.toInt, y.toInt)
                Place(position, direction)
            } 
            case "MOVE" => Move
            case "LEFT" => Left
            case "RIGHT" => Right
            case "REPORT" => Report
        }
    }

    def isValidPosition(position:Position) = {
        val (x, y) = (position.x, position.y)
        x >= 0 & x <= tableSize & y >= 0 & y <= tableSize
    }

    def executeCommand(command:Command): String= {
        command match{
            case Place(position: Position, direction: Direction) => {
                // Checking if position is valid, otherwise ignoring command.
                if (this.isValidPosition(position)){
                    this.position = position;
                    this.direction = direction;
                    s"Placing robot in position ${position.x},${position.y} and direction ${this.direction}" 
                } else {
                    s"Invalid Input. Can not PLACE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${this.position.x},${this.position.y}"
                }
            } 
            case Move => {
                val (x, y) = (position.x, position.y)

                val newPosition = this.direction match {
                    case North => Position(x, y + 1)
                    case East => Position(x + 1, y)
                    case South => Position(x, y -1)
                    case West => Position(x -1 , y)
                }

                if (this.isValidPosition(newPosition)) {
                    this.position = newPosition
                    "Moving"
                } else {
                    s"Invalid Input. Can not MOVE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${this.position.x},${this.position.y}"
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
            case Report => s"${this.position.x},${this.position.y},${directionToString(this.direction)}"
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