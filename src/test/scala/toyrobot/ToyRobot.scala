import org.specs2.mutable.Specification
import toyrobot._

class ToyRobotSpec extends Specification {
    val path = "src/test/scala/resources/input.txt"
    val commands = CommandReader.getCommands(path)
    val robot = new ToyRobot(commands)

    "ToyRobot" should {
        // "return commands as type List[String]" in {
        //     val result = robot.getCommands()
        //     result must haveClass[List[String]]
        // }
        "return position as Array[Int]" in  {
            val result = robot.getInitialPosition()
            result must haveClass[Array[Int]]
        }
        "return position 0,0 with sample input" in  {
            val result = robot.getInitialPosition()
            result must beEqualTo(Array(0,0))
        }
    }
}


