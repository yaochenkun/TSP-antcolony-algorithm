# TSP-AntColonyAlgorithm
An implementation of TSP(Traveling Salesman Problem) by Ant Colony Algorithm in Java.
## Details
* Solve TSP in Java.
* Adopt Object-Oriented.
* Divide into 5 Classes: __AntAlgorithm__, __Ant__, __City__, __Road__ and __Constant__.
* Verify respectively in 3 different size of cities: 10 cities, 20 cities and 31 cities.

## Classes
* __AntAlgorithm__: controls the whole algorithm's life cycle including initing all the ants, choosing the next city and simulating the round trip for each ant, updating pheromone for each road.
* __Ant__: portrays the serveral actions of ant such as initing the birthplace, choosing the next city, arriving the next city.
* __City__: contains the id of city, the sum of visibility and pheromone with weight, and the possibility to be choosed by one ant.
* __Road__: contains the distance and the pheromone of each road.
* __Constant__：contains the data of map, the initial value of pheromone(C), the max number of round trips, the number of ants, the flow-rate of pheromone and so on.

## For More
If you want to learn more information about this implementation, please visit the following three sites(but a pity only in Chinese):
* [use Ant Colony Algorithm to solve TSP(1)-Summary](http://yaochenkun.site/index.php/2016/12/11/ant_article/)
* [use Ant Colony Algorithm to solve TSP(2)-Core Code](http://yaochenkun.site/index.php/2016/12/12/ant2_article/)
* [use Ant Colony Algorithm to solve TSP(3)-Verification](http://yaochenkun.site/index.php/2016/12/12/ant3_article/)
