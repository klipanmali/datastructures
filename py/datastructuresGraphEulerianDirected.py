# A Python3 program to check if a given directed graph is Eulerian or not
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0
        self.inc = [0] * self.num_of_v # number of incoming edges per vertex

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.inc[end_v] += 1

    def dfs_util(self, v, visited):
        visited[v] = True
        for node in self.adjacents[v]:
            if not visited[node]:
                self.dfs_util(node, visited)

    def get_transpose(self):
        gr = Graph(self.num_of_v)

        for node in range(self.num_of_v):
            for child in self.adjacents[node]:
                gr.add_edge(child, node)

        return gr

    def is_strongly_connected(self):
        visited = [False] * self.num_of_v

        v = 0
        for v in range(self.num_of_v):
            if len(self.adjacents[v]) > 0:
                break

        self.dfs_util(v, visited)

        # If DFS traversal doesn't visit all
        # vertices, then return false.
        for i in range(self.num_of_v):
            if not visited[i]:
                return False

        gr = self.get_transpose()

        visited = [False] * self.num_of_v
        gr.dfs_util(v, visited)

        for i in range(self.num_of_v):
            if not visited[i]:
                return False

        return True

    def is_eulerian_cycle(self):

        # Check if all non-zero degree vertices
        # are connected
        if self.is_strongly_connected() == False:
            return False

        # Check if in degree and out degree of
        # every vertex is same
        for v in range(self.num_of_v):
            if len(self.adjacents[v]) != self.inc[v]:
                return False

        return True


if __name__ == "__main__":
    g = Graph(5);
    # 1--->0--->3
    # A   /  <  |
    # |  /    \ |
    # 2 <       4
    #
    g.add_edge(1, 0);
    g.add_edge(0, 2);
    g.add_edge(2, 1);
    g.add_edge(0, 3);
    g.add_edge(3, 4);
    g.add_edge(4, 0); # comment out to change to not Eulerian
    if g.is_eulerian_cycle():
        print("Given directed graph is eulerian");
    else:
        print("Given directed graph is NOT eulerian");
