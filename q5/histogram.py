#!/usr/bin/env python
# coding=utf-8

import math
import numpy
import pylab as P
tt="clustercritical.dat"
file=open(tt)
lines=file.readlines()
temp1=[[],[]]
for line in lines:
    t=line.split(",")
    temp1[0].append(math.log(int(t[0],10)))
    temp1[1].append(math.log(int(t[1],10)))
tt="cluster0.1.dat"
file=open(tt)
lines=file.readlines()
temp2=[[],[]]
for line in lines:
    t=line.split(",")
    temp2[0].append(math.log(int(t[0]),10))
    temp2[1].append(math.log(int(t[1],10)))
tt="cluster0.9.dat"
file=open(tt)
lines=file.readlines()
temp3=[[],[]]
for line in lines:
    t=line.split(",")
    temp3[0].append(math.log(int(t[0],10)))
    temp3[1].append(math.log(int(t[1],10)))
index=[temp1[0],temp2[0],temp3[0]]
data=[temp1[1],temp2[1],temp3[1]]
P.ylim(0,10)
P.xlim(0,4)
P.hist(index, 35, weights=data, histtype='bar')
fig=P.gcf()
fig.savefig("histogram.png")
