all: compute plotImage

compute:
	gcc q1.c -o q1 -O3
	./q1 data/mystery1
	./q1 data/mystery2
	perl mkboxcount/mkboxcount.pl -q2 -r0.0000000001 -R10000000000 -s10 data/mystery1 > output/mystery1_mkboxcount.result
	perl mkboxcount/mkboxcount.pl -q2 -r0.0000000001 -R10000000000 -s10 data/mystery2 > output/mystery2_mkboxcount.result

plotImage:
	gnuplot < plot/mystery2l2.plt 
	gnuplot < plot/mk2.plt 
	gnuplot < plot/mk1.plt 
	gnuplot < plot/mysteryl2.plt
	gnuplot < plot/mysteryl1.plt
	gnuplot < plot/mystery2l1.plt

clean: 
	\rm -f *.class output/*.result output/*.png 
