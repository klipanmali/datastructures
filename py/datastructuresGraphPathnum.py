# Python 3 program to count all paths
# from a source to a destination in directed graph
# similar solution for detecting cycle in directed graph
# using stack instead of copying path vertices os better solution
from collections import defaultdict


class Graph:

    def __init__(self, num_of_vertices):
        self.graph = defaultdict(list)

    def add_edge(self, vertex, other_vertex):
        self.graph[vertex].append(other_vertex)

    def path_num_utils(self, vertex, end_vertex, num_of_paths, path_vertices):
        if vertex not in path_vertices:
            path_vertices.append(vertex)
            if vertex != end_vertex:
                for next_vertex in self.graph[vertex]:
                    if next_vertex not in path_vertices:
                        copy_path_vertices = list(path_vertices)
                        num_of_paths = self.path_num_utils(next_vertex, end_vertex, num_of_paths, copy_path_vertices)
            else:
                # If current vertex is same as
                # destination, then increment count
                print('bingo')
                print(path_vertices)
                num_of_paths += 1

        return num_of_paths

    def get_path_number(self, start_vertex, end_vertex):
        num_of_paths = 0
        path_vertices = []
        return self.path_num_utils(start_vertex, end_vertex, num_of_paths, path_vertices)


if __name__ == '__main__':

    # --->0------>1
    # |   | \ / > |
    # |   |  X    |
    # |  \| /  \  |/
    # ----2     > 3<--
    #             |  |
    #             ----

    g = Graph(4)
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(0, 3)
    g.add_edge(2, 0)
    g.add_edge(2, 1)
    g.add_edge(1, 3)
    g.add_edge(3, 3)

    start = 0
    end = 3
    pathnum = g.get_path_number(start, end)
    print(f'Between {start} and {end} there is {pathnum} paths')
