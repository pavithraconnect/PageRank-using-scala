// Databricks notebook source
//import
import sys.process._


// COMMAND ----------

//dataset download
"wget -P /tmp <path>"!



// COMMAND ----------

//RDD and mapping
val prfile=sc.textFile("file:/tmp/webcrawl")
val iter=100
val neigh=prfile.map(x=>((x.split("\t")(0)),(x.split("\t")(2).replace("{","").replace("}","").replace("(","").replace(")","").split(","))))
var ranks=prfile.map(x=>((x.split("\t")(0)),(x.split("\t")(1)).toDouble))



// COMMAND ----------

//Calculating 100 iterations
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

//Result Collection
val result = ranks.collect().sortBy(-_._2)
result.foreach(x => println(x._1 + " rank: " + x._2))


// COMMAND ----------


