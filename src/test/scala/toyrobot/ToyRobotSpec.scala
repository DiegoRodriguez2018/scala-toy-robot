import org.specs2.mutable.Specification
import toyrobot._

class ToyRobotSpec extends Specification {

    "ToyRobot" should {
        "return 0,1,NORTH when processing input_1.txt" in  {
            val commandsPath = "src/test/scala/resources/input_1.txt"
            val commands = CommandReader.getCommands(commandsPath)
            
            val robot = new ToyRobot(commands)
            val result = robot.execute

            result must beEqualTo("0,1,NORTH")
        }

    }
}


