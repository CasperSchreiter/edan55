import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.io.Source

object TreeWidth {

  def graphParse(filename: String): Map[Int, Node] = {

    val graph: Map[Int, Node] = scala.collection.mutable.Map[Int, Node]()

    for (line <- Source.fromFile(filename).getLines()) {
      if (!line.startsWith("c") && !line.startsWith("p")) {
        var ints = line.split(" ")
        var from = ints(0).toInt
        var fromNode: Option[Node] = graph.get(from)
        
        if (fromNode == None) {
          graph.+=(from -> new Node(from, new ArrayBuffer[Int])) 
        }
        
        for (s <- 1 until ints.length) {
          var to = ints(s).toInt
          
          if (graph.keySet.contains(to)) {
            
          } else {
            val list = new ArrayBuffer[Int]
            list += from
            val n =  new Node(to, list)
            
            
            
          }
          
        }
        
      }
      println(line)
    }

    graph
  }

  class Node(val id: Int, val children: ArrayBuffer[Int]) {
    

  }

}
