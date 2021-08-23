from collections import defaultdict


class Graph:

    def __init__(self, num_of_vertices):
        self.num_of_vertices = num_of_vertices
        self.adjacent = defaultdict(list)
        self.visited = set()
        self.intime = [0] * self.num_of_vertices
        self.outtime = [0] * self.num_of_vertices
        self.timer = 0

    def add_edge(self, source_vertex, target_vertex):
        self.adjacent[source_vertex].append(target_vertex)

    def dfs_util(self, vertex):
        self.timer += 1
        if vertex not in self.visited:
            # Mark the current node as visited
            # and print it
            self.visited.add(vertex)
            self.intime[vertex] = self.timer

            # Recur for all the vertices
            # adjacent to this vertex
            for adjacent_vertex in self.adjacent[vertex]:
                if adjacent_vertex not in self.visited:
                    self.dfs_util(adjacent_vertex)

            self.timer += 1
            self.outtime[vertex] = self.timer

    def dfs(self, root_vertex):
        self.dfs_util(root_vertex)

    def on_same_path(self, vertex, other_vertex):
        return ((self.intime[vertex] < self.intime[other_vertex] and self.outtime[vertex] > self.outtime[other_vertex]) or (
                    self.intime[other_vertex] < self.outtime[vertex] and self.outtime[other_vertex] > self.outtime[vertex]))


if __name__ == "__main__":
    num_of_vertices = 10
    graph = Graph(num_of_vertices)
    graph.add_edge(1, 2)
    graph.add_edge(1, 3)
    graph.add_edge(3, 6)
    graph.add_edge(2, 4)
    graph.add_edge(2, 5)
    graph.add_edge(5, 7)
    graph.add_edge(5, 8)
    graph.add_edge(5, 9)

    graph.dfs(1)

    print("Yes") if graph.on_same_path(1, 5) else print("No")
    print("Yes") if graph.on_same_path(2, 9) else print("No")
    print("Yes") if graph.on_same_path(2, 6) else print("No")
