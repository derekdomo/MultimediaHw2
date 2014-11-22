#!/usr/bin/env python
# coding=utf-8
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot  as plt
import numpy as np
import math
tt="Q5.2"
file=open(tt)
lines=file.readlines()
temp=[[],[]]
for l in lines:
    t=l.split(",")
    temp[0].append(float(t[0]))
    temp[1].append(float(t[1]))
plt.figure()
plt.plot(temp[0],temp[1])
fig = plt.gcf()
fig.savefig("integral.png")
