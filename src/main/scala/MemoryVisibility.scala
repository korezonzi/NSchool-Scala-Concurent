object MemoryVisibility extends App {
//  @volatile var number = 0
//  @volatile var ready  = false
  var number = 0
  var ready  = false
  private [this] def getNumber: Int = synchronized{number}
  private [this] def getReady:  Boolean = synchronized{ready}

  //別スレッド起動させる
  new Thread(() => {
    while (!getReady) {
      Thread.`yield`()
    }
    println(getNumber)
  }).start()

  number  =2525
  ready  = true
}
