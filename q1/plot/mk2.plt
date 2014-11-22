#please plot your results
set logscale xy
set terminal png truecolor
set output "output/mk2.png"
set autoscale
set timefmt "%H:%M:%S"
plot "output/mystery2_mkboxcount.result" with linespoints
set output

quit
