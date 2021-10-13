# Python3 program to find out whether a given graph is Bipartite or not
# valid for undirected disconnected graph , BFS method
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

    # This function returns true if graph G[V][V]
    # is Bipartite, else false
    def is_bipartite_util(self, src):

        # Create a color array to store colors
        # assigned to all vertices. Vertex
        # number is used as index in this array.
        # The value '-1' of self.colorArr[i] is used
        # to indicate that no color is assigned to
        # vertex 'i'. The value 1 is used to indicate
        # first color is assigned and value 0
        # indicates second color is assigned.

        # Assign first color to source

        # Create a queue (FIFO) of vertex numbers and
        # enqueue source vertex for BFS traversal
        queue = []
        queue.append(src)

        # Run while there are vertices in queue
        # (Similar to BFS)
        while queue:

            u = queue.pop()

            # Return false if there is a self-loop
            if self.adjacents.get(u).__contains__(u):
                return False

            for v in self.adjacents.get(u):

                # An edge from u to v exists and
                # destination v is not colored
                if self.colorArr[v] == -1:

                    # Assign alternate color to
                    # this adjacent v of u
                    self.colorArr[v] = 1 - self.colorArr[u]
                    queue.append(v)

                # An edge from u to v exists and destination
                # v is colored with same color as u
                elif self.colorArr[v] == self.colorArr[u]:
                    return False

        # If we reach here, then all adjacent
        # vertices can be colored with alternate
        # color
        return True

    def is_bipartite(self):
        self.colorArr = [-1 for _ in range(self.num_of_v)]
        for i in range(self.num_of_v):
            if self.colorArr[i] == -1:
                if not self.is_bipartite_util(i):
                    return False
        return True


if __name__ == "__main__":
    g1 = Graph(4)
    # 0---1---2
    # |       |
    # +---3---+
    g1.add_edge(0, 1)
    g1.add_edge(1, 2)
    g1.add_edge(2, 3)
    g1.add_edge(3, 0)
    print("Yes" if g1.is_bipartite() else "No")

    g2 = Graph(6)
    # 1---0---4---2
    #     |   |
    #     5---3
    g2.add_edge(0, 1)
    g2.add_edge(0, 4)
    g2.add_edge(2, 4)
    g2.add_edge(3, 4)
    g2.add_edge(3, 5)
    g2.add_edge(5, 0)
    print("Yes" if g2.is_bipartite() else "No")

    g3 = Graph(6)
    # 1---0---4---2
    # |       |
    # 5-------3
    g3.add_edge(0, 1)
    g3.add_edge(0, 4)
    g3.add_edge(2, 4)
    g3.add_edge(3, 4)
    g3.add_edge(3, 5)
    g3.add_edge(5, 1)
    print("Yes" if g3.is_bipartite() else "No")

    g4 = Graph(3)
    # 0---1---2
    # |       |
    # +-------+
    g4.add_edge(0, 1)
    g4.add_edge(1, 2)
    g4.add_edge(2, 0)
    print("Yes" if g4.is_bipartite() else "No")

    g5 = Graph(3)
    # 0---1---2
    # |       |
    # +-------+
    g5.add_edge(0, 1)
    g5.add_edge(1, 2)
    print("Yes" if g5.is_bipartite() else "No")

