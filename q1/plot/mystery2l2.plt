#please plot your results
set logscale xy
set terminal png truecolor
set output "output/mystery2L2.png"
set autoscale
set timefmt "%H:%M:%S"
plot "output/mystery2_l2.result" with linespoints
set output

quit
