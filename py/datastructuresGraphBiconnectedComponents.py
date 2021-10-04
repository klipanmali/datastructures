# Python program to find biconnected components in a given
# undirected graph
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0
        self.count = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)
        self.adjacents[end_v].append(start_v)

    '''A recursive function that finds and prints strongly connected
    components using DFS traversal
    u --> The vertex to be visited next
    disc[] --> Stores discovery times of visited vertices
    low[] -- >> earliest visited vertex (the vertex with minimum
               discovery time) that can be reached from subtree
               rooted with current vertex
    st -- >> To store visited edges'''
    def bcc_util(self, u, parent, low, disc, st):

        # Count of children in current node
        children = 0

        # Initialize discovery time and low value
        disc[u] = self.time
        low[u] = self.time
        self.time += 1

        # Recur for all the vertices adjacent to this vertex
        for v in self.adjacents[u]:
            # If v is not visited yet, then make it a child of u
            # in DFS tree and recur for it
            if disc[v] == -1:
                parent[v] = u
                children += 1
                st.append((u, v))  # store the edge in stack
                self.bcc_util(v, parent, low, disc, st)

                # Check if the subtree rooted with v has a connection to
                # one of the ancestors of u
                # Case 1 -- per Strongly Connected Components Article
                low[u] = min(low[u], low[v])

                # If u is an articulation point, pop
                # all edges from stack till (u, v)
                if parent[u] == -1 and children > 1 or parent[u] != -1 and low[v] >= disc[u]:
                    self.count += 1  # increment count
                    w = -1
                    while w != (u, v):
                        w = st.pop()
                        print(w, end=",")
                    print("")

            elif v != parent[u] and low[u] > disc[v]:
                '''Update low value of 'u' only of 'v' is still in stack
                (i.e. it's a back edge, not cross edge).
                Case 2
                -- per Strongly Connected Components Article'''

                low[u] = min(low[u], disc[v])

                st.append((u, v))

    # The function to do DFS traversal.
    # It uses recursive BCCUtil()
    def bcc(self):

        # Initialize disc and low, and parent arrays
        disc = [-1] * self.num_of_v
        low = [-1] * self.num_of_v
        parent = [-1] * self.num_of_v
        st = []

        # Call the recursive helper function to
        # find articulation points
        # in DFS tree rooted with vertex 'i'
        for i in range(self.num_of_v):
            if disc[i] == -1:
                self.bcc_util(i, parent, low, disc, st)

            # If stack is not empty, pop all edges from stack
            if st:
                self.count = self.count + 1

                while st:
                    w = st.pop()
                    print(w, end=",")
                print("")


if __name__ == "__main__":
    g = Graph(12)

    # 4---3        7
    # |  /|       / \
    # |/  |      /   \
    # 2---1-----5-----8----9
    #     |     |
    #     |     |
    #     0-----6    10----11
    g.add_edge(0, 1)
    g.add_edge(1, 2)
    g.add_edge(1, 3)
    g.add_edge(2, 3)
    g.add_edge(2, 4)
    g.add_edge(3, 4)
    g.add_edge(1, 5)
    g.add_edge(0, 6)
    g.add_edge(5, 6)
    g.add_edge(5, 7)
    g.add_edge(5, 8)
    g.add_edge(7, 8)
    g.add_edge(8, 9)
    g.add_edge(10, 11)

    g.bcc()
    print(f'Above are {g.count} biconnected components in graph')
