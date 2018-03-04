// Databricks notebook source
import sys.process._


// COMMAND ----------

"wget -P /tmp http://www.utdallas.edu/~axn112530/cs6350/data/PRData/webcrawl"!



// COMMAND ----------

val prfile=sc.textFile("file:/tmp/webcrawl")
val iter=100
val neigh=prfile.map(x=>((x.split("\t")(0)),(x.split("\t")(2).replace("{","").replace("}","").replace("(","").replace(")","").split(","))))
var ranks=prfile.map(x=>((x.split("\t")(0)),(x.split("\t")(1)).toDouble))



// COMMAND ----------


for(i <- 1 to iter)
{
  val pr=neigh.join(ranks)
             .values
             .flatMap{ case (urls,rankval) =>
                      var size=urls.size
                      urls.map(url => (url,rankval/size))}
     ranks=pr.reduceByKey((x,y) => x+y)
}



// COMMAND ----------

val result = ranks.collect().sortBy(-_._2)
result.foreach(x => println(x._1 + " rank: " + x._2))


// COMMAND ----------


