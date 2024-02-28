import sys

def gcd(a, b):
	x = max([a,b])
	y = min([a,b])
	
	while y != 0:
		n = x%y
		x = y
		y = n
	return x


T = int(input())
for test in range(T):
	M, N, x, y = map(int, sys.stdin.readline().split())
	
	yy = x % N
	if yy == 0:
		yy = N
	
	cnt = 0
	lcd = M*N/gcd(M,N)
	while True:
		if yy == y:
			break
		yy = (yy+M)%N
		if yy == 0:
			yy = N
		cnt += 1
		
		if cnt*M+x > lcd:
			cnt = -1
			break
	if cnt == -1:
		print(-1)
	else:
		print(cnt*M+x)
	
