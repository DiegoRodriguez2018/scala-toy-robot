package toyrobot
import toyrobot.{ Robot, Direction }

object Trajectory {
    private val tableSize = 5;

    def build(commands: List[Command]): List[Robot]= {
        val initialValue = new Robot(Position(0,0), North, "Starting robot")
        val robots = commands.scanLeft(initialValue)((previousRobot,command)=> executeCommand(previousRobot,command))
        robots
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
                    val message = s"Placing robot in position ${position.x},${position.y} and direction ${direction}"
                    new Robot(position, direction, message)
                } else {
                    val message = s"Invalid Input. Can not PLACE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${position.x},${position.y}"
                    
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
                    new Robot(newPosition, previousDirection, "Moving")
                } else {
                    val message = s"Invalid Input. Can not MOVE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${previousPosition.x},${previousPosition.y}"
                    val newRobot = previousRobot.copy(log = message)
                    newRobot
                }
            }
            case Left =>  {
                val newDirection = previousDirection match {
                    case North => West
                    case West => South
                    case South => East
                    case East => North
                }
                new Robot(previousPosition, newDirection, "Rotating Left")
            }
            case Right =>  {
                val newDirection = previousDirection match {
                    case  North =>  East
                    case  East => South
                    case  South => West
                    case  West => North
                }
                new Robot(previousPosition, newDirection, "Rotating Right")
            }
            case Report => {
                val msgDirection = Direction.directionToString(previousDirection)
                val message = s"\n\nThe robot final position is ${previousPosition.x},${previousPosition.y},${msgDirection}\n\n"
                previousRobot.copy(log = message)
            }
        }
    }
}
