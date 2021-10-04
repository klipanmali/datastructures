# Python program to check if a given directed graph is strongly
# connected or not
# Following is Kosaraju’s DFS based simple algorithm that does two DFS traversals of graph:
# if every node can be reached from a vertex v, and every node can reach v, then the graph is strongly connected
# BFS can be used as well
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    def DFS_Util(self, vertex, visited):
        visited[vertex] = True

        for adj in self.adjacents[vertex]:
            if not visited[adj]:
                self.DFS_Util(adj,visited)

    def transpose(self):
        gt = Graph(self.num_of_v)
        for vertex in range(self.num_of_v):
            for adj in self.adjacents[vertex]:
                gt.add_edge(adj,vertex)
        return gt

    def is_SC(self):
        visited = [False] * self.num_of_v
        self.DFS_Util(0,visited)
        for v in range(self.num_of_v):
            if not visited[v]:
                return False

        gt = self.transpose();
        visited = [False] * self.num_of_v
        gt.DFS_Util(0,visited)
        for v in range(self.num_of_v):
            if not visited[v]:
                return False

        return True


if __name__ == "__main__":

    g1 = Graph(5)
    #           +----+
    #           v    |
    # 0--->1--->2--->4
    # A         |
    # |         v
    # +---------3

    g1.add_edge(0, 1)
    g1.add_edge(1, 2)
    g1.add_edge(2, 3)
    g1.add_edge(3, 0)
    g1.add_edge(2, 4)
    g1.add_edge(4, 2)
    print("Yes" if g1.is_SC() else "No")

    g2 = Graph(4)
    g2.add_edge(0, 1)
    g2.add_edge(1, 2)
    g2.add_edge(2, 3)
    print("Yes" if g2.is_SC() else "No")
