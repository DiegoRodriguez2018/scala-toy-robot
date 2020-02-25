import org.specs2.mutable.Specification
import toyrobot._

class ToyRobotSpec extends Specification {

    "ToyRobot" should {
        "return 0,1,NORTH when processing input_1.txt" in  {
            val commandsPath = "src/test/scala/resources/input_1.txt"
            val commands = CommandReader.getCommands(commandsPath)
            val robot = new ToyRobot(commands)
            val result = robot.execute.last
            result must beEqualTo("0,1,NORTH")
        }


        "return 0,0,WEST when processing input_2.txt" in  {
            val commandsPath = "src/test/scala/resources/input_2.txt"
            val commands = CommandReader.getCommands(commandsPath)
            val robot = new ToyRobot(commands)
            val result = robot.execute.last
            result must beEqualTo("0,0,WEST")
        }

        "return 3,3,NORTH when processing input_3.txt" in  {
            val commandsPath = "src/test/scala/resources/input_3.txt"
            val commands = CommandReader.getCommands(commandsPath)
            val robot = new ToyRobot(commands)
            val result = robot.execute.last
            result must beEqualTo("3,3,NORTH")
        }
    }
}


    
