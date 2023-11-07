#BOJ 11724 연결요소의개수
import sys, queue

N, M = map(int, sys.stdin.readline().split())

def dfs(g, start, visit):
    stack = []
    stack.append(start)
    while len(stack):
        now = stack.pop()
        if now not in visit:
            visit.append(now)
            for i in range(0,len(g[now])):
                if g[now][i] == 1 and i not in stack:
                    stack.append(i)

G = [[0 for i in range(0,N)] for j in range(0,N)]
for i in range(0, M):
    u, v = map(int, sys.stdin.readline().split())
    G[u-1][v-1] = 1
    G[v-1][u-1] = 1

visit = []
count = 0
for i in range(0, N):
    if i not in visit:
        count += 1
        dfs(G, i, visit)

print(count)