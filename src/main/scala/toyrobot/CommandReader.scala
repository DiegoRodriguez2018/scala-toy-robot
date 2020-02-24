package toyrobot
import scala.io.Source

object CommandReader {
    
    def main(args: Array[String]): Unit = {   
        println(getCommands("src/main/scala/resources/input.txt"))
    }

    def getCommands(path:String): List[String] = {
        val lines = Source.fromFile(path).getLines().toList
        lines
        
    }
}

