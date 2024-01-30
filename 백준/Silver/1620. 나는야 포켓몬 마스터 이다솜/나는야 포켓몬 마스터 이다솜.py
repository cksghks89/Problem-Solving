import sys

N, M = map(int, input().split())
dogamList = [0 for i in range(N+1)]
dogamDict = dict()

for i in range(N):
	temp = sys.stdin.readline().rstrip()
	dogamList[i+1] = temp
	dogamDict[temp] = i+1

for i in range(M):
	q = sys.stdin.readline().rstrip()
	if q.isdigit():
		print(dogamList[int(q)])
	else:
		print(dogamDict[q])
