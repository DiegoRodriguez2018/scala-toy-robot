import org.specs2.mutable.Specification
import toyrobot._

class ToyRobotSpec extends Specification {
    val path = "src/test/scala/resources/input.txt"
    val commands = CommandReader.getCommands(path)

    "ToyRobot" should {
        // "return commands as type List[String]" in {
        //     val result = robot.getCommands()
        //     result must haveClass[List[String]]
        // }
        "return position as Array[Int]" in  {
            val robot = new ToyRobot(commands)
            val result = robot.getInitialPosition()
            result must haveClass[Array[Int]]
        }
        "return position 0,0 with sample input" in  {
            val robot = new ToyRobot(commands)
            val result = robot.getInitialPosition()
            result must beEqualTo(Array(0,0))
        }
        "return 0,1,NORTH with sample input" in  {
            val robot = new ToyRobot(commands)
            val result = robot.execute
            result must beEqualTo("0,1,NORTH")
        }
    }
}


