import networkx as nx


nodes = list(range(1, 21))
G = nx.Graph()
G.add_nodes_from(nodes)


with open("/home/emilia/Documents/graf.txt") as f:
    edges = []
    for line in f:
        line = line.strip()
        edge = []
        for val in line.split("; "):
            edge.append(float(val))
        G.add_edge(int(edge[0]), int(edge[1]), weight=edge[2])


def dijkstry(G, s):
    prev = []
    dist = []
    path = []
    Q = list(G.nodes())
    dist.append(3000.0)
    prev.append(None)
    for node in Q:
        dist.append(3000.0)
        prev.append(None)

    summary = []
    dist[s] = 0.0
    visited = []
    temp = None
    while Q:
        min_dist = 3000.0
        for node in Q:
            if node not in visited:
                if dist[node] < min_dist:
                    temp = node
                    min_dist = dist[node]

        print(temp, dist[temp])
        for (u, v, d) in G.edges(data=True):
            if v == temp and u in Q:
                if dist[u] > dist[v] + d["weight"]:
                    dist[u] = dist[v] + d["weight"]
                    prev[u] = v

            elif u == temp and v in Q:
                if dist[v] > dist[u] + d["weight"]:
                    dist[v] = dist[u] + d["weight"]
                    prev[v] = u
        visited.append(temp)
        path.append(temp)
        summary.append(min_dist)
        Q.remove(temp)
        #print(summary, Q, dist)

    return path, sum(summary), prev


print("Path: ", dijkstry(G, 20))


def find_path(path, start, end):
    init = start
    shortest = []
    while init and not init == end:
        for i in range(len(path)):
            if i == init:
                shortest.append(i)
                init = path[i]
                print(i, path[i])
    return shortest


p, s, pre = dijkstry(G, 20)
shor = find_path(pre, 1, 20)
print(shor)
#print(find_path(pre, 1, 20))
print(nx.dijkstra_path(G, 1, 20, 'weight'))
