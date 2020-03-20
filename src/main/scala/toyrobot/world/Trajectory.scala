package toyrobot
import toyrobot.{ Table, Robot, Direction }

object Trajectory {
    def build(commands: List[Command]): List[Robot]= {
        val initialValue = new Robot(Position(0,0), North, "Starting robot")
        commands.scanLeft(initialValue)((currentRobot,command) => track(currentRobot,command))
    } 

    def track(currentRobot: Robot, command:Command): Robot = {
        val (currentPosition, currentDirection)= currentRobot.unapply
        command match {
            case Place(position: Position, direction: Direction) => 
                this.place(position, direction, currentRobot)
            case Move => 
                this.move(currentPosition,currentDirection, currentRobot)
            case Left => 
                new Robot(currentPosition, getLeftDirection(currentDirection), "Rotating Left")
            case Right => 
                new Robot(currentPosition, getRightDirection(currentDirection), "Rotating Right")
            case Report => 
                this.report(currentPosition,currentDirection, currentRobot)
        }
    }

    def place(position: Position, direction: Direction, currentRobot:Robot):Robot = {
        // Checking if position is valid, otherwise ignoring command.

        if (Table.isValidPosition(position)){
            val message = s"Placing robot in position ${position.x},${position.y} and direction ${direction}"
            new Robot(position, direction, message)
        } else {
            val tableSize = Table.tableSize
            val message = s"Invalid Input. Can not PLACE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${currentRobot.position.x},${currentRobot.position.y}"
            currentRobot.copy(log = message)
        }
    } 

    def move (currentPosition:Position, currentDirection:Direction, currentRobot: Robot):Robot = {
        val (x, y) = currentPosition.unapply;

        val newPosition = currentDirection match {
            case North => Position(x, y + 1)
            case East => Position(x + 1, y)
            case South => Position(x, y -1)
            case West => Position(x -1 , y)
        }

        if (Table.isValidPosition(newPosition)) {
            new Robot(newPosition, currentDirection, "Moving")
        } else {
            val tableSize = Table.tableSize
            val message = s"Invalid Input. Can not MOVE Robot outside the ${tableSize}x${tableSize} table. Position remains at ${currentPosition.x},${currentPosition.y}"
            val newRobot = currentRobot.copy(log = message)
            newRobot
        }
    }

    def report(currentPosition:Position, currentDirection:Direction, currentRobot: Robot) = {
        val direction = Direction.toString(currentDirection)
        val message = s"\n\nRobot's final position is ${currentPosition.x},${currentPosition.y},${direction}\n\n"
        currentRobot.copy(log = message)
    }

    def getLeftDirection (currentDirection: Direction):Direction = currentDirection match {
        case North => West
        case West => South
        case South => East
        case East => North
    }

    def getRightDirection (currentDirection: Direction):Direction = currentDirection match {
        case  North =>  East
        case  East => South
        case  South => West
        case  West => North
    }
}
