import org.specs2.mutable.Specification
import toyrobot._


class ToyRobotSpec extends Specification {
    val path = "src/test/scala/resources/input.txt"
    val commands = CommandReader.getCommands(path)
    val robot = new ToyRobot(commands)

    "getCommands" should {
        "return commands as type List[String]" in {
            val result = robot.getCommands()
            result must haveClass[List[String]]
        }
    }
}


