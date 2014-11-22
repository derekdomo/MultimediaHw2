#!/usr/bin/env python
# coding=utf-8
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot  as plt
import numpy as np
import math
tt="result"
file=open(tt)
lines=file.readlines()
temp=[]
for l in lines:
    t=l.split(" ")
    temp.append(float(t[1]))
plt.figure()
plt.loglog([10**(i-10) for i in xrange(21)],temp, label="L1 distance", color="red")
fig = plt.gcf()
fig.savefig("integral.pdf")
