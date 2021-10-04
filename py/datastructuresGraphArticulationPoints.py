# Python program to find articulation points in an undirected graph (Cut vertices)
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)


    '''A recursive function that find articulation points
     using DFS traversal
     u --> The vertex to be visited next
     visited[] --> keeps track of visited vertices
     disc[] --> Stores discovery times of visited vertices
     parent[] --> Stores parent vertices in DFS tree
     ap[] --> Store articulation points'''

    def ap_util(self, u, visited, ap, parent, low, disc):

        # Count of children in current node
        children = 0

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
                children += 1
                self.ap_util(v, visited, ap, parent, low, disc)

                # Check if the subtree rooted with v has a connection to
                # one of the ancestors of u
                low[u] = min(low[u], low[v])

                # (2) If u is not root and low value of one of its child is more
                # than discovery value of u.
                if parent[u] != -1 and low[v] >= disc[u]:
                    ap[u] = True

                    # Update low value of u for parent function calls
            elif v != parent[u]:
                low[u] = min(low[u], disc[v])

        # u is an articulation point in following cases
        # (1) u is root of DFS tree and has two or more children.
        if parent[u] == -1 and children > 1:
            ap[u] = True

    # The function to do DFS traversal. It uses recursive APUtil()
    def articulation_points(self):

        # Mark all the vertices as not visited
        # and Initialize parent and visited,
        # and ap(articulation point) arrays
        visited = [False] * self.num_of_v
        disc = [float("Inf")] * self.num_of_v
        low = [float("Inf")] * self.num_of_v
        parent = [-1] * self.num_of_v
        ap = [False] * self.num_of_v  # To store articulation points

        # Call the recursive helper function
        # to find articulation points
        # in DFS tree rooted with vertex 'i'
        for i in range(self.num_of_v):
            if not visited[i]:
                self.ap_util(i, visited, ap, parent, low, disc)

        for index, value in enumerate(ap):
            if value:
                print(index)


if __name__ == "__main__":

    g1 = Graph(5)
    # 1----0----3----4
    # |   /
    # | /
    # 2
    g1.add_edge(1, 0)
    g1.add_edge(0, 2)
    g1.add_edge(2, 1)
    g1.add_edge(0, 3)
    g1.add_edge(3, 4)
    print("\nArticulation points in first graph ")
    g1.articulation_points()

    g2 = Graph(4)
    # 0----1----2----3
    g2.add_edge(0, 1)
    g2.add_edge(1, 2)
    g2.add_edge(2, 3)
    print("\nArticulation points in second graph ")
    g2.articulation_points()

    g3 = Graph(7)
    # 6
    # |
    # 0----1----3----5
    # |   / \        |
    # | /     \      |
    # 2         4----+
    g3.add_edge(0, 1)
    g3.add_edge(1, 2)
    g3.add_edge(2, 0)
    g3.add_edge(1, 3)
    g3.add_edge(1, 4)
    g3.add_edge(1, 6)
    g3.add_edge(3, 5)
    g3.add_edge(4, 5)
    print("\nArticulation points in third graph ")
    g3.articulation_points()
