#BOJ 10814 나이순 정렬

N = int(input())

li = []

for i in range(0, N):
	a, b = input().split()
	a = int(a)
	li.append([a,b])

li.sort(key=lambda x: x[0])

for i in range(0, N):
	print(li[i][0],li[i][1])