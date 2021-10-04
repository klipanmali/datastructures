# Python3 program to print Eulerian circuit in given
# directed graph using Hierholzer algorithm
# The algorithm assumes that the given graph has a Eulerian cycle.
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.time = 0

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    def print_circuit(self):

        # adj represents the adjacency list of
        # the directed graph

        if len(self.adjacents) == 0:
            return  # empty graph

        # Maintain a stack to keep vertices
        # We can start from any vertex, here we start with 0
        curr_path = [0]

        # list to store final circuit
        circuit = []

        while curr_path:

            curr_v = curr_path[-1]

            # If there's remaining edge in adjacency list
            # of the current vertex
            if self.adjacents[curr_v]:

                # Find and remove the next vertex that is
                # adjacent to the current vertex
                next_v = self.adjacents[curr_v].pop()

                # Push the new vertex to the stack
                curr_path.append(next_v)

            # back-track to find remaining circuit
            else:
                # Remove the current vertex from the current path and
                # put it in the curcuit
                circuit.append(curr_path.pop())

        # we've got the circuit, now print it in reverse
        while(circuit):
            i = circuit.pop()
            print(i, end="")
            if i:
                print(" -> ", end="")
        print()


if __name__ == "__main__":
    # Input Graph 1
    g1 = Graph(3)
    #  0-->1
    #  A   |
    #  |   |
    #  2<--+
    g1.add_edge(0, 1)
    g1.add_edge(1, 2)
    g1.add_edge(2, 0)
    print("graph1")
    g1.print_circuit()

    # Input Graph 2
    g2 = Graph(7)
    # +-------------------+
    # | +-->6--------+    |
    # v/             v    |
    # 0<---2--->3--->4--->5
    # |   > <        |
    # |  /   \       |
    # v /     +------+
    # 1
    #
    g2.add_edge(0, 1)
    g2.add_edge(0, 6)
    g2.add_edge(1, 2)
    g2.add_edge(2, 0)
    g2.add_edge(2, 3)
    g2.add_edge(3, 4)
    g2.add_edge(4, 2)
    g2.add_edge(4, 5)
    g2.add_edge(5, 0)
    g2.add_edge(6, 4)
    print("graph2")
    g2.print_circuit()

    # uuupppssss,
    # first you need to check if there is Eulerian path off eulerian cycle
    g3 = Graph(4)
    # 0--->1--->3
    # A   /
    # |  /
    # 2<+
    g3.add_edge(0, 1)
    g3.add_edge(1, 2)
    g3.add_edge(2, 0)
    g3.add_edge(1, 3)
    print("graph3")
    g3.print_circuit()
