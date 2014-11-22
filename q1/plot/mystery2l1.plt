#please plot your results
set logscale xy
set terminal png truecolor
set output "output/mystery2L1.png"
set autoscale
set timefmt "%H:%M:%S"
plot "output/mystery2_l1.result" with linespoints
set output

quit
