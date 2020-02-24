import org.specs2.mutable.Specification
import toyrobot.ToyRobot._
import toyrobot.ToyRobot
class MainTest extends Specification {
    "ToyRobot Main" should {

        "returnString should return type String" in {
            val result = ToyRobot.returnString()
            result must haveClass[String]
        }
    }
}


