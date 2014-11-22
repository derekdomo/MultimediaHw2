#please plot your results
set logscale xy
set terminal png truecolor
set output "output/mysteryL2.png"
set autoscale
set timefmt "%H:%M:%S"
plot "output/mystery1_l2.result" with linespoints
set output

quit
