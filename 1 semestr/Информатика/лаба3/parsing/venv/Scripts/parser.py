import time
start = time.time()
f = open (r'C:\Users\Даниил\Desktop\учеба\информатика\лаба3\parsing\xml.txt', 'r',encoding="utf8" )
f1 = open(r'C:\Users\Даниил\Desktop\учеба\информатика\лаба3\parsing\yaml.txt', 'w', encoding="utf8")
k = 0
s=f.read().split('\n')
for i in range(len(s)):
    a = s[i].replace('<', '>').split('>')[1:-1]
    if len(a) > 2:
        a = a[:-1]
    if len(a) == 1:
        if a[0][0] == '/':
            k -= 1
        else:
            f1.write('   ' * k + '' + a[0]+ ':'+''+'\n')
            k+= 1
    else:
        f1.write('   ' * k + '' + a[0] + ':' + a[1]+''+ '\n')
f1.close()
f.close()
print(time.time() - start)
