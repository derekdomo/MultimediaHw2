all: compute plotImage

compute:
	python q2.py 10000 0.2
	python q2.py 10000 0.7
	python q2.py 10000 0.5
plotImage:
	python plt.py output/q2.2.result
	python plt.py output/q2.3.result
	python plt.py output/q2.4.result
clean: 
	\rm -f *.class output/*
