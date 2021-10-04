# Python program to find bridges in a given undirected graph
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)

    '''A recursive function that finds and prints bridges
    using DFS traversal
    u --> The vertex to be visited next
    visited[] --> keeps tract of visited vertices
    disc[] --> Stores discovery times of visited vertices
    parent[] --> Stores parent vertices in DFS tree'''

    def bridge_util(self, u, visited, parent, low, disc):

        # Mark the current node as visited and print it
        visited[u] = True

        # Initialize discovery time and low value
        disc[u] = self.time
        low[u] = self.time
        self.time += 1

        # Recur for all the vertices adjacent to this vertex
        for v in self.adjacents[u]:
            # If v is not visited yet, then make it a child of u
            # in DFS tree and recur for it
            if not visited[v]:
                parent[v] = u
                self.bridge_util(v, visited, parent, low, disc)

                # Check if the subtree rooted with v has a connection to
                # one of the ancestors of u
                low[u] = min(low[u], low[v])

                ''' If the lowest vertex reachable from subtree
                under v is below u in DFS tree, then u-v is
                a bridge'''
                if low[v] > disc[u]:
                    print("%d %d" % (u, v))
            elif v != parent[u]:  # Update low value of u for parent function calls.
                low[u] = min(low[u], disc[v])

    # DFS based function to find all bridges. It uses recursive
    # function bridgeUtil()
    def bridge(self):

        # Mark all the vertices as not visited and Initialize parent and visited,
        # and ap(articulation point) arrays
        visited = [False] * self.num_of_v
        disc = [float("Inf")] * self.num_of_v
        low = [float("Inf")] * self.num_of_v
        parent = [-1] * self.num_of_v

        # Call the recursive helper function to find bridges
        # in DFS tree rooted with vertex 'i'
        for i in range(self.num_of_v):
            if not visited[i]:
                self.bridge_util(i, visited, parent, low, disc)


if __name__ == "__main__":
    g1 = Graph(5)

    # 1----0----3
    # |   /     |
    # | /       |
    # 2         4
    g1.add_edge(1, 0)
    g1.add_edge(0, 2)
    g1.add_edge(2, 1)
    g1.add_edge(0, 3)
    g1.add_edge(3, 4)

    print("Bridges in first graph ")
    g1.bridge()

    g2 = Graph(4)

    # 0----1----2----3
    g2.add_edge(0, 1)
    g2.add_edge(1, 2)
    g2.add_edge(2, 3)
    print("\nBridges in second graph ")
    g2.bridge()

    g3 = Graph(7)

    # 0----1----3----5
    # |  / | \  |    |
    # | /  |  \ |    |
    # 2    6    4----+
    g3.add_edge(0, 1)
    g3.add_edge(1, 2)
    g3.add_edge(2, 0)
    g3.add_edge(1, 3)
    g3.add_edge(1, 4)
    g3.add_edge(1, 6)
    g3.add_edge(3, 5)
    g3.add_edge(4, 5)
    print("\nBridges in third graph ")
    g3.bridge()
