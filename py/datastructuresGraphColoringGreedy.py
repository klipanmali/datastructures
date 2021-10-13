# Python3 program to implement greedy
# algorithm for graph coloring
# in undirected graph
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0
        self.colorArr = [-1 for _ in range(self.num_of_v)]

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)

    # Assigns colors (starting from 0) to all
    # vertices and prints the assignment of colors
    def greedy_coloring(self):

        result = [-1] * self.num_of_v

        # Assign the first color to first vertex
        result[0] = 0;

        # A temporary array to store the available colors.
        # True value of available[cr] would mean that the
        # color cr is assigned to one of its adjacent vertices
        available = [False] * self.num_of_v

        # Assign colors to remaining V-1 vertices
        for u in range(1, self.num_of_v):

            # Process all adjacent vertices and
            # flag their colors as unavailable
            for i in self.adjacents.get(u):
                if (result[i] != -1):
                    available[result[i]] = True

            # Find the first available color
            cr = 0
            while cr < self.num_of_v:
                # if available[cr] == False:
                if not available[cr]:

                    break

                cr += 1

            # Assign the found color
            result[u] = cr

            # Reset the values back to false
            # for the next iteration
            for i in self.adjacents.get(u):
                if result[i] != -1:
                    available[result[i]] = False

        # Pint the result
        for u in range(self.num_of_v):
            print("Vertex", u, " --->  Color", result[u])


if __name__ == "__main__":
    g1 = Graph(5)
    # (assigned color)
    #     (1)1
    #      / | \
    # (0)0   |(0)3---4(1)
    #      \ | /
    #     (2)2
    g1.add_edge(0, 1)
    g1.add_edge(0, 2)
    g1.add_edge(1, 2)
    g1.add_edge(1, 3)
    g1.add_edge(2, 3)
    g1.add_edge(3, 4)
    print("Coloring of graph 1 ")
    g1.greedy_coloring()

    g2 = Graph(5)
    # (assigned color)
    #     (1)1
    #      / | \
    # (0)0   |(3)4---3(0)
    #      \ | /
    #     (2)2
    g2.add_edge(0, 1)
    g2.add_edge(0, 2)
    g2.add_edge(1, 2)
    g2.add_edge(1, 4)
    g2.add_edge(2, 4)
    g2.add_edge(4, 3)
    print("\nColoring of graph 2")
    g2.greedy_coloring()