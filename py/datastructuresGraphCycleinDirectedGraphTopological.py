# Detecting cycle in directed graph using topological sorting
# This doesn't considers self loops
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.visited = [False for i in range(self.num_of_v)]
        # Stack to store the
        # visited vertices in
        # the Topological Sort
        self.visited_stack = []
        self.adjacents = defaultdict(list)

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    def dfs(self, vertex):
        # Set the vertex as visited
        self.visited[vertex] = True

        for adjacent in self.adjacents[vertex]:
            if not self.visited[adjacent]:
                self.dfs(adjacent)

        # Push into the stack on
        # complete visit of vertex
        self.visited_stack.append(vertex)

    # Function to check and return
    # if a cycle exists or not
    def detect_cycle(self):

        # Stores the position of
        # vertex in topological order
        pos = dict()
        ind = 0
        # Store Topological Order
        tsort = []

        # Pop all elements from stack
        while len(self.visited_stack) != 0:
            pos[self.visited_stack[-1]] = ind

            # Push element to get
            # Topological Order
            tsort.append(self.visited_stack[-1])

            ind += 1

            # Pop from the stack
            self.visited_stack.pop()

        for i in range(self.num_of_v):
            for adjacent in self.adjacents[i]:
                first = 0 if i not in pos else pos[i]
                second = 0 if adjacent not in pos else pos[adjacent]

                # If parent vertex
                # does not appear first
                if first > second:
                    # Cycle exists
                    return True

        # Return false if cycle
        # does not exist
        return False


if __name__ == "__main__":

    num_of_v = 4

    # +->0----->1
    # |  |      |
    # |  |      |
    # |  |      |
    # |  V<-----+
    # +--2--------->3
    graph = Graph(num_of_v)
    # Insert edges
    graph.add_edge(0, 1)
    graph.add_edge(0, 2)
    graph.add_edge(1, 2)
    graph.add_edge(2, 0)
    graph.add_edge(2, 3)

    # another graph
    # graph.add_edge(3, 2)
    # graph.add_edge(2, 1)
    # graph.add_edge(1, 0)
    # graph.add_edge(0, 3)

    for i in range(num_of_v):
        if graph.visited[i] is False:
            graph.dfs(i)

    # If cycle exist
    if graph.detect_cycle():
        print('Yes')
    else:
        print('No')
