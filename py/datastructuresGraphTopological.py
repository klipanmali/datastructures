# Python program to print topological sorting of a DAG
from collections import defaultdict
from collections import deque


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    def topo_sort_util(self, vertex, visited, stack):
        visited[vertex] = True
        for adjacent in self.adjacents[vertex]:
            if not visited[adjacent]:
                self.topo_sort_util(adjacent, visited, stack)

        stack.append(vertex)

    def topological_sort(self):

        visited = [False] * self.num_of_v
        stack = deque()
        for i in range(0, self.num_of_v):
            if not visited[i]:
                self.topo_sort_util(i, visited, stack)

        while stack:
            print(stack.pop(), end=",")
        print()


if __name__ == "__main__":

    g = Graph(6)

    # 5            4
    # |  \       / |
    # |   \     /  |
    # v    > 0 <   |
    # 2            |
    # |            v
    # +--->3------>1

    g.add_edge(5, 0)
    g.add_edge(5, 2)
    g.add_edge(4, 0)
    g.add_edge(4, 1)
    g.add_edge(2, 3)
    g.add_edge(3, 1)

    print("Topological sort of graph is")
    g.topological_sort()
