# Python implementation of Kosaraju's algorithm
# to print all Strongly connected components
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    def DFS_util(self, vertex, visited):
        visited[vertex] = True
        print(vertex, end=",")

        for adj in self.adjacents[vertex]:
            if not visited[adj]:
                self.DFS_util(adj, visited)

    def transpose(self):
        gt = Graph(self.num_of_v)
        for vertex in range(self.num_of_v):
            for adj in self.adjacents[vertex]:
                gt.add_edge(adj, vertex)
        return gt

    def fill_stack(self, vertex, visited, stack):
        # Mark the current node as visited
        visited[vertex] = True
        # Recur for all the vertices adjacent to this vertex
        for i in self.adjacents[vertex]:
            if not visited[i]:
                self.fill_stack(i, visited, stack)
        stack.append(vertex)

    # The main function that finds and prints all strongly
    # connected components
    def print_sscs(self):
        stack = []

        # Mark all the vertices as not visited (For first DFS)
        visited = [False] * self.num_of_v

        # Fill vertices in stack according to their finishing
        # times
        for i in range(self.num_of_v):
            if not visited[i]:
                self.fill_stack(i, visited, stack)

        # Create a reversed graph
        gr = self.transpose()

        # Mark all the vertices as not visited (For second DFS)
        visited = [False] * self.num_of_v

        # Now process all vertices in order defined by Stack
        while stack:
            i = stack.pop()
            if not visited[i]:
                gr.DFS_util(i, visited)
                print("")


if __name__ == "__main__":
    g = Graph(6)

    # 1---->0--->3--->4--->5
    # A   /           A    |
    # |  /            +----+
    # 2<-

    g.add_edge(1, 0)
    g.add_edge(0, 2)
    g.add_edge(2, 1)
    g.add_edge(0, 3)
    g.add_edge(3, 4)
    g.add_edge(4, 5)
    g.add_edge(5, 4)

    print("Following are strongly connected components in given graph")
    g.print_sscs()
