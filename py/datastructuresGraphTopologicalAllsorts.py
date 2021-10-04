# All possible Topological Sorts, using in_degree
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)

    def add_edge(self, source_v, dest_v):
        self.adjacents[source_v].append(dest_v)

    def all_topo_sorts(self):

        visited = [False] * self.num_of_v
        in_degree = [0] * self.num_of_v
        for vert in range(0, self.num_of_v):
            for adj in self.adjacents[vert]:
                in_degree[adj] += 1

        stack = []
        self.all_topo_sorts_util(visited, in_degree, stack)

    def all_topo_sorts_util(self, visited, in_degree, stack):

        for v in range(self.num_of_v):

            if in_degree[v] == 0 and not visited[v]:

                visited[v] = True
                stack.append(v)
                for a in self.adjacents[v]:
                    in_degree[a] -= 1
                self.all_topo_sorts_util(visited, in_degree, stack)

                visited[v] = False
                stack.pop()
                for a in self.adjacents[v]:
                    in_degree[a] += 1

        if len(stack) == self.num_of_v:
            print(stack)

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

    print("All possibel topological sorts")
    g.all_topo_sorts()
