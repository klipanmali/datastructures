# Python3 Program to print BFS traversal
# from a given source vertex. BFS(int s)
# traverses vertices reachable from s.
from collections import defaultdict
import queue


# This class represents a directed graph
# using adjacency list representation
class Graph:

    # Constructor
    def __init__(self):

        # default dictionary to store graph
        self.graph = defaultdict(list)

    # function to add an edge to graph
    def add_edge(self, u, v):
        self.graph[u].append(v)

    # Function to print a BFS of graph
    def bfs(self, s):

        # Mark all the vertices as not visited
        visited = [False] * (max(self.graph) + 1)

        # Create a queue for BFS
        # Since, queue has been initialized as a list, it will take O(n) time to get the first element (as indexes of other elements have to
        # be moved).
        # better  solution is to use queue or deque
        # qu = []
        qu = queue.Queue()

        # Mark the source node as
        # visited and enqueue it
        qu.put(s)
        visited[s] = True

        while queue:

            # Dequeue a vertex from
            # queue and print it
            s = qu.get()
            print (s, end = " ")

            # Get all adjacent vertices of the
            # dequeued vertex s. If a adjacent
            # has not been visited, then mark it
            # visited and enqueue it
            for i in self.graph[s]:
                # if visited[i] == False:
                if not visited[i]:
                    qu.put(i)
                    visited[i] = True

# Driver code


def main():
    
    # ---0------>1
    # |  |\     /
    # |  |    /
    # |  |  /
    # -->2<-       3<---
    #     --------> |  |
    #               ----
    # Create a graph given in
    # the above diagram
    g = Graph()
    g.add_edge(0, 1)
    g.add_edge(0, 2)
    g.add_edge(1, 2)
    g.add_edge(2, 0)
    g.add_edge(2, 3)
    g.add_edge(3, 3)
    
    start_vertex = 1
    print ("Following is Breadth First Traversal (starting from vertex",start_vertex,")")
    
    g.bfs(start_vertex)


if __name__ == '__main__':
    main()
