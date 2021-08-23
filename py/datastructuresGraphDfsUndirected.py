# Python3 program to print DFS traversal
# from a given given graph, grap is undirected and unweighted

class UndirectedGraph:

    def __init__(self, num_of_vertices):
        self.num_of_vertices = num_of_vertices
        self.graph = [[] for i in range(self.num_of_vertices)]

    def add_edge(self,vertex,adjacent_vertex):
        # for undirected graph relation in both directions must be created
        self.graph[vertex].append(adjacent_vertex)
        self.graph[adjacent_vertex].append(vertex)

    def dfs_util(self,vertex,visited):
        visited[vertex] = True
        print(vertex, end=" ")

        for adjacent_index in range(len(self.graph[vertex])):
            if not visited[self.graph[vertex][adjacent_index]]:
                self.dfs_util(self.graph[vertex][adjacent_index],visited)

    def dfs_disconnected(self):
        visited = [False] * (self.num_of_vertices)
        print(visited)
        print(self.graph)

        # full, disconnected graph
        for u in range(self.num_of_vertices):
            if not visited[u]:
                self.dfs_util(u,visited)

    def dfs(self,root_vertex):
        visited = [False] * (self.num_of_vertices)

        self.dfs_util(root_vertex,visited)

if __name__ == '__main__':
    num_of_vertices = 5
    g = UndirectedGraph(num_of_vertices)

    # Vertex numbers should be from 0 to 4.

    # 0-------1-------2
    # |     / |     /
    # |   /   |   /
    # | /     | /
    # 4-------3
    g.add_edge(0, 1)
    g.add_edge(0, 4)
    g.add_edge(1, 2)
    g.add_edge(1, 3)
    g.add_edge(1, 4)
    g.add_edge(2, 3)
    g.add_edge(3, 4)

    g.dfs_disconnected()
    print()
    g.dfs(4)