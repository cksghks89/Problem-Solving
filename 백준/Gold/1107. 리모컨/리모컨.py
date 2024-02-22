def check(num, banSet):
	rtn = True
	li = list(str(num))
	for i in li:
		if int(i) in banSet:
			rtn = False
			break
	return rtn

N = int(input())
M = int(input())

if M == 0:
	print(min([abs(N-100), len(str(N))]))
elif M == 10:
	t = input()
	print(abs(N-100))
else:
	ban = set()
	inputList = list(map(int, input().split()))
	for i in inputList:
		ban.add(i)

	ans = abs(N-100)
	underMax = ans
	overMin = ans

	for i in range(N, -1, -1):
		temp = check(i, ban)
		if temp:
			underMax = N-i + len(str(i))
			break
		
	for i in range(N, N+underMax+ans):
		temp = check(i, ban)
		if temp:
			overMin = i-N + len(str(i))
			break

	ans = min([ans, underMax, overMin])
	print(ans)