# Detect Cycle in a Directed Graph using BFS
from collections import defaultdict
from queue import Queue


class Graph:

    def __init__(self, num_of_v):
        self.num_of_v = num_of_v
        self.adjacents = defaultdict(list)
        self.in_degree = [0] * self.num_of_v

    def add_edge(self, start_v, end_v):
        self.adjacents[start_v].append(end_v)

    def does_cycle_exists(self):

        # compute in degree = num of incoming edges
        for vertex in range(self.num_of_v):
            for adjacent in self.adjacents[vertex]:
                self.in_degree[adjacent] += 1

        # Create an queue and enqueue all vertices with
        # indegree 0
        queue = Queue()
        for vertex in range(self.num_of_v):
            if self.in_degree[vertex] == 0:
                queue.put(vertex)

        counter = 0

        # One by one dequeue vertices from queue and enqueue
        # adjacents if indegree of adjacent becomes 0
        while not queue.empty():

            vertex = queue.get()
            print("from q", vertex)
            for adjacent in self.adjacents.get(vertex):
                self.in_degree[adjacent] -= 1
                if self.in_degree[adjacent] == 0:
                    queue.put(adjacent)

            counter += 1
            print("counter", counter)

        # If count of visited nodes is not equal to the number of nodes in the graph has cycle, otherwise not.
        return False if counter == self.num_of_v else True


if __name__ == "__main__":

    num_of_v = 6
    g = Graph(num_of_v)

    # 3--->4--->5--->0--->1
    #                A    |
    #                |    v
    #                +----2
    # vertex 0 has two incoming edges, and it brakes the loop
    g.add_edge(0, 1)
    g.add_edge(1, 2)
    g.add_edge(2, 0) # cycle

    g.add_edge(3, 4)
    g.add_edge(4, 5)
    g.add_edge(5, 0)

    print("there is cycle in graph" if g.does_cycle_exists() else "no cycle in graph")
