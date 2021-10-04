# Python program to check if a given graph is Eulerian or not
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)

    # A function used by isConnected
    def dfs_util(self, v, visited):
        # Mark the current node as visited
        visited[v] = True

        # Recur for all the vertices adjacent to this vertex
        for i in self.adjacents[v]:
            if not visited[i]:
                self.dfs_util(i, visited)

    '''Method to check if all non-zero degree vertices are
    connected. It mainly does DFS traversal starting from
    node with non-zero degree'''
    def is_connected(self):

        # Mark all the vertices as not visited
        visited = [False] * self.num_of_v

        #  Find a vertex with non-zero degree
        for i in range(self.num_of_v):
            if len(self.adjacents[i]) > 1: # ovdje nesto smrdi
                break

        # If there are no edges in the graph, return true
        if i == self.num_of_v - 1: # ovdje nedto smrdi
            return True

        # Start DFS traversal from a vertex with non-zero degree
        self.dfs_util(i, visited)

        # Check if all non-zero degree vertices are visited
        for i in range(self.num_of_v):
            if visited[i] == False and len(self.adjacents[i]) > 0:
                return False

        return True

    '''The function returns one of the following values
    0 --> If graph is not Eulerian
    1 --> If graph has an Euler path (Semi-Eulerian)
    2 --> If graph has an Euler Circuit (Eulerian)  '''
    def is_eulerian(self):
        # Check if all non-zero degree vertices are connected
        if not self.is_connected():
            return 0
        else:
            # Count vertices with odd degree
            odd = 0
            for i in range(self.num_of_v):
                if len(self.adjacents[i]) % 2 != 0:
                    odd += 1

            '''If odd count is 2, then semi-eulerian.
            If odd count is 0, then eulerian
            If count is more than 2, then graph is not Eulerian
            Note that odd count can never be 1 for undirected graph'''
            if odd == 0:
                return 2
            elif odd == 2:
                return 1
            elif odd > 2:
                return 0

    # Function to run test cases
    def test(self):
        res = self.is_eulerian()
        if res == 0:
            print("graph is not Eulerian")
        elif res == 1:
            print("graph has a Euler path")
        else:
            print("graph has a Euler cycle")


if __name__ == "__main__":
    # Let us create and test graphs shown in above figures
    g1 = Graph(5)
    # 1---0---3
    # |  /    |
    # | /     |
    # 2       4
    g1.add_edge(1, 0)
    g1.add_edge(0, 2)
    g1.add_edge(2, 1)
    g1.add_edge(0, 3)
    g1.add_edge(3, 4)
    g1.test()

    g2 = Graph(5)
    # 1---0---3
    # |  / \  |
    # | /   \ |
    # 2       4
    g2.add_edge(1, 0)
    g2.add_edge(0, 2)
    g2.add_edge(2, 1)
    g2.add_edge(0, 3)
    g2.add_edge(3, 4)
    g2.add_edge(4, 0)
    g2.test()

    g3 = Graph(5)
    # +-------+
    # |       |
    # 1---0---3
    # |  /    |
    # | /     |
    # 2       4
    g3.add_edge(1, 0)
    g3.add_edge(0, 2)
    g3.add_edge(2, 1)
    g3.add_edge(0, 3)
    g3.add_edge(3, 4)
    g3.add_edge(1, 3)
    g3.test()

    # Let us create a graph with 3 vertices
    # connected in the form of cycle
    # 0---1---2
    # |       |
    # +-------+
    g4 = Graph(3)
    g4.add_edge(0, 1)
    g4.add_edge(1, 2)
    g4.add_edge(2, 0)
    g4.test()

    # Let us create a graph with all vertices
    # with zero degree
    g5 = Graph(3)
    g5.test()
