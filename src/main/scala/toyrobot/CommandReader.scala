package toyrobot
import scala.io.Source

object CommandReader {
    def getCommands(path:String): List[String] = Source.fromFile(path).getLines().toList
}

