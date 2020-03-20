package toyrobot
import toyrobot.{Position, Direction}

case class Robot(position: Position, direction: Direction, log: String){
    def unapply = (this.position, this.direction)
}
