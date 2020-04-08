import org.specs2.mutable.Specification
import toyrobot._

class ToyRobotSpec extends Specification {
    "ToyRobot" should {
        "return 0,1,NORTH when processing input_1.txt" in  {
            val commandsPath = "src/test/scala/resources/input_1.txt"
            val commands = CommandReader.getCommands(commandsPath)
            val executer = CommandExecuter.execute(commands)
            val result = executer.last.log
            result must beEqualTo("\n\nRobot's final position is 0,1,NORTH\n\n")
        }

        "return 0,0,WEST when processing input_2.txt" in  {
            val commandsPath = "src/test/scala/resources/input_2.txt"
            val commands = CommandReader.getCommands(commandsPath)
            val executer = CommandExecuter.execute(commands)
            val result = executer.last.log
            result must beEqualTo("\n\nRobot's final position is 0,0,WEST\n\n")
        }

        "return 3,3,NORTH when processing input_3.txt" in  {
            val commandsPath = "src/test/scala/resources/input_3.txt"
            val commands = CommandReader.getCommands(commandsPath)
            val executer = CommandExecuter.execute(commands)
            val result = executer.last.log
            result must beEqualTo("\n\nRobot's final position is 3,3,NORTH\n\n")
        }
    }
}


    
