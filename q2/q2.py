#!/usr/bin/env python
# coding=utf-8
import random
import sys
argT = int(sys.argv[1])
argP = float(sys.argv[2])
usedTable = [1]
totalNum=1
for i in xrange(argT):
    p = random.random()
    if p<argP:
        usedTable.append(1)
        totalNum=totalNum+1
    else:
        pre = argP 
        for j in xrange(len(usedTable)):
            if p >= pre and p < (pre+(1-argP)*1.0*usedTable[j]/totalNum):
                usedTable[j]=usedTable[j]+1
                totalNum=totalNum+1
                break
            pre=pre+(1-argP)*usedTable[j]/totalNum
dict={}
for i in usedTable:
    if dict.has_key(i):
        dict[i]=dict[i]+1
    else:
        dict[i]=1
filename=""
if sys.argv[2]=='0.2':
    filename="q2.2"
elif sys.argv[2]=='0.7':
    filename="q2.3"
else:
    filename="q2.4"
file="output/"+filename+".result"
handler=open(file,"w")
lines=[]
for i in sorted(dict):
    lines.append(str(i)+","+str(dict[i])+"\n")
handler.writelines(lines);
print dict


