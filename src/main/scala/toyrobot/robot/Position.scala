package toyrobot

case class Position(x:Int, y:Int){
    def unapply = (this.x, this.y)
}