# Python Program to detect cycle in an undirected graph
# because disconnected undirected graph is actually two undirected graph
# it is enough to monitor visited vertices, and parent of vertex
# this is not valid for disconnected directed graph
# this solution is using DFS

from collections import defaultdict


class Graph:

    def __init__(self, num_of_vertices):
        self.num_of_vertices = num_of_vertices
        self.adjacent = defaultdict(list)

    def add_edge(self, start_vertex, end_vertex):
        self.adjacent[start_vertex].append(end_vertex)
        self.adjacent[end_vertex].append(start_vertex)

    def is_cycle_util(self, vertex, parent_vertex, visited):
        visited[vertex] = True
        for adjacent_v in self.adjacent[vertex]:
            if not visited[adjacent_v]:
                if self.is_cycle_util(adjacent_v, vertex, visited):
                    return True

            # If an adjacent vertex is
            # visited and not parent
            # of current vertex,
            # then there is a cycle
            elif adjacent_v != parent_vertex:
                return True

        return False

    def is_cyclic(self):
        visited = [False] * self.num_of_vertices

        cycle_detected = False
        # do DFS through all the unvisited(/unconnected) nodes
        for v in range(self.num_of_vertices):
            if not visited[v]:
                if self.is_cycle_util(v, -1, visited):
                    cycle_detected = True
                    break

        return cycle_detected


if __name__ == "__main__":

    #   1---0---3---4
    #   | /
    #   2

    g = Graph(5)
    g.add_edge(1, 0)
    g.add_edge(1, 2)
    g.add_edge(2, 0)
    g.add_edge(0, 3)
    g.add_edge(3, 4)

    # if g.is_cyclic():
    #     print("Graph contains cycle")
    # else:
    #     print("Graph does not contain cycle ")

    # 0--1--2

    g1 = Graph(3)
    g1.add_edge(0, 1)
    g1.add_edge(1, 2)
    # g1.add_edge(2,2)

    if g1.is_cyclic():
        print("Graph contains cycle")
    else:
        print("Graph does not contain cycle ")
