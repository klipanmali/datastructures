# Python3 program to find transpose of a graph.


class Graph:

    def __init__(self):
        self.graph = dict()

    def add_edge(self, vertex, other_vertex):
        try:
            self.graph[vertex]
        except KeyError:
            self.graph[vertex] = list()

        self.graph[vertex].append(other_vertex)

    def transpose(self):
        transpose_graph = Graph()

        for (key, values) in self.graph.items():
            for value in values:
                transpose_graph.add_edge(value, key)

        return transpose_graph

    def __str__(self):
        return str(self.graph)


if __name__ == "__main__":

    graph = Graph()
    graph.add_edge(0, 1)
    graph.add_edge(0, 4)
    graph.add_edge(0, 3)
    graph.add_edge(2, 0)
    graph.add_edge(3, 2)
    graph.add_edge(4, 1)
    graph.add_edge(4, 3)

    print("Original graph")
    print(graph)
    print("Transposed graph")
    print(graph.transpose())
