# Python3 program to print DFS traversal
# from a given given graph, version using recursion
from collections import defaultdict


# This class represents a directed graph using
# adjacency list representation
class Graph:
    
    def __init__(self):
        self.graph = defaultdict(list)

    def add_edge(self, from_vertex, to_vertex):
        self.graph[from_vertex].append(to_vertex)

    def dfs_util(self,vertex,visited_vertices):
        if vertex not in visited_vertices:
            # Mark the current node as visited
            # and print it
            visited_vertices.add(vertex)
            print(vertex, end=" ")

            # Recur for all the vertices
            # adjacent to this vertex
            for v in self.graph[vertex]:
                if v not in visited_vertices:
                    self.dfs_util(v,visited_vertices)

    def dfs_recursive(self,start_vertex):
        visited_vertices = set()
        self.dfs_util(start_vertex,visited_vertices)


def main():
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

    start_vertex = 3
    print("Following is DFS from (starting from vertex",start_vertex, ")")
    g.dfs_recursive(start_vertex)


if __name__ == '__main__':
    main()
