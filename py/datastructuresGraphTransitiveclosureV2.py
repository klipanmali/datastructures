# Python program for transitive closure using DFS
# Complexity : O(V^2)
from collections import defaultdict


# This class represents a directed graph using adjacency
# list representation
class GraphClosureV2:

    def __init__(self, vertices):
        # No. of vertices
        self.V = vertices

        # default dictionary to store graph
        self.graph = defaultdict(list)

        # To store transitive closure
        self.tc = [[0 for j in range(self.V)] for i in range(self.V)]

    # function to add an edge to graph
    def add_edge(self, u, v):
        self.graph[u].append(v)

    # A recursive DFS traversal function that finds
    # all reachable vertices for s
    def dfs_util(self, s, v):

        # Mark reachability from s to v as true.
        if s == v:
            # if v in self.graph[v]: #  now the result is the same as for GraphClosure
            self.tc[s][v] = 1

        else:
            self.tc[s][v] = 1

        # Find all the vertices reachable through v
        for i in self.graph[v]:
            if self.tc[s][i] == 0:
                self.dfs_util(s, i)

    # The function to find transitive closure. It uses
    # recursive DFSUtil()
    def transitive_closure(self):
        # Call the recursive helper function to print DFS
        # traversal starting from all vertices one by one
        for i in range(self.V):
            self.dfs_util(i, i)
        print(self.tc)


if __name__ == "__main__":
    # Let us create the following weighted graph
    #           10
    #     (0)------->(3)
    #     |         |\
    #   5 |         |
    #     |         | 1
    #    \|         |
    #     (1)------->(2)
    #         3
    g = GraphClosureV2(4)
    g.add_edge(0, 1)
    g.add_edge(0, 3)
    g.add_edge(1, 2)
    g.add_edge(2, 3)

    print("Transitive closure matrix is")
    g.transitive_closure()
