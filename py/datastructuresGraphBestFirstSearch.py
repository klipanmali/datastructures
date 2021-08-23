# Best First Search is to use an evaluation function to decide which adjacent is most promising and then explore.
# Priority Queue is used here as evaluation function
from collections import defaultdict
from queue import PriorityQueue


class Graph:

    def __init__(self, num_of_vertices):
        self.num_of_vertices = num_of_vertices
        # self.adjacent = [[] for i in range(num_of_vertices)]
        self.adjacent = defaultdict(list)

    def add_edge(self, from_vertex, to_vertex, cost):
        self.adjacent[from_vertex].append((to_vertex, cost))

    def best_first_search(self, start_vertex, target_vertex):
        visited = [False] * self.num_of_vertices
        priority_Q = PriorityQueue()
        priority_Q.put((0, start_vertex))
        while priority_Q.empty() == False:
            vertex = priority_Q.get()[1]
            print(vertex, end=" ")
            if vertex == target_vertex:
                break

            for adj_vertex, cost in self.adjacent[vertex]:
                if visited[adj_vertex] == False:
                    visited[adj_vertex] = True
                    priority_Q.put((cost, adj_vertex))
        print()


if __name__ == "__main__":

    graph = Graph(14)
    graph.add_edge(0, 1, 3)
    graph.add_edge(0, 2, 6)
    graph.add_edge(0, 3, 5)
    graph.add_edge(1, 4, 9)
    graph.add_edge(1, 5, 8)
    graph.add_edge(2, 6, 12)
    graph.add_edge(2, 7, 14)
    graph.add_edge(3, 8, 7)
    graph.add_edge(8, 9, 5)
    graph.add_edge(8, 10, 6)
    graph.add_edge(9, 11, 1)
    graph.add_edge(9, 12, 10)
    graph.add_edge(9, 13, 2)

    graph.best_first_search(0,10)


