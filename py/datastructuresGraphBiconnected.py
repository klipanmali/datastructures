# A Python program to find if a given undirected graph is biconnected
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)

    '''A recursive function that returns true if there is an articulation
    point in given graph, otherwise returns false.
    This function is almost same as isAPUtil()
    u --> The vertex to be visited next
    visited[] --> keeps tract of visited vertices
    disc[] --> Stores discovery times of visited vertices
    parent[] --> Stores parent vertices in DFS tree'''
    def is_bcUtil(self, u, visited, parent, low, disc):

        # Count of children in current node
        children = 0

        # Mark the current node as visited and print it
        visited[u] = True

        # Initialize discovery time and low valu
        disc[u] = self.time
        low[u] = self.time
        self.time += 1

        # Recur for all the vertices adjacent to this vertex
        for v in self.adjacents[u]:
            # If v is not visited yet, then make it a child of u
            # in DFS tree and recur for it
            if not visited[v]:
                parent[v] = u
                children += 1
                if self.is_bcUtil(v, visited, parent, low, disc):
                    return True

                # Check if the subtree rooted with v has a connection to
                # one of the ancestors of u
                low[u] = min(low[u], low[v])

                # u is an articulation point in following cases
                # (1) u is root of DFS tree and has two or more children.
                if parent[u] == -1 and children > 1:
                    return True

                # (2) If u is not root and low value of one of its child is more
                # than discovery value of u.
                if parent[u] != -1 and low[v] >= disc[u]:
                    return True

            elif v != parent[u]:  # Update low value of u for parent function calls.
                low[u] = min(low[u], disc[v])

        return False

        # The main function that returns true if graph is Biconnected,
        # otherwise false. It uses recursive function isBCUtil()
    def is_bc(self):

        # Mark all the vertices as not visited and Initialize parent and visited,
        # and ap(articulation point) arrays
        visited = [False] * self.num_of_v
        disc = [float("Inf")] * self.num_of_v
        low = [float("Inf")] * self.num_of_v
        parent = [-1] * self.num_of_v

        # Call the recursive helper function to find if there is an
        # articulation points in given graph. We do DFS traversal starting
        # from vertex 0
        if self.is_bcUtil(0, visited, parent, low, disc):
            return False

        '''Now check whether the given graph is connected or not.
        An undirected graph is connected if all vertices are
        reachable from any starting point (we have taken 0 as
        starting point)'''
        if any(not i for i in visited):
            return False

        return True


if __name__ == "__main__":
    g1 = Graph(2)
    # 0---1
    g1.add_edge(0, 1)
    print("Yes" if g1.is_bc() else "No")

    g2 = Graph(5)
    # 1---0---3
    #  \  |   |
    #   \ |   |
    #     2---4
    g2.add_edge(1, 0)
    g2.add_edge(0, 2)
    g2.add_edge(2, 1)
    g2.add_edge(0, 3)
    g2.add_edge(3, 4)
    g2.add_edge(2, 4)
    print("Yes" if g2.is_bc() else "No")

    g3 = Graph(3)
    # 0---1---2
    g3.add_edge(0, 1)
    g3.add_edge(1, 2)
    print("Yes" if g3.is_bc() else "No")

    g4 = Graph(5)
    # 1---0---3
    #  \  |   |
    #   \ |   |
    #     2   4
    g4.add_edge(1, 0)
    g4.add_edge(0, 2)
    g4.add_edge(2, 1)
    g4.add_edge(0, 3)
    g4.add_edge(3, 4)
    print("Yes" if g4.is_bc() else "No")

    g5 = Graph(3)
    # 0---1
    #  \  |
    #   \ |
    #     2
    g5.add_edge(0, 1)
    g5.add_edge(1, 2)
    g5.add_edge(2, 0)
    print("Yes" if g5.is_bc() else "No")