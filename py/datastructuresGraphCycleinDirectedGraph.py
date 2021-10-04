# Detect Cycle in a Directed Graph
# this solution is using DFS
# valid for disconnected graph too
# similar solution as for num of paths in graph

from collections import defaultdict


class Graph:

    def __init__(self, num_of_vertices):
        self.adjacent = defaultdict(list)
        self.num_of_vertices = num_of_vertices

    def add_edge(self, start_vertex, end_vertex):
        self.adjacent[start_vertex].append(end_vertex)

    def is_cyclic_util(self, v, visited, reached_stack):

        # reached same vertex, cycle detected
        if reached_stack[v]:
            print("cycle", end=" ")
            print(reached_stack)
            return True

        # vertex can be visited but not in reached stack if traverse continues from disconnected vertex
        if visited[v]:
            return False

        # "add" vertex to stack
        reached_stack[v] = True
        visited[v] = True

        cycle_detected = False
        for adjacent_v in self.adjacent[v]:
            if self.is_cyclic_util(adjacent_v, visited, reached_stack):
                cycle_detected = True
                break

        # "remove" vertex from stack
        reached_stack[v] = False
        return cycle_detected

    def is_cyclic(self):
        # need both because of  disconnected graph
        visited = [False] * self.num_of_vertices
        reached_stack = [False] * self.num_of_vertices

        for vertex in range(self.num_of_vertices):
            if self.is_cyclic_util(vertex, visited, reached_stack):
                return True

        return False


if __name__ == "__main__":

    # +->0--->1
    # |  |   /
    # |  |  /
    # |  | /
    # +--2-----3<-+
    #          |  |
    #          +--+

    g = Graph(4)
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(1, 2)
    g.add_edge(2, 0)
    g.add_edge(2, 3)
    g.add_edge(3, 3)
    if g.is_cyclic() == 1:
        print("Graph has a cycle")
    else:
        print("Graph has no cycle")
