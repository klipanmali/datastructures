# Python program to detect cycle in
# a directed graph by color
from collections import defaultdict


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.nodes = defaultdict(list)

    def add_edge(self, start_v, end_v):
        self.nodes[start_v].append(end_v)

    def DFS_util(self, node, colors):
        # GRAY :  This vertex is being processed (DFS
        #         for this vertex has started, but not
        #         ended (or this vertex is in function
        #         call stack)
        colors[node] = "GRAY"

        for adj in self.nodes[node]:

            if colors[adj] == "GRAY":
                return True

            if colors[adj] == "WHITE" and self.DFS_util(adj, colors):
                return True

        colors[node] = "BLACK"
        return False

    def is_cycle_detected(self):

        colors = ["WHITE"] * self.num_of_v
        for node in range(0, self.num_of_v):
            if colors[node] == "WHITE":
                if self.DFS_util(node, colors):
                    return True

        return False


if __name__ == "__main__":

    num_of_v = 4
    g = Graph(4)

#    0---->1
#    A \   |
#    |   \ |     +-+
#    |    vv     v |
#    +---- 2---->3-+
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(1, 2)
#   g.add_edge(2, 0)
    g.add_edge(2, 3)
    g.add_edge(3, 3)

    print("cycle detected" if g.is_cycle_detected() else "Yeah no cycle")
