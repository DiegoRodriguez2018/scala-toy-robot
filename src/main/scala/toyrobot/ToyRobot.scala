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


// TODO: 
// Limit space to 5x5
// Rotation

class ToyRobot (commandsList:List[String]) {
    private var position: Array[Int] =  Array(0,0) 
    private var direction: Direction =  North 

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

    def executeCommand(command:Command): String= {
        command match{
            case Place(position, direction) => {
                this.position = position;
                this.direction = direction;
                s"Placing robot in position ${this.position(0)},${this.position(1)} and direction ${this.direction}" 
            } 
            case Move => {
                this.direction match {
                    case North => this.position(1) +=1
                    case East => this.position(0) +=1
                    case South => this.position(1) -=1
                    case West => this.position(0) -=1
                }
                "moving"
            }
            case Left =>  "Rotating Left"
            case Right => "Rotating Right"
            case Report => s"${this.position(0)},${this.position(1)},${directionToString(this.direction)}"
        }
    }

    def execute():String ={
        val commands = for {
            line <-commandsList
            command = this.getCommandFromLine(line)
        } yield command

        val results = commands.map(executeCommand)

        results.foreach(println)
        results.last

    }
}