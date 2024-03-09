import sys
from collections import deque

M, N, H = map(int, sys.stdin.readline().split())

tomato = [list() for i in range(H)]

for i in range(H):
	for j in range(N):
		tomato[i].append(list(map(int, sys.stdin.readline().split())))

d = [[[-1 for i in range(M)] for j in range(N)] for k in range(H)]
q = deque()

for i in range(H):
	for j in range(N):
		for k in range(M):
			if tomato[i][j][k] == 1:
				q.append([i, j, k])
				d[i][j][k] = 0
			elif tomato[i][j][k] == -1:
				d[i][j][k] = 0
dx = [0, 0, 0, 0, 1, -1]
dy = [0, 0, 1, -1, 0, 0]
dz = [1, -1, 0, 0, 0, 0]

while len(q) != 0:
	tmp = q.popleft()
	x = tmp[0]
	y = tmp[1]
	z = tmp[2]
	
	for i in range(6):
		if 0 <= x+dx[i] < H and 0 <= y+dy[i] < N and 0 <= z+dz[i] < M:
			if d[x+dx[i]][y+dy[i]][z+dz[i]] == -1:
				q.append([x+dx[i], y+dy[i], z+dz[i]])
				d[x+dx[i]][y+dy[i]][z+dz[i]] = d[x][y][z]+1


ans = 0

for i in range(H):
	for j in range(N):
		for k in range(M):
			if d[i][j][k] == -1:
				ans = -1
				break
			elif ans < d[i][j][k]:
				ans = d[i][j][k]
		if ans == -1:
			break
	if ans == -1:
		break
print(ans)