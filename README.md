# PageRank-using-scala

Goal

Code that calculates PageRank(PR) for a set of pages from the Apache
Foundation that have been crawled by a search engine. 

Schema of Dataset:

First Field: URL
Second Field: Starting PageRank (1.0 for every page)
Third Field: List of links found at that URL


Requirements:

Data read through RDD run the PageRank
algorithm to compute the rank of every page.

Assumptions:

1. The PR only up to 3 decimal points.
2.The terminating condition of the algorithm will be either if the ranks converge or
until a maximum of 100 iterations. That is, you can set a maximum number of
iterations to be 100.
3. You can use any numerical processing library, but you can't use any library that
computes PageRank.
