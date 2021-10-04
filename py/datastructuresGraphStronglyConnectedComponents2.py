# Python program to find strongly connected components in a given
# directed graph using Tarjan's algorithm (single DFS)
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    '''A recursive function that find finds and prints strongly connected
    components using DFS traversal
    u --> The vertex to be visited next
    disc[] --> Stores discovery times of visited vertices
    low[] -- >> earliest visited vertex (the vertex with minimum
                discovery time) that can be reached from subtree
                rooted with current vertex
     st -- >> To store all the connected ancestors (could be part
           of SCC)
     stackMember[] --> bit/index array for faster check whether
                  a node is in stack
    '''
    def scc_util(self, u, low, disc, stack_member, st):

        # Initialize discovery time and low value
        disc[u] = self.time
        low[u] = self.time
        self.time += 1
        stack_member[u] = True
        st.append(u)

        # Go through all vertices adjacent to this
        for v in self.adjacents[u]:

            # If v is not visited yet, then recur for it
            if disc[v] == -1:

                self.scc_util(v, low, disc, stack_member, st)
                # Check if the subtree rooted with v has a connection to
                # one of the ancestors of u
                # Case 1 (per above discussion on Disc and Low value)
                low[u] = min(low[u], low[v])

            elif stack_member[v]:
                '''Update low value of 'u' only if 'v' is still in stack
                (i.e. it's a back edge, not cross edge).
                Case 2 (per above discussion on Disc and Low value) '''
                low[u] = min(low[u], disc[v])
        print("N"+str(u)+" d"+str(disc[u])+" l"+str(low[u]))
        # head node found, pop the stack and print an SCC
        w = -1  # To store stack extracted vertices
        if low[u] == disc[u]:
            while w != u:
                w = st.pop()
                print(w, end=",")
                stack_member[w] = False
            print("")

    # The function to do DFS traversal.
    # It uses recursive SCCUtil()
    def SCC(self):

        # Mark all the vertices as not visited
        # and Initialize parent and visited,
        # and ap(articulation point) arrays
        disc = [-1] * self.num_of_v
        low = [-1] * self.num_of_v
        stack_member = [False] * self.num_of_v
        st = []

        # Call the recursive helper function
        # to find articulation points
        # in DFS tree rooted with vertex 'i'
        for i in range(self.num_of_v):
            if disc[i] == -1:
                self.scc_util(i, low, disc, stack_member, st)


if __name__ == "__main__":
    g1 = Graph(5)
    # 1---->0---->3---->4
    # A    /
    # |   /
    # 2<--
    g1.add_edge(1, 0)
    g1.add_edge(0, 2)
    g1.add_edge(2, 1)
    g1.add_edge(0, 3)
    g1.add_edge(3, 4)
    print("SSC in first graph ")
    g1.SCC()

    g2 = Graph(4)
    # 0---->1---->2---->3
    g2.add_edge(0, 1)
    g2.add_edge(1, 2)
    g2.add_edge(2, 3)
    print("nSSC in second graph ")
    g2.SCC()

    g3 = Graph(7)
    #       6
    #       A
    #       |
    # 0---->1---->3---->5
    # A    / \          A
    # |   /   \         |
    # 2<--     -->4-----+
    g3.add_edge(0, 1)
    g3.add_edge(1, 2)
    g3.add_edge(2, 0)
    g3.add_edge(1, 3)
    g3.add_edge(1, 4)
    g3.add_edge(1, 6)
    g3.add_edge(3, 5)
    g3.add_edge(4, 5)
    print("nSSC in third graph ")
    g3.SCC()

    g4 = Graph(10)
    g4.add_edge(0, 1)
    g4.add_edge(0, 3)
    g4.add_edge(1, 2)
    g4.add_edge(1, 4)
    g4.add_edge(2, 0)
    g4.add_edge(2, 6)
    g4.add_edge(3, 2)
    g4.add_edge(4, 5)
    g4.add_edge(4, 6)
    g4.add_edge(5, 6)
    g4.add_edge(5, 7)
    g4.add_edge(5, 8)
    g4.add_edge(5, 9)
    g4.add_edge(6, 4)
    g4.add_edge(7, 9)
    g4.add_edge(8, 9)
    g4.add_edge(9, 8)
    print("nSSC in fourth graph ")
    g4.SCC()

    g5 = Graph(5)
    # +-----------------+
    # v                 |
    # 0---->1---->2---->3
    #             A \
    #             |   \
    #             |     v
    #             +-----4
    g5.add_edge(0, 1)
    g5.add_edge(1, 2)
    g5.add_edge(2, 3)
    g5.add_edge(2, 4)
    g5.add_edge(3, 0)
    g5.add_edge(4, 2)
    print("nSSC in fifth graph ")
    g5.SCC()
