package toyrobot

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
        val (previousPosition, previousDirection)= (previousRobot.position, previousRobot.direction)
        command match{
            case Place(position: Position, direction: Direction) => {
                // Checking if position is valid, otherwise ignoring command.
                if (this.isValidPosition(position)){
                    println(s"Placing robot in position ${position.x},${position.y} and direction ${direction}") 
                    new Robot(position, direction)
                } else {
                    println(s"Invalid Input. Can not PLACE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${position.x},${position.y}")
                    previousRobot
                }
            } 
            case Move => {
                val (x, y) = (previousPosition.x, previousPosition.y);

                val newPosition = previousDirection match {
                    case North => Position(x, y + 1)
                    case East => Position(x + 1, y)
                    case South => Position(x, y -1)
                    case West => Position(x -1 , y)
                }

                if (this.isValidPosition(newPosition)) {
                    println("Moving")
                    new Robot(newPosition, previousDirection)
                } else {
                    println(s"Invalid Input. Can not MOVE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${previousPosition.x},${previousPosition.y}")
                    previousRobot
                }
            }
            case Left =>  {
                println("Rotating Left")
                val newDirection = previousDirection match {
                    case North => West
                    case West => South
                    case South => East
                    case East => North
                }
                new Robot(previousPosition, newDirection)
            }
            case Right =>  {
                println("Rotating Right")
                val newDirection = previousDirection match {
                    case  North =>  East
                    case  East => South
                    case  South => West
                    case  West => North
                }
                new Robot(previousPosition, newDirection)
            }
            case Report => {
                previousRobot
            }
        }
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