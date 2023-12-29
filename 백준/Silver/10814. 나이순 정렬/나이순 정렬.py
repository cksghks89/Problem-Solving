import sys

n = int(input())
arr = []
for i in range(n):
    arr.append(input().split())
    
arr.sort(key=lambda arr: int(arr[0]))

for i in range(n):
    sys.stdout.write(arr[i][0] + ' ' + arr[i][1] + '\n')
