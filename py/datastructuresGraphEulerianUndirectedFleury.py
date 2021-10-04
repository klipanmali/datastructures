# Fleury’s Algorithm for printing Eulerian Path or Circuit in undirected graph
# Not very efficient, ime complexity is O ((V+E)2)
# There are better algorithms to print Euler tour, Hierholzer’s Algorithm finds in O(V+E) time.
# Python program print Eulerian Trail in a given Eulerian or Semi-Eulerian Graph
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)

    # This function removes edge u-v from graph
    def remove_edge(self, start_v, end_v):
        self.adjacents[start_v].remove(end_v)
        self.adjacents[end_v].remove(start_v)

    # A DFS based function to count reachable vertices from v
    def dfs_count(self, v, visited):
        count = 1
        visited[v] = True
        for i in self.adjacents[v]:
            if not visited[i]:
                count = count + self.dfs_count(i, visited)
        return count

    # The function to check if edge u-v can be considered as next edge in
    # Euler Tour
    def is_valid_next_edge(self, u, v):
        # The edge u-v is valid in one of the following two cases:

        #  1) If v is the only adjacent vertex of u
        if len(self.adjacents[u]) == 1:
            return True
        else:
            '''
             2) If there are multiple adjacents, then u-v is not a bridge
                 Do following steps to check if u-v is a bridge

            2.a) count of vertices reachable from u'''
            visited = [False] * self.num_of_v
            count1 = self.dfs_count(u, visited)

            '''2.b) Remove edge (u, v) and after removing the edge, count
                vertices reachable from u'''
            self.remove_edge(u, v)
            visited = [False] * self.num_of_v
            count2 = self.dfs_count(u, visited)

            # 2.c) Add the edge back to the graph
            self.add_edge(u, v)

            # 2.d) If count1 is greater, then edge (u, v) is a bridge
            return False if count1 > count2 else True

    # Print Euler tour starting from vertex u
    def print_euler_util(self, u):
        #Recur for all the vertices adjacent to this vertex
        for v in self.adjacents[u]:
            #If edge u-v is not removed and it's a a valid next edge
            if self.is_valid_next_edge(u, v):
                print("%d-%d " %(u,v)),
                self.remove_edge(u, v)
                self.print_euler_util(v)

    '''The main function that print Eulerian Trail. It first finds an odd
   degree vertex (if there is any) and then calls printEulerUtil()
   to print the path '''

    def print_euler_tour(self):
        # Find a vertex with odd degree
        u = 0
        for i in range(self.num_of_v):
            if len(self.adjacents[i]) % 2 != 0:
                u = i
                break
        # Print tour starting from odd vertex
        print("\n")
        self.print_euler_util(u)


if __name__ == "__main__":
    g1 = Graph(4)
    # 0---2---3
    #  \  |
    #   \ |
    #    1
    g1.add_edge(0, 1)
    g1.add_edge(0, 2)
    g1.add_edge(1, 2)
    g1.add_edge(2, 3)
    g1.print_euler_tour()

    g2 = Graph(3)
    # 0---1---2
    # |       |
    # +-------+
    g2.add_edge(0, 1)
    g2.add_edge(1, 2)
    g2.add_edge(2, 0)
    g2.print_euler_tour()

    g3 = Graph(5)
    #  +----+
    # /      \
    # 1---0---3---4
    # |  /    |   |
    # | /     |   |
    # 2------+    |
    #  \          |
    #   +---------+
    g3.add_edge(1, 0)
    g3.add_edge(0, 2)
    g3.add_edge(2, 1)
    g3.add_edge(0, 3)
    g3.add_edge(3, 4)
    g3.add_edge(3, 2)
    g3.add_edge(3, 1)
    g3.add_edge(2, 4)
    g3.print_euler_tour()

    # with this grap there are problems
    # if there are more than two vertices with odd dergree, ther is no Eulerian pat
    g4 = Graph(6)
    #  0---1---3---4
    #   \  |   |
    #    \ |   |
    #     2    5
    g4.add_edge(0, 1)
    g4.add_edge(1, 2)
    g4.add_edge(2, 0)
    g4.add_edge(1, 3)
    g4.add_edge(3, 4)
    g4.add_edge(3, 5)
    g4.print_euler_tour()

