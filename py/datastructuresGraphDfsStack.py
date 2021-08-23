# Python3 program to print DFS traversal
# from a given given graph, version using recursion
from collections import defaultdict
from queue import LifoQueue


class Graph:

    def __init__(self):
        self.graph = defaultdict(list)

    def add_edge(self,begin_vertex, end_vertex):
        self.graph[begin_vertex].append(end_vertex)

    def dfs_util(self,start_vertex,visited_vertices):
        stack = LifoQueue()
        stack.put(start_vertex)

        while not stack.empty():
            vertex = stack.get()
            if vertex not in visited_vertices:
                print(vertex, end=" ")
                visited_vertices.add(vertex)
                edges = self.graph[vertex]
                for end_vertex in edges:
                    if end_vertex not in visited_vertices:
                        stack.put(end_vertex)

    def dfs_stack(self,start_vertex):
        visited_vertices = set()
        self.dfs_util(start_vertex,visited_vertices)


def main():
    # solution using stack
    #         ---0------>1
    #         |  |\     /
    #         |  |    /
    #         |  |  /
    #         -->2<-       3<---
    #             --------> |  |
    #                       ----
    # Create a graph given in
    # the above diagram

    g = Graph()
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(1, 2)
    g.add_edge(2, 0)
    g.add_edge(2, 3)
    g.add_edge(3, 3)

    start_vertex = 2
    print("Following is DFS from (starting from vertex",start_vertex, ")")
    g.dfs_stack(start_vertex)


if __name__ == '__main__':
    main()