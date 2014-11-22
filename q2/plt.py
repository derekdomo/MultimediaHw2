#!/usr/bin/env python
# coding=utf-8
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot  as plt
import numpy as np
import sys
filename=sys.argv[1]
file=open(filename)
lines=file.readlines()
temp=[[],[]]
for l in lines:
    t=l.split(",")
    temp[0].append(float(t[0]))
    temp[1].append(float(t[1]))
plt.figure()
plt.loglog(temp[0], temp[1])
fig = plt.gcf()
fig.savefig(sys.argv[1]+".pdf")
