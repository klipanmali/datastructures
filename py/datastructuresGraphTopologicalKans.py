# A Python program to print topological sorting of a graph
# using Kahn’s algorithm (in_degrees)
from collections import defaultdict
from queue import Queue


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)

    def add_edge(self, source_v, dest_v):
        self.adjacents[source_v].append(dest_v)

    def topological_sort(self):
        in_degree = [0] * self.num_of_v
        for vert in range(0, self.num_of_v):
            for adj in self.adjacents[vert]:
                in_degree[adj] += 1

        vert_queue = Queue()
        for vert in range(0, self.num_of_v):
            if in_degree[vert] == 0:
                vert_queue.put(vert)

        processed_vert = 0
        topological_queue = Queue()
        while not vert_queue.empty():
            vert = vert_queue.get()
            topological_queue.put(vert)
            processed_vert += 1
            for adj in self.adjacents.get(vert):
                in_degree[adj] -= 1
                if in_degree[adj] == 0:
                    vert_queue.put(adj)

        print(processed_vert)

        if processed_vert != self.num_of_v:
            print("cycle detected in grap")
        else:
            while not topological_queue.empty():
                vert = topological_queue.get()
                print(vert, end=",")

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

    g.add_edge(5, 2)
    g.add_edge(5, 0)
    g.add_edge(4, 0)
    g.add_edge(4, 1)
    g.add_edge(2, 3)
    g.add_edge(3, 1)
    # g.add_edge(1, 4) # cycle

    print("Following is a Topological Sort of the given graph")
    g.topological_sort()
